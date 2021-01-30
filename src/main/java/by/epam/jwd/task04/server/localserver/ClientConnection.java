package by.epam.jwd.task04.server.localserver;

import by.epam.jwd.task04.server.exception.LocalServerException;
import by.epam.jwd.task04.server.service.ServiceFactory;
import by.epam.jwd.task04.server.service.TaskMakerService;
import by.epam.jwd.task04.server.service.exception.ServiceException;
import by.epam.jwd.task04.server.service.task.exception.NotCorrectArgumentsException;
import by.epam.jwd.task04.server.service.task.exception.TaskExceptionMessage;
import by.epam.jwd.task04.server.service.validation.RequestValidator;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientConnection extends Thread {

    private static final Logger logger = Logger.getLogger(ClientConnection.class);

    private final Socket clientSocket;

    private ObjectInputStream serverInput;
    private ObjectOutputStream serverOutput;

    private final TaskMakerService taskMakerService;

    @Override
    public void run() {
        try {
            logger.info("New thread created.");
            logger.info("Trying to get output stream.");
            serverOutput = new ObjectOutputStream(clientSocket.getOutputStream());
            logger.info("Output stream is gotten.");
            logger.info("Trying to get input stream.");
            serverInput = new ObjectInputStream(clientSocket.getInputStream());
            logger.info("Input stream is gotten.");
            logger.info("Start conversation.");
            while (true) {
                try {
                    logger.info("Trying to read object from output stream.");
                    Object request = serverInput.readObject();
                    logger.info("Object is read. Trying to define an object type.");
                    if (request instanceof String) {
                        logger.info("Object is String. Trying to read the string.");
                        if ("stop connect".equals(request)) {
                            logger.info("String is \"stop connect\".Break the loop.");
                            break;
                        } else if ("text".equals(request)) {
                            logger.info("String is \"text\". Trying to send text to client.");
                            serverOutput.writeObject(LocalServer.getInstance().getText().getFileText());
                            logger.info("Text was sent.");
                        } else {
                            logger.info("Unknown string. Break connection and loop.");
                            serverOutput.writeObject(OutputStream.nullOutputStream());
                            break;
                        }
                    } else if (request instanceof RequestValidator requestValidator) {
                        logger.info("Object is RequestValidator. Check input parameters.");
                        if (requestValidator.isCorrectInput()) {
                            logger.info("Input parameters are correct. Trying to perform and send task to client.");
                            serverOutput.writeObject(taskMakerService.performTask(LocalServer.getInstance().getText(),
                                    requestValidator.getTask(), requestValidator.getParameters()));
                            logger.info("Task was sent.");
                        } else {
                            logger.info("Input parameters aren't correct. Trying to send NotCorrectArgumentsException to client.");
                            serverOutput.writeObject(new NotCorrectArgumentsException(
                                    TaskExceptionMessage.getTaskMessage(requestValidator.getTask())));
                            logger.info("NotCorrectArgumentsException was sent");
                        }
                    } else {
                        logger.info("Unknown class type. Break connection and loop.");
                        serverOutput.writeObject(OutputStream.nullOutputStream());
                        break;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    logger.error("IOException was thrown due to unsuccessfully writing or reading object. Break the loop.", e);
                    e.printStackTrace();
                    break;
                } catch (LocalServerException e) {
                    logger.error("LocalServerException was thrown due to unsuccessfully getting instance of LocalServer.", e);
                    e.printStackTrace();
                    break;
                } catch (ServiceException e) {
                    logger.error("ServiceException was throw due to unsuccessfully performing task.", e);
                    e.printStackTrace();
                    break;
                }
            }
        } catch (IOException e) {
            logger.error("IOException was thrown due to unsuccessfully getting input or output stream.", e);
            e.printStackTrace();
        } finally {
            try {
                logger.info("Trying to close server input.");
                serverInput.close();
                logger.info("Server input is closed.");
                logger.info("Trying to close server output.");
                serverOutput.close();
                logger.info("Server output is closed.");
            } catch (IOException e) {
                logger.error("IOException was thrown due to unsuccessfully closing input or output stream.", e);
                e.printStackTrace();
            }
        }

    }

    public ClientConnection(Socket clientSocket) {
        this.clientSocket = clientSocket;
        taskMakerService = ServiceFactory.getInstance().getTaskMakerService();
        logger.info("Class ClientConnection is created. Starting a new thread.");
        start();
    }
}
