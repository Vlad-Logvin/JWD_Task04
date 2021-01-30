package by.epam.jwd.task04.text.impl;

import by.epam.jwd.task04.text.Text;
import by.epam.jwd.task04.text.impl.textelement.TextElement;

import java.util.List;

public class ProgrammingText extends Text {

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
}
