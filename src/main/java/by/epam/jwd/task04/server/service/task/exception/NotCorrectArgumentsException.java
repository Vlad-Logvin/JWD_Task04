package by.epam.jwd.task04.server.service.task.exception;

public class NotCorrectArgumentsException extends Exception{
    public NotCorrectArgumentsException() {
        super();
    }

    public NotCorrectArgumentsException(String message) {
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
