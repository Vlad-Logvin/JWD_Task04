package by.epam.jwd.task04.client.exception;

public class ClientException extends Exception{
    public ClientException() {
        super();
    }

    public ClientException(String message) {
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
