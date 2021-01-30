package by.epam.jwd.task04.text.service.parser;

import by.epam.jwd.task04.text.Text;
import by.epam.jwd.task04.text.impl.ProgrammingText;
import by.epam.jwd.task04.text.impl.textelement.TextElement;
import by.epam.jwd.task04.text.impl.textelement.impl.blockofcode.BlockOfCode;
import by.epam.jwd.task04.text.impl.textelement.impl.sentence.Sentence;
import by.epam.jwd.task04.text.impl.textelement.impl.sentence.sentenceelement.SentenceElement;
import by.epam.jwd.task04.text.impl.textelement.impl.sentence.sentenceelement.impl.punctuationmark.PunctuationMark;
import by.epam.jwd.task04.text.impl.textelement.impl.sentence.sentenceelement.impl.word.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {
    private final static Pattern BLOCK_OF_CODE = Pattern.compile("\\r\\n.*\\r\\n.*\\{.*(\\r\\n.+)*};\\r\\n");
    private final static Pattern SENTENCE = Pattern.compile("([А-аЯ-я\"]+[()\\s-\\n,\"]*)+[.:!?]+");
    private final static Pattern SENTENCE_ELEMENTS = Pattern.compile("([А-аЯ-я]+)|([,-.:!;?]+)");

    public TextParser() {

    }

    public static Text parseText(String text) {
        return new ProgrammingText(getTextElement(text));
    }

    private static List<SentenceElement> getSentenceElements(String sentence) {
        List<SentenceElement> sentenceElements = new ArrayList<>();
        Matcher sentenceElementMatcher = SENTENCE_ELEMENTS.matcher(sentence);
        while (sentenceElementMatcher.find()) {
            String sentenceElement = sentence.substring(sentenceElementMatcher.start(), sentenceElementMatcher.end());
            if (sentenceElement.matches("[,-.:!;?]+")) {
                sentenceElements.add(new PunctuationMark(sentenceElement));
            } else {
                sentenceElements.add(new Word(sentenceElement));
            }
        }
        return sentenceElements;
    }

    private static List<TextElement> getTextElement(String text) {
        Matcher blockMatcher = BLOCK_OF_CODE.matcher(text);
        List<TextElement> textElements = new ArrayList<>();
        int counter = 0;
        while (blockMatcher.find()) {
            String sentences = text.substring(counter, blockMatcher.start());
            Matcher sentenceMatcher = SENTENCE.matcher(sentences);
            while (sentenceMatcher.find()) {
                String sentence = sentences.substring(sentenceMatcher.start(), sentenceMatcher.end());
                List<SentenceElement> sentenceElements = getSentenceElements(sentence);
                textElements.add(new Sentence(sentenceElements));
            }
            textElements.add(new BlockOfCode(text.substring(blockMatcher.start(), blockMatcher.end())));
            counter = blockMatcher.end();
        }
        return textElements;
    }
}
