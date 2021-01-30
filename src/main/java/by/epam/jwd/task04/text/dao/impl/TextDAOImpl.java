package by.epam.jwd.task04.text.dao.impl;

import by.epam.jwd.task04.text.dao.TextDAO;
import by.epam.jwd.task04.text.dao.exception.DAOException;
import org.apache.log4j.Logger;

import java.io.*;

public class TextDAOImpl implements TextDAO {
    private static final Logger logger = Logger.getLogger(TextDAOImpl.class);
    private final File filePath = new File("D:\\Java\\Java epam\\programs\\client-server-application\\src\\main\\resources\\BookText.txt");

    @Override
    public String getText() throws DAOException {
        BufferedReader bufferedReader = null;
        try {
            logger.info("Trying to create BufferedReader object.");
            bufferedReader = new BufferedReader(new FileReader(filePath));
            logger.info("BufferedReader object was created.");
            StringBuilder textBuilder = new StringBuilder();
            logger.info("Trying to read line from file.");
            String line = bufferedReader.readLine();
            logger.info("Line was read.");
            while (line != null) {
                logger.info("Trying to append textBuilder.");
                textBuilder.append(line);
                textBuilder.append(System.lineSeparator());
                logger.info("textBuilder was append.");
                logger.info("Trying to read line from file.");
                line = bufferedReader.readLine();
                logger.info("Line was read.");
            }
            return textBuilder.toString();
        } catch (IOException e) {
            logger.error("IOException was thrown due to unsuccessfully reading from file or creating BufferedReader object.", e);
            throw new DAOException(e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    logger.info("Trying to close BufferedReader object.");
                    bufferedReader.close();
                    logger.info("BufferedReader object was closed.");
                }
            } catch (IOException e) {
                logger.error("IOException was thrown due to unsuccessfully closing BufferedReader object.", e);
                throw new DAOException(e.getMessage());
            }
        }
    }
}
