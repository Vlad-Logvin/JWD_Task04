package by.epam.jwd.task04.text.impl.textelement.impl.sentence.sentenceelement.impl.punctuationmark;

import by.epam.jwd.task04.text.impl.textelement.impl.sentence.sentenceelement.SentenceElement;

import java.io.Serializable;
import java.util.Objects;

public class PunctuationMark extends SentenceElement implements Serializable {
    private String mark;

    public PunctuationMark(String mark) {
        this.mark = mark;
    }

    public PunctuationMark(){}
    
    @Override
    public String getSentenceElement() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "PunctuationMark{" +
                "mark='" + mark + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PunctuationMark that = (PunctuationMark) o;
        return Objects.equals(mark, that.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark);
    }
}
