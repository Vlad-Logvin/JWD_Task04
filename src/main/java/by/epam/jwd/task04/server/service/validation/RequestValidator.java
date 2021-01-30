package by.epam.jwd.task04.server.service.validation;

import by.epam.jwd.task04.server.service.task.Task;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.List;

public class RequestValidator implements Serializable {

    private static final Logger logger = Logger.getLogger(RequestValidator.class);
    private boolean textContent;
    private Task task;
    private Object[] parameters;

    public boolean isTextContent() {
        return textContent;
    }

    public void setTextContent(boolean textContent) {
        this.textContent = textContent;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public Task getTask() {
        return task;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public RequestValidator(Task task, Object[] parameters) {
        textContent = false;
        this.task = task;
        this.parameters = parameters;
    }

    public boolean isCorrectInput() {
        logger.info("Checking for correct input.");
        if (task == null) {
            return false;
        }
        if (task.getNumberOfParameters() != parameters.length) {
            return false;
        }
        return checkInputParameters();
    }

    private boolean checkInputParameters() {
        switch (task) {
            case WORDS_WITH_SPECIFIED_LENGTH_IN_ALL_INTERROGATIVE_SENTENCES:
            case TEXT_WITH_REMOVED_WORDS_OF_THE_SPECIFIED_LENGTH_STARTING_WITH_A_CONSONANT_LETTER:
                if (parameters[0].getClass() != Integer.class) {
                    return false;
                }
                break;
            case WORDS_SORTED_BY_ASCENDING_ORDER_OF_THE_NUMBER_OF_SPECIFIED_LETTER_IN_THE_WORD:
            case TEXT_WITH_SORTED_WORDS_IN_DESCENDING_ORDER_OF_THE_NUMBER_OF_OCCURRENCES_OF_THE_SPECIFIED_CHARACTER:
                if (parameters[0].getClass() != Character.class) {
                    return false;
                }
                break;
            case WORDS_FROM_GIVEN_LIST_SORTED_BY_THE_NUMBER_OF_OCCURRENCES:
                if (parameters[0] instanceof List<?>) {
                    List<Object> words = (List<Object>) parameters[0];
                    for (Object word : words) {
                        if (word.getClass() != String.class) {
                            return false;
                        }
                    }
                }
                break;
            case TEXT_WITH_THE_EXCLUDED_SUBSTRING_STARTING_AND_ENDING_WITH_THE_SPECIFIED_CHARACTERS:
                if (parameters[0].getClass() != Character.class || parameters[1].getClass() != Character.class) {
                    return false;
                }
                break;
            case SENTENCE_WITH_WORDS_OF_A_GIVEN_LENGTH_REPLACED_BY_THE_SPECIFIED_SUBSTRING:
                if (parameters[0].getClass() != Integer.class || parameters[1].getClass() != Integer.class
                        || parameters[2].getClass() != String.class){
                    return false;
                }
                    break;
        }
        return true;
    }

    public boolean getTextContent() {
        return textContent;
    }
}
