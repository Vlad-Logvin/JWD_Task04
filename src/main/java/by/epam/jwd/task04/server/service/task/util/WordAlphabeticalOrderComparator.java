package by.epam.jwd.task04.server.service.task.util;

import by.epam.jwd.task04.text.impl.textelement.impl.sentence.sentenceelement.impl.word.Word;

import java.util.Comparator;

public class WordAlphabeticalOrderComparator  implements Comparator<Word> {
    @Override
    public int compare(Word o1, Word o2) {
        return o1.getSentenceElement().compareTo(o2.getSentenceElement());
    }
}
