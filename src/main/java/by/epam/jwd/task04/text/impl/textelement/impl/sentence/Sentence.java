package by.epam.jwd.task04.text.impl.textelement.impl.sentence;

import by.epam.jwd.task04.text.impl.textelement.TextElement;
import by.epam.jwd.task04.text.impl.textelement.impl.sentence.sentenceelement.SentenceElement;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Sentence extends TextElement  implements Serializable {
    private List<SentenceElement> sentenceElements;

    public Sentence(List<SentenceElement> sentenceElements) {
        this.sentenceElements = sentenceElements;
    }

    public List<SentenceElement> getSentenceElements() {
        return sentenceElements;
    }

    public void setSentenceElements(List<SentenceElement> sentenceElements) {
        this.sentenceElements = sentenceElements;
    }

    public String getTextElement() {
        StringBuilder sentence = new StringBuilder();
        for (SentenceElement sentenceEl : sentenceElements) {
            sentence.append(sentenceEl.getSentenceElement()).append(" ");
            if(sentenceEl.getSentenceElement().matches("[.!?]+")){
                sentence.append(System.lineSeparator());
            }
        }
        return sentence.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(sentenceElements, sentence.sentenceElements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sentenceElements);
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "sentenceElements=" + sentenceElements +
                '}';
    }
}
