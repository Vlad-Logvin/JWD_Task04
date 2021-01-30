package by.epam.jwd.task04.server.service.task.util;

import by.epam.jwd.task04.text.impl.ProgrammingText;
import by.epam.jwd.task04.text.impl.textelement.TextElement;
import by.epam.jwd.task04.text.impl.textelement.impl.sentence.Sentence;
import by.epam.jwd.task04.text.impl.textelement.impl.sentence.sentenceelement.SentenceElement;
import by.epam.jwd.task04.text.impl.textelement.impl.sentence.sentenceelement.impl.word.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskUtil {

    public static List<Word> getWordList(ProgrammingText text) {
        List<Word> words = new ArrayList<>();
        for (TextElement textEl : text.getTextElements()) {
            if (textEl instanceof Sentence) {
                words.addAll(getWordList((Sentence) textEl));
            }
        }
        return words;
    }

    public static List<Word> getWordList(Sentence sentence) {
        List<Word> words = new ArrayList<>();
        for (SentenceElement el : sentence.getSentenceElements()) {
            if (el instanceof Word) {
                words.add((Word) el);
            }
        }
        return words;
    }

    public static List<Sentence> getSentenceList(ProgrammingText text) {
        List<Sentence> sentences = new ArrayList<>();
        for (TextElement textEl : text.getTextElements()) {
            if (textEl instanceof Sentence) {
                sentences.add((Sentence) textEl);
            }
        }
        return sentences;
    }

    public static Map<Word, Integer> findSameWordsInDifferentSentences(ProgrammingText text) {
        List<Sentence> sentences = TaskUtil.getSentenceList(text);
        Map<Word, Integer> words = new HashMap<>();
        Integer numberOfWords;
        for (Sentence sentence : sentences) {
            Map<Word, Integer> sameWords = new HashMap<>();
            for (SentenceElement el : sentence.getSentenceElements()) {
                if (el instanceof Word) {
                    Integer sameWordsNumber = sameWords.get(el);
                    if (sameWordsNumber != null) {
                        break;
                    }
                    sameWords.put((Word) el, 1);
                    numberOfWords = words.get(el);
                    words.put((Word) el, numberOfWords == null ? 1 : numberOfWords + 1);
                }
            }
        }
        return words;
    }

    public static String getSentenceString(List<Sentence> sentences) {
        StringBuilder allText = new StringBuilder();
        for (Sentence sentence : sentences) {
            allText.append(sentence.getTextElement());
            allText.append(System.lineSeparator());
        }
        return allText.toString();
    }

    public static Map<Word, Integer> putWordsToMap(Sentence sentence) {
        Map<Word, Integer> wordMap = new HashMap<>();
        for (SentenceElement el : sentence.getSentenceElements()) {
            if (el instanceof Word) {
                wordMap.put((Word) el, 1);
            }
        }
        return wordMap;
    }

    public static String getWordString(List<Word> wordList) {
        StringBuilder words = new StringBuilder();
        for (Word word : wordList) {
            words.append(word.getSentenceElement()).append(" ");
        }
        return words.toString();
    }

    public static List<SentenceElement> swapFirstAndLastWordInSentence(List<SentenceElement> sentenceElements) {
        SentenceElement swap = sentenceElements.get(0);
        sentenceElements.set(0, sentenceElements.get(sentenceElements.size() - 2));
        sentenceElements.set(sentenceElements.size() - 2, swap);
        return sentenceElements;
    }

    public static List<String> findPalindrome(ProgrammingText text) {
        String input = text.getFileText();
        String formattedInput = "@" + input + "#";
        char[] inputCharArr = formattedInput.toCharArray();
        int[][] radius = new int[2][input.length() + 1];
        List<String> palindromes = new ArrayList<>();
        int max;
        for (int j = 0; j <= 1; j++) {
            radius[j][0] = max = 0;
            int i = 1;
            while (i <= input.length()) {
                palindromes.add(Character.toString(inputCharArr[i]));
                while (inputCharArr[i - max - 1] == inputCharArr[i + j + max])
                    max++;
                radius[j][i] = max;
                int k = 1;
                while ((radius[j][i - k] != max - k) && (k < max)) {
                    radius[j][i + k] = Math.min(radius[j][i - k], max - k);
                    k++;
                }
                max = Math.max(max - k, 0);
                i += k;
            }
        }
        for (int i = 1; i <= input.length(); i++) {
            for (int j = 0; j <= 1; j++) {
                for (max = radius[j][i]; max > 0; max--) {
                    String substring = input.substring(i - max - 1, max + j + i - 1);
                    if (substring.matches("[^\\s?!.:,]+")) {
                        palindromes.add(substring);
                    }
                }
            }
        }
        return palindromes;
    }
}
