package by.epam.jwd.task04.text.impl.textelement.impl.sentence.sentenceelement.impl.word;

import by.epam.jwd.task04.text.impl.textelement.impl.sentence.sentenceelement.SentenceElement;

import java.io.Serializable;
import java.util.Objects;

public class Word extends SentenceElement  implements Serializable {
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(word, word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }

    @Override
    public String getSentenceElement() {
        return word;
    }
}
