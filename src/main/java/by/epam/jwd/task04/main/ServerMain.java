package by.epam.jwd.task04.main;

import by.epam.jwd.task04.server.exception.LocalServerException;
import by.epam.jwd.task04.server.localserver.LocalServer;

public class ServerMain {
    public static void main(String[] args) throws  LocalServerException {
        LocalServer server = null;
        try {
            server = LocalServer.getInstance();
            server.startServer();
        } catch (LocalServerException e) {
            System.out.println(e.getMessage());
        } finally {
            if (server!= null && server.isStart()) {
                server.stopServer();
            }
        }
    }
}
