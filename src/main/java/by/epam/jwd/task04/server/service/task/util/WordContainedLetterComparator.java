package by.epam.jwd.task04.server.service.task.util;

import by.epam.jwd.task04.text.impl.textelement.impl.sentence.sentenceelement.impl.word.Word;

import java.util.Comparator;

public class WordContainedLetterComparator implements Comparator<Word> {

    private final char letter;

    public WordContainedLetterComparator(char letter) {
        this.letter = letter;
    }

    @Override
    public int compare(Word o1, Word o2) {
        int counter = 0;
        for (char c : o1.getSentenceElement().toCharArray()) {
            if (c == letter) {
                counter++;
            }
        }
        for (char c : o2.getSentenceElement().toCharArray()) {
            if (c == letter) {
                counter--;
            }
        }
        if (counter == 0) {
            return o1.getSentenceElement().compareTo(o2.getSentenceElement());
        }
        return counter;
    }
}
