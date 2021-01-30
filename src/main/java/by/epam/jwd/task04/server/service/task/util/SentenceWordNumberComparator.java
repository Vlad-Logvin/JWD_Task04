package by.epam.jwd.task04.server.service.task.util;

import by.epam.jwd.task04.text.impl.textelement.impl.sentence.Sentence;
import by.epam.jwd.task04.text.impl.textelement.impl.sentence.sentenceelement.SentenceElement;
import by.epam.jwd.task04.text.impl.textelement.impl.sentence.sentenceelement.impl.word.Word;

import java.util.Comparator;

public class SentenceWordNumberComparator implements Comparator<Sentence> {

    @Override
    public int compare(Sentence o1, Sentence o2) {
        int compareSentences = 0;
        for (SentenceElement el : o1.getSentenceElements()) {
            if (el instanceof Word) {
                compareSentences++;
            }
        }
        for (SentenceElement el : o2.getSentenceElements()) {
            if (el instanceof Word) {
                compareSentences--;
            }
        }
        return compareSentences;
    }
}
