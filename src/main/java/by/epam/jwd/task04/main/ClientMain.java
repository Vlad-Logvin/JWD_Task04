package by.epam.jwd.task04.main;

import by.epam.jwd.task04.client.Client;
import by.epam.jwd.task04.client.ServerConnection;
import by.epam.jwd.task04.client.exception.ClientException;
import by.epam.jwd.task04.client.exception.ServerConnectionException;
import by.epam.jwd.task04.server.localserver.LocalServer;
import by.epam.jwd.task04.server.service.task.Task;

import java.util.ArrayList;
import java.util.List;

public class ClientMain {
    public static void main(String[] args) throws ServerConnectionException {
        Client client = new Client();
        ServerConnection serverConnection = null;
        try {
            serverConnection = client.connect(LocalServer.port);
            //System.out.println(serverConnection.getText());
            System.out.println(serverConnection.performTask(Task.MAX_NUMBER_OF_SENTENCES_WITH_SAME_WORDS));
            System.out.println("01===================================================================");
            System.out.println(serverConnection.performTask(Task.SENTENCES_IN_ASCENDING_ORDER_OF_WORDS));
            System.out.println("02===================================================================");
            System.out.println(serverConnection.performTask(Task.WORD_IN_FIRST_SENTENCE_WHICH_DOES_NOT_HAVE_IN_ANOTHER_SENTENCES));
            System.out.println("03===================================================================");
            System.out.println(serverConnection.performTask(Task.WORDS_WITH_SPECIFIED_LENGTH_IN_ALL_INTERROGATIVE_SENTENCES, 5));
            System.out.println("04===================================================================");
            System.out.println(serverConnection.performTask(Task.TEXT_WITH_SWAPPED_THE_FIRST_AND_THE_LAST_WORDS_IN_ALL_SENTENCES));
            System.out.println("05===================================================================");
            System.out.println(serverConnection.performTask(Task.WORDS_IN_ALPHABETICAL_ORDER_BY_THE_FIRST_LETTER));
            System.out.println("06===================================================================");
            System.out.println(serverConnection.performTask(Task.WORDS_SORTED_BY_INCREASING_THE_PROPORTION_OF_VOWEL_LETTERS));
            System.out.println("07===================================================================");
            System.out.println(serverConnection.performTask(Task.WORDS_STARTING_WITH_VOWELS_SORTED_BY_ALPHABETICAL_ORDER_BY_THE_FIRST_CONSONANT_LETTER));
            System.out.println("08===================================================================");
            System.out.println(serverConnection.performTask(Task.WORDS_SORTED_BY_ASCENDING_ORDER_OF_THE_NUMBER_OF_SPECIFIED_LETTER_IN_THE_WORD, 'и'));
            System.out.println("09===================================================================");
            List<String> words = new ArrayList<>(); words.add("Внимание"); words.add("привет"); words.add("в");
            System.out.println(serverConnection.performTask(Task.WORDS_FROM_GIVEN_LIST_SORTED_BY_THE_NUMBER_OF_OCCURRENCES, words));
            System.out.println("10===================================================================");
            System.out.println(serverConnection.performTask(Task.TEXT_WITH_THE_EXCLUDED_SUBSTRING_STARTING_AND_ENDING_WITH_THE_SPECIFIED_CHARACTERS, 'а', 'и'));
            System.out.println("11===================================================================");
            System.out.println(serverConnection.performTask(Task.TEXT_WITH_REMOVED_WORDS_OF_THE_SPECIFIED_LENGTH_STARTING_WITH_A_CONSONANT_LETTER, 8));
            System.out.println("12===================================================================");
            System.out.println(serverConnection.performTask(Task.TEXT_WITH_SORTED_WORDS_IN_DESCENDING_ORDER_OF_THE_NUMBER_OF_OCCURRENCES_OF_THE_SPECIFIED_CHARACTER, 'и'));
            System.out.println("13===================================================================");
            System.out.println(serverConnection.performTask(Task.MAX_SUBSTRING_PALINDROME));
            System.out.println("14===================================================================");
            System.out.println(serverConnection.performTask(Task.TEXT_WITH_CONVERTED_EACH_WORD_BY_REMOVING_ALL_SUBSEQUENT_OCCURRENCES_OF_THE_FIRST_LETTER_OF_THE_WORD));
            System.out.println("15===================================================================");
            System.out.println(serverConnection.performTask(Task.SENTENCE_WITH_WORDS_OF_A_GIVEN_LENGTH_REPLACED_BY_THE_SPECIFIED_SUBSTRING, 3, 8, "hello"));
            System.out.println("16===================================================================");
        } catch (ClientException | ServerConnectionException e) {
            System.out.println(e.getMessage());
        } finally {
            if (serverConnection!=null && serverConnection.isStart()) {
                serverConnection.stopConnection();
            }
        }
    }
}