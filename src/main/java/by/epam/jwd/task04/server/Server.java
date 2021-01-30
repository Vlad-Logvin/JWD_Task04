package by.epam.jwd.task04.server;

import by.epam.jwd.task04.server.exception.LocalServerException;

public abstract class Server {
    private boolean start;

    public abstract void startServer() throws LocalServerException;
    public boolean isStart(){
        return start;
    }
    protected void setStart(boolean start) { //Чтобы не давать доступ к использованию run кому попало
        this.start = start;
    }
}
