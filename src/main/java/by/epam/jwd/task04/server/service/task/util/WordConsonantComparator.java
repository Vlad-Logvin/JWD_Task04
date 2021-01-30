package by.epam.jwd.task04.server.service.task.util;

import by.epam.jwd.task04.text.impl.textelement.impl.sentence.sentenceelement.impl.word.Word;

import java.util.Comparator;

public class WordConsonantComparator implements Comparator<Word> {
    public String consonants = "бвгджзйклмнпрстфхцчшщъьБВГДЖЗЙКЛМНПРСТФХЦЧШЩЪЬ";

    @Override
    public int compare(Word o1, Word o2) {
        Character consonant1 = null;
        for (Character c : o1.getSentenceElement().toCharArray()) {
            if (consonants.contains(c.toString())) {
                consonant1 = c;
                break;
            }
        }
        Character consonant2 = null;
        for (Character c : o2.getSentenceElement().toCharArray()) {
            if (consonants.contains(c.toString())) {
                consonant2 = c;
                break;
            }
        }
        if (consonant1 == null && consonant2 == null) {
            return 0;
        } else if (null == consonant2) {
            return 1;
        } else if (null == consonant1) {
            return -1;
        } else {
            return consonant1.compareTo(consonant2);
        }
    }
}