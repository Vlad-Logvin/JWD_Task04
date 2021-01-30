package by.epam.jwd.task04.text.service;

import by.epam.jwd.task04.text.service.impl.TextServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final TextService textService = new TextServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public TextService getTextService() {
        return textService;
    }
}
