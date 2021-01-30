package by.epam.jwd.task04.text.dao;

import by.epam.jwd.task04.text.dao.exception.DAOException;

public interface TextDAO {
    String getText() throws DAOException;
}
