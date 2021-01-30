package by.epam.jwd.task04.server.service;

import by.epam.jwd.task04.server.service.impl.TaskMakerServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final TaskMakerService taskMakerService = new TaskMakerServiceImpl();

    private ServiceFactory(){

    }

    public static ServiceFactory getInstance(){
        return instance;
    }

    public TaskMakerService getTaskMakerService() {
        return taskMakerService;
    }
}
