package by.epam.jwd.task04.server.service.task;

import by.epam.jwd.task04.server.service.exception.ServiceException;
import by.epam.jwd.task04.server.service.task.util.*;
import by.epam.jwd.task04.text.impl.ProgrammingText;
import by.epam.jwd.task04.text.impl.textelement.impl.sentence.Sentence;
import by.epam.jwd.task04.text.impl.textelement.impl.sentence.sentenceelement.SentenceElement;
import by.epam.jwd.task04.text.impl.textelement.impl.sentence.sentenceelement.impl.word.Word;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class TaskMaker implements Serializable {

    private final static Logger logger = Logger.getLogger(TaskMaker.class);

    private final ProgrammingText text;

    public TaskMaker(ProgrammingText text) {
        this.text = text;
    }

    public String performTask(Task task, Object[] parameters) throws ServiceException {
        logger.info("Trying to find needing task.");
        switch (task) {
            case MAX_NUMBER_OF_SENTENCES_WITH_SAME_WORDS -> {
                return getTask01();
            }
            case SENTENCES_IN_ASCENDING_ORDER_OF_WORDS -> {
                return getTask02();
            }
            case WORD_IN_FIRST_SENTENCE_WHICH_DOES_NOT_HAVE_IN_ANOTHER_SENTENCES -> {
                return getTask03();
            }
            case WORDS_WITH_SPECIFIED_LENGTH_IN_ALL_INTERROGATIVE_SENTENCES -> {
                return getTask04((int) parameters[0]);
            }
            case TEXT_WITH_SWAPPED_THE_FIRST_AND_THE_LAST_WORDS_IN_ALL_SENTENCES -> {
                return getTask05();
            }
            case WORDS_IN_ALPHABETICAL_ORDER_BY_THE_FIRST_LETTER -> {
                return getTask06();
            }
            case WORDS_SORTED_BY_INCREASING_THE_PROPORTION_OF_VOWEL_LETTERS -> {
                return getTask07();
            }
            case WORDS_STARTING_WITH_VOWELS_SORTED_BY_ALPHABETICAL_ORDER_BY_THE_FIRST_CONSONANT_LETTER -> {
                return getTask08();
            }
            case WORDS_SORTED_BY_ASCENDING_ORDER_OF_THE_NUMBER_OF_SPECIFIED_LETTER_IN_THE_WORD -> {
                return getTask09((char) parameters[0]);
            }
            case WORDS_FROM_GIVEN_LIST_SORTED_BY_THE_NUMBER_OF_OCCURRENCES -> {
                return getTask10((List<String>) parameters[0]);
            }
            case TEXT_WITH_THE_EXCLUDED_SUBSTRING_STARTING_AND_ENDING_WITH_THE_SPECIFIED_CHARACTERS -> {
                return getTask11((char) parameters[0], (char) parameters[1]);
            }
            case TEXT_WITH_REMOVED_WORDS_OF_THE_SPECIFIED_LENGTH_STARTING_WITH_A_CONSONANT_LETTER -> {
                return getTask12((int) parameters[0]);
            }
            case TEXT_WITH_SORTED_WORDS_IN_DESCENDING_ORDER_OF_THE_NUMBER_OF_OCCURRENCES_OF_THE_SPECIFIED_CHARACTER -> {
                return getTask13((char) parameters[0]);
            }
            case MAX_SUBSTRING_PALINDROME -> {
                return getTask14();
            }
            case TEXT_WITH_CONVERTED_EACH_WORD_BY_REMOVING_ALL_SUBSEQUENT_OCCURRENCES_OF_THE_FIRST_LETTER_OF_THE_WORD -> {
                return getTask15();
            }
            case SENTENCE_WITH_WORDS_OF_A_GIVEN_LENGTH_REPLACED_BY_THE_SPECIFIED_SUBSTRING -> {
                return getTask16((int) parameters[0], (int) parameters[1], (String) parameters[2]);
            }
            default -> {
                logger.warn("Task was not found. Throw ServiceException.");
                throw new ServiceException("no such task");
            }
        }
    }

    private String getTask01() {
        logger.info("Task01 found. Start to perform.");
        return Collections.max(TaskUtil.findSameWordsInDifferentSentences(text).values()).toString();
    }

    private String getTask02() {
        logger.info("Task02 found. Start to perform.");
        List<Sentence> sentences = TaskUtil.getSentenceList(text);
        sentences.sort(new SentenceWordNumberComparator());
        return TaskUtil.getSentenceString(sentences);
    }

    private String getTask03() {
        logger.info("Task03 found. Start to perform.");
        Map<Word, Integer> wordMap = TaskUtil.putWordsToMap(TaskUtil.getSentenceList(text).get(0));
        List<Word> wordList = TaskUtil.getWordList(text);
        wordList = wordList.subList(wordMap.size(), wordList.size());
        for (Word word1 : wordList) {
            if (wordMap.get(word1) != null) {
                wordMap.remove(word1);
            }
        }
        String words = TaskUtil.getWordString((new ArrayList<>(wordMap.keySet())));
        return "".equals(words) ? "There are no such words" : words;
    }

    private String getTask04(int wordLength) {
        logger.info("Task04 found. Start to perform.");
        StringBuilder words = new StringBuilder();
        for (Sentence sentence : TaskUtil.getSentenceList(text)) {
            List<SentenceElement> sentenceElements = sentence.getSentenceElements();
            if (sentenceElements.get(sentenceElements.size() - 1).getSentenceElement().equals("?")) {
                for (SentenceElement el : sentenceElements) {
                    if (el instanceof Word && el.getSentenceElement().length() != wordLength) {
                        words.append(el.getSentenceElement()).append(" ");
                    }
                }
            }
        }
        return "".equals(words.toString()) ? "All words are deleted or there is no interrogative sentences" : words.toString();
    }

    private String getTask05() {
        logger.info("Task05 found. Start to perform.");
        StringBuilder sentences = new StringBuilder();
        for (Sentence sentence : TaskUtil.getSentenceList(text)) {
            List<SentenceElement> elements = TaskUtil.swapFirstAndLastWordInSentence(sentence.getSentenceElements());
            List<Sentence> sentenceList = new ArrayList<>();
            sentenceList.add(new Sentence(elements));
            sentences.append(TaskUtil.getSentenceString(sentenceList));
        }
        return sentences.toString();
    }

    private String getTask06() {
        logger.info("Task06 found. Start to perform.");
        StringBuilder words = new StringBuilder();
        List<Word> wordList = TaskUtil.getWordList(text);
        wordList.sort(new WordAlphabeticalOrderComparator());
        int wordListLength = wordList.size();
        for (int i = 0; i < wordListLength; i++) {
            if (i != 0 && wordList.get(i).getSentenceElement().charAt(0) > wordList.get(i - 1).getSentenceElement().charAt(0)) {
                words.append(System.lineSeparator());
            }
            words.append(wordList.get(i).getSentenceElement()).append(" ");
        }
        return words.toString();
    }

    private String getTask07() {
        logger.info("Task07 found. Start to perform.");
        List<Word> wordList = TaskUtil.getWordList(text);
        wordList.sort(new WordVowelProportionComparator());
        return TaskUtil.getWordString(wordList);
    }

    private String getTask08() {
        logger.info("Task08 found. Start to perform.");
        StringBuilder words = new StringBuilder();
        List<Word> wordList = TaskUtil.getWordList(text);
        wordList.sort(new WordConsonantComparator());
        String vowels = "аэуеоияеыюАЭУЕОИЫЯЭЮ";
        for (Word word : wordList) {
            if (vowels.contains(String.valueOf(word.getSentenceElement().charAt(0)))) {
                words.append(word.getSentenceElement()).append(" ");
            }
        }
        return words.toString();
    }

    private String getTask09(char letter) {
        logger.info("Task09 found. Start to perform.");
        List<Word> wordList = TaskUtil.getWordList(text);
        wordList.sort(new WordContainedLetterComparator(letter));
        return TaskUtil.getWordString(wordList);
    }

    private String getTask10(List<String> words) {
        logger.info("Task10 found. Start to perform.");
        StringBuilder wordsBuilder = new StringBuilder();
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, 1);
        }
        for (Word word : TaskUtil.getWordList(text)) {
            Integer numberOfWords = wordMap.get(word.getSentenceElement());
            if (numberOfWords != null) {
                wordMap.put(word.getSentenceElement(), numberOfWords + 1);
            }
        }
        List<Map.Entry<String, Integer>> sortedList = wordMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toList());

        for (Map.Entry<String, Integer> m : sortedList) {
            wordsBuilder.append(m.getKey()).append(" ");
        }
        return wordsBuilder.toString();
    }

    private String getTask11(char begin, char end) {
        logger.info("Task11 found. Start to perform.");
        List<Sentence> sentences = TaskUtil.getSentenceList(text);
        List<String> result = new ArrayList<>();
        for (Sentence sentence : sentences) {
            String sent = sentence.getTextElement();
            int b = sent.indexOf(begin);
            int e = sent.lastIndexOf(end);
            if (e != -1 && b != -1) {
                result.add(sent.substring(0, b) + sent.substring(e));
            } else {
                result.add(sent);
            }
        }
        StringBuilder resultBuilder = new StringBuilder();
        for (String s : result) {
            resultBuilder.append(s);
            resultBuilder.append(System.lineSeparator());
        }
        return resultBuilder.toString();
    }

    private String getTask12(int wordLength) {
        logger.info("Task12 found. Start to perform.");
        String consonants = "бвгджзйклмнпрстфхцчшщъьБВГДЖЗЙКЛМНПРСТФХЦЧШЩЪЬ";
        StringBuilder words = new StringBuilder();
        for (Word w : TaskUtil.getWordList(text)) {
            String word = w.getSentenceElement();
            if (!consonants.contains(((Character) word.charAt(0)).toString()) || word.length() != wordLength) {
                words.append(word).append(" ");
            }
        }
        return "".equals(words.toString()) ? "No words left" : words.toString();
    }

    private String getTask13(char letter) {
        logger.info("Task13 found. Start to perform.");
        List<Word> wordList = TaskUtil.getWordList(text);
        wordList.sort(new WordContainedLetterComparator(letter).reversed());
        return TaskUtil.getWordString(wordList);
    }

    private String getTask14() {
        logger.info("Task14 found. Start to perform.");
        return Collections.max(TaskUtil.findPalindrome(text), Comparator.comparing(String::length));
    }

    private String getTask15() {
        logger.info("Task15 found. Start to perform.");
        StringBuilder words = new StringBuilder();
        for (Word w : TaskUtil.getWordList(text)) {
            String word = w.getSentenceElement();
            words.append(word.replaceAll("[" + word.charAt(0) + word.charAt(word.length() - 1) + "]", "")).append(" ");
        }
        return words.toString();
    }

    private String getTask16(int sentenceNumber, int wordLength, String substring) {
        logger.info("Task16 found. Start to perform.");
        StringBuilder sentence = new StringBuilder();
        for (SentenceElement el : TaskUtil.getSentenceList(text).get(sentenceNumber).getSentenceElements()) {
            if (el.getSentenceElement().length() == wordLength) {
                sentence.append(substring);
            } else {
                sentence.append(el.getSentenceElement());
            }
            sentence.append(" ");
        }
        return sentence.toString();
    }
}
