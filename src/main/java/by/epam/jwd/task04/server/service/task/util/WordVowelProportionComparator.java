package by.epam.jwd.task04.server.service.task.util;

import by.epam.jwd.task04.text.impl.textelement.impl.sentence.sentenceelement.impl.word.Word;

import java.util.Comparator;

public class WordVowelProportionComparator implements Comparator<Word> {
    public String vowels = "аэуеоияеыюАЭУЕОИЫЯЭЮ";

    @Override
    public int compare(Word o1, Word o2) {
        int counter1 = 0;
        for (Character c : o1.getSentenceElement().toCharArray()) {
            if (vowels.contains(c.toString())) {
                counter1++;
            }
        }
        int counter2 = 0;
        for (Character c : o2.getSentenceElement().toCharArray()) {
            if (vowels.contains(c.toString())) {
                counter2++;
            }
        }
        return Double.compare((double) counter1 / (double) o1.getSentenceElement().length(), (double) counter2 / (double) o2.getSentenceElement().length());
    }
}
