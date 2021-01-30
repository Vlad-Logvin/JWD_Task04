package by.epam.jwd.task04.server.service.task.exception;

import by.epam.jwd.task04.server.service.task.Task;

public class TaskExceptionMessage {
    public static String getTaskMessage(Task task) {
        return "Incorrect parameters! " + task.getTask() + " contains " + task.getNumberOfParameters() + " parameters.\n" + getParameters(task);
    }

    private static String getParameters(Task task) {
        switch (task) {
            case WORDS_WITH_SPECIFIED_LENGTH_IN_ALL_INTERROGATIVE_SENTENCES -> {
                return "\t1: wordLength: int - the length of words which will be gotten.\n";
            }
            case WORDS_SORTED_BY_ASCENDING_ORDER_OF_THE_NUMBER_OF_SPECIFIED_LETTER_IN_THE_WORD,
                    TEXT_WITH_SORTED_WORDS_IN_DESCENDING_ORDER_OF_THE_NUMBER_OF_OCCURRENCES_OF_THE_SPECIFIED_CHARACTER -> {
                return "\t1: letter: char - the letter to be sorted by.\n";
            }
            case WORDS_FROM_GIVEN_LIST_SORTED_BY_THE_NUMBER_OF_OCCURRENCES -> {
                return "\t1: words: List<String> - the words that will be sorted.\n";
            }
            case TEXT_WITH_THE_EXCLUDED_SUBSTRING_STARTING_AND_ENDING_WITH_THE_SPECIFIED_CHARACTERS -> {
                return """
                        \t1: begin: char - the letter that substring begins.
                        \t2: end: char - the letter thar substring ends.
                        """;
            }
            case TEXT_WITH_REMOVED_WORDS_OF_THE_SPECIFIED_LENGTH_STARTING_WITH_A_CONSONANT_LETTER -> {
                return "\t1: wordLength: int - the length of words which will be deleted.\n";
            }
            case SENTENCE_WITH_WORDS_OF_A_GIVEN_LENGTH_REPLACED_BY_THE_SPECIFIED_SUBSTRING -> {
                return """
                        \t1: sentenceNumber: int - the number of sentence.
                        \t2: wordLength: int - the length of words which will be replaced.
                        \t3: substring: String - the string to replace the word with.
                        """;
            }
            default -> {
                return "";
            }
        }
    }
}
