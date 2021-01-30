package by.epam.jwd.task04.text.dao.exception;

public class DAOException extends Exception{
    public DAOException() {
        super();
    }

    public DAOException(String message) {
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
