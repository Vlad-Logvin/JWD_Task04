package by.epam.jwd.task04.server.service.task;

import java.io.Serializable;

public enum Task implements Serializable {
    MAX_NUMBER_OF_SENTENCES_WITH_SAME_WORDS("Task01", 0),
    SENTENCES_IN_ASCENDING_ORDER_OF_WORDS("Task02", 0),
    WORD_IN_FIRST_SENTENCE_WHICH_DOES_NOT_HAVE_IN_ANOTHER_SENTENCES("Task03", 0),
    WORDS_WITH_SPECIFIED_LENGTH_IN_ALL_INTERROGATIVE_SENTENCES("Task04", 1),
    TEXT_WITH_SWAPPED_THE_FIRST_AND_THE_LAST_WORDS_IN_ALL_SENTENCES("Task05", 0),
    WORDS_IN_ALPHABETICAL_ORDER_BY_THE_FIRST_LETTER("Task06", 0),
    WORDS_SORTED_BY_INCREASING_THE_PROPORTION_OF_VOWEL_LETTERS("Task07", 0),
    WORDS_STARTING_WITH_VOWELS_SORTED_BY_ALPHABETICAL_ORDER_BY_THE_FIRST_CONSONANT_LETTER("Task08", 0),
    WORDS_SORTED_BY_ASCENDING_ORDER_OF_THE_NUMBER_OF_SPECIFIED_LETTER_IN_THE_WORD("Task09", 1),
    WORDS_FROM_GIVEN_LIST_SORTED_BY_THE_NUMBER_OF_OCCURRENCES("Task10", 1),
    TEXT_WITH_THE_EXCLUDED_SUBSTRING_STARTING_AND_ENDING_WITH_THE_SPECIFIED_CHARACTERS("Task11", 2),
    TEXT_WITH_REMOVED_WORDS_OF_THE_SPECIFIED_LENGTH_STARTING_WITH_A_CONSONANT_LETTER("Task12", 1),
    TEXT_WITH_SORTED_WORDS_IN_DESCENDING_ORDER_OF_THE_NUMBER_OF_OCCURRENCES_OF_THE_SPECIFIED_CHARACTER("Task13", 1),
    MAX_SUBSTRING_PALINDROME("Task14", 0),
    TEXT_WITH_CONVERTED_EACH_WORD_BY_REMOVING_ALL_SUBSEQUENT_OCCURRENCES_OF_THE_FIRST_LETTER_OF_THE_WORD("Task15", 0),
    SENTENCE_WITH_WORDS_OF_A_GIVEN_LENGTH_REPLACED_BY_THE_SPECIFIED_SUBSTRING("Task16", 3);

    private final String task;
    private final int numberOfParameters;

    Task(String task, int numberOfParameters) {
        this.task = task;
        this.numberOfParameters = numberOfParameters;
    }

    public String getTask() {
        return task;
    }

    public int getNumberOfParameters()
    {
        return numberOfParameters;
    }
}
