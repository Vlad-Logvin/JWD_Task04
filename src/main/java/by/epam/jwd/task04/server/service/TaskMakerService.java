package by.epam.jwd.task04.server.service;

import by.epam.jwd.task04.server.service.exception.ServiceException;
import by.epam.jwd.task04.server.service.task.Task;
import by.epam.jwd.task04.text.Text;

public interface TaskMakerService {
    String performTask(Text text, Task task, Object[] parameters) throws ServiceException;
}
