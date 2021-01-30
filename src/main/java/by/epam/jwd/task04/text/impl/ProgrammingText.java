package by.epam.jwd.task04.text.impl;

import by.epam.jwd.task04.text.Text;
import by.epam.jwd.task04.text.impl.textelement.TextElement;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ProgrammingText extends Text  implements Serializable {

    private List<TextElement> textElements;

    public ProgrammingText(List<TextElement> textElements) {
        this.textElements = textElements;
    }

    public List<TextElement> getTextElements() {
        return textElements;
    }

    public void setTextElements(List<TextElement> textElements) {
        this.textElements = textElements;
    }

    @Override
    public String getFileText() {
        String text = "";
        for(TextElement txtEl : textElements){
            text += txtEl.getTextElement();
        }
        return text;
    }

    public ProgrammingText() {
    }

    @Override
    public String toString() {
        return "ProgrammingText{" +
                "textElements=" + textElements +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammingText that = (ProgrammingText) o;
        return Objects.equals(textElements, that.textElements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textElements);
    }
}
