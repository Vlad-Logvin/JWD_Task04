package by.epam.jwd.task04.server.service.impl;

import by.epam.jwd.task04.server.service.TaskMakerService;
import by.epam.jwd.task04.server.service.exception.ServiceException;
import by.epam.jwd.task04.server.service.task.Task;
import by.epam.jwd.task04.server.service.task.TaskMaker;
import by.epam.jwd.task04.text.Text;
import by.epam.jwd.task04.text.impl.ProgrammingText;

public class TaskMakerServiceImpl implements TaskMakerService {
    @Override
    public String performTask(Text text, Task task, Object[] parameters) throws ServiceException {
        TaskMaker taskMaker = new TaskMaker((ProgrammingText) text);
        return taskMaker.performTask(task, parameters);
    }
}
