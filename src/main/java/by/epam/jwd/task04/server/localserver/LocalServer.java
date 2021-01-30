package by.epam.jwd.task04.server.localserver;

import by.epam.jwd.task04.server.Server;
import by.epam.jwd.task04.server.exception.LocalServerException;
import by.epam.jwd.task04.text.Text;
import by.epam.jwd.task04.text.impl.ProgrammingText;
import by.epam.jwd.task04.text.service.ServiceException;
import by.epam.jwd.task04.text.service.ServiceFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;

public class LocalServer extends Server{

    private static final Logger logger = Logger.getLogger(LocalServer.class);

    private final ServerSocket localServerSocket;

    public static final int port = 1111;

    private final boolean isStarted = true;
    private final boolean isStop = false;

    private final ProgrammingText text;

    private static LocalServer localServer = null;

    public static synchronized LocalServer getInstance() throws LocalServerException {
        logger.info("Checks the existence of the server.");
        if (localServer == null) {
            logger.info("Server is not exist. Creating server.");
            localServer = new LocalServer();
            logger.info("Server created successfully.");
        }
        return localServer;
    }

    @Override
    public void startServer() throws LocalServerException {
        while (true){
            try {
                logger.info("Server is waiting for client.");
                new ClientConnection(localServerSocket.accept());
            } catch (IOException e) {
                logger.error("IOException was thrown due to an unsuccessful connection.", e);
                throw new LocalServerException(e.getMessage());
            }
        }
    }

    public void stopServer() throws LocalServerException {
        try {
            logger.info("Trying to close server.");
            localServerSocket.close();
            logger.info("Server was closed.");
        } catch (IOException e) {
            logger.error("IOException was thrown due to an unsuccessful closing.", e);
            throw new LocalServerException(e.getMessage());
        }
        setStart(isStop);
    }

    private LocalServer() throws LocalServerException {
        try {
            logger.info("Trying to create server.");
            localServerSocket = new ServerSocket(port);
            logger.info("Server is created. Trying to parse text from file.");
            text = (ProgrammingText) ServiceFactory.getInstance().getTextService().parseText();
            logger.info("Text was parsed.");
        } catch (IOException e) {
            logger.error("IOException was thrown due to an unsuccessful creating.", e);
            throw new LocalServerException(e.getMessage());
        } catch (ServiceException e) {
            logger.error("ServiceException was thrown due to unsuccessfully parsing.", e);
            throw new LocalServerException(e.getMessage());
        }
        setStart(isStarted);
    }

    public Text getText() {
        return text;
    }
}
