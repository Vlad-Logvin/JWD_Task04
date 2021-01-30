package by.epam.jwd.task04.text.impl.textelement.impl.sentence.sentenceelement.impl.punctuationmark;

import by.epam.jwd.task04.text.impl.textelement.impl.sentence.sentenceelement.SentenceElement;

public class PunctuationMark extends SentenceElement {
    private String mark;

    public PunctuationMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String getSentenceElement() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
