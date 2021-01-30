package by.epam.jwd.task04.text.service.impl;

import by.epam.jwd.task04.text.Text;
import by.epam.jwd.task04.text.dao.DAOFactory;
import by.epam.jwd.task04.text.dao.TextDAO;
import by.epam.jwd.task04.text.dao.exception.DAOException;
import by.epam.jwd.task04.text.service.ServiceException;
import by.epam.jwd.task04.text.service.TextService;
import by.epam.jwd.task04.text.service.parser.TextParser;
import org.apache.log4j.Logger;

public class TextServiceImpl implements TextService {
    private static final Logger logger = Logger.getLogger(TextServiceImpl.class);
    @Override
    public Text parseText() throws ServiceException {
        logger.info("Getting DAOFactory instance.");
        DAOFactory daoFactory = DAOFactory.getInstance();
        logger.info("Getting textDAO from DAOFactory.");
        TextDAO textDAO = daoFactory.getTextDAO();
        try {
            logger.info("Trying to parse text from textDAO.");
            return TextParser.parseText(textDAO.getText());
        } catch (DAOException e) {
            logger.error("DAOException was thrown due to unsuccessfully parsing text.", e);
            throw new ServiceException(e.getMessage());
        }
    }
}
