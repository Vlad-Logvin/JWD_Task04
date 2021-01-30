package by.epam.jwd.task04.server.exception;

public class LocalServerException extends Exception {
    public LocalServerException() {
        super();
    }

    public LocalServerException(String message) {
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
