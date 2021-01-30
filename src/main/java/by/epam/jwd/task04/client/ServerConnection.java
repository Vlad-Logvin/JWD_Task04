package by.epam.jwd.task04.client;

import by.epam.jwd.task04.client.exception.ServerConnectionException;
import by.epam.jwd.task04.server.service.task.exception.NotCorrectArgumentsException;
import by.epam.jwd.task04.server.service.task.Task;
import by.epam.jwd.task04.server.service.validation.RequestValidator;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnection {
    private final static Logger logger = Logger.getLogger(ServerConnection.class);
    private final Socket serverSocket;
    private final ObjectOutputStream clientOutput;
    private final ObjectInputStream clientInput;
    private boolean isStart = true;

    public ServerConnection(Socket serverSocket) throws ServerConnectionException {
        this.serverSocket = serverSocket;
        try {
            logger.info("Trying to get output stream.");
            clientOutput = new ObjectOutputStream(serverSocket.getOutputStream());
            logger.info("Output stream was gotten.");
            logger.info("Trying to get input stream.");
            clientInput = new ObjectInputStream(serverSocket.getInputStream());
            logger.info("Input stream was gotten.");
        } catch (IOException e) {
            logger.error("IOException was thrown due to unsuccessfully getting input or output stream.", e);
            throw new ServerConnectionException(e.getMessage());
        }
    }

    public String getText() throws ServerConnectionException {
        try {
            logger.info("Trying to write object.");
            clientOutput.writeObject("text");
            logger.info("Object was written.");
            logger.info("Trying to read object and send to client.");
            return (String) clientInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("IOException or ClassNotFoundException were throw due to unsuccessfully reading or writing object from stream.", e);
            throw new ServerConnectionException(e.getMessage());
        }
    }

    public String performTask(Task task, Object... parameters) throws ServerConnectionException {
        try {
            logger.info("Trying to write object.");
            clientOutput.writeObject(new RequestValidator(task, parameters));
            logger.info("Object was written.");
            logger.info("Trying to read object.");
            Object obj = clientInput.readObject();
            logger.info("Object was read. Trying to define object.");
            if (obj instanceof NotCorrectArgumentsException) {
                logger.info("Object is NotCorrectArgumentsException. Throw ServerConnectionException.");
                throw new ServerConnectionException(((NotCorrectArgumentsException) obj).getMessage());
            } else if (obj instanceof String) {
                logger.info("Object is String. Return object as string.");
                return (String) obj;
            } else {
                logger.info("Unknown object. Throw ServerConnectionException.");
                throw new ServerConnectionException("Unknown object has come while reading object.");
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.error("IOException or ClassNotFoundException were throw due to unsuccessfully reading or writing object from stream.", e);
            throw new ServerConnectionException(e.getMessage());
        }
    }

    public void stopConnection() throws ServerConnectionException {
        try {
            logger.info("Trying to write object.");
            clientOutput.writeObject("stop connect");
            logger.info("Object was written.");
            logger.info("Trying to close socket.");
            serverSocket.close();
            logger.info("Socket is close. Connection is broken.");
            isStart = false;
        } catch (IOException e) {
            logger.error("IOException was thrown due to unsuccessfully writing object to stream or closing.", e);
            throw new ServerConnectionException(e.getMessage());
        }
    }

    public boolean isStart() {
        return isStart;
    }
}
