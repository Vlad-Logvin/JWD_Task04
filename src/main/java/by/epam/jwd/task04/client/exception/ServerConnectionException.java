package by.epam.jwd.task04.client.exception;

public class ServerConnectionException extends Exception{
    public ServerConnectionException() {
        super();
    }

    public ServerConnectionException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
