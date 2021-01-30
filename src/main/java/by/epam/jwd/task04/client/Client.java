package by.epam.jwd.task04.client;

import by.epam.jwd.task04.client.exception.ClientException;
import by.epam.jwd.task04.client.exception.ServerConnectionException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private final static Logger logger = Logger.getLogger(Client.class);

    private static final String defaultHost = "localhost";

    public ServerConnection connect(int port) throws ClientException {
        try {
            logger.info("Trying to connect to server.");
            Socket serverSocket = new Socket(defaultHost, port);
            logger.info("Connection successful.");
            return new ServerConnection(serverSocket);
        } catch (IOException e) {
            logger.error("IOException was thrown due to unsuccessfully connection to server.", e);
            throw new ClientException(e.getMessage());
        } catch (ServerConnectionException e) {
            logger.error("ServerConnectionException was thrown due to unsuccessfully communication with the server.", e);
            throw new ClientException(e.getMessage());
        }
    }
}
