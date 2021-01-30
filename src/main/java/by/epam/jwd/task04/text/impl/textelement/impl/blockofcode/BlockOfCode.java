package by.epam.jwd.task04.text.impl.textelement.impl.blockofcode;

import by.epam.jwd.task04.text.impl.textelement.TextElement;

import java.util.Objects;

public class BlockOfCode extends TextElement {
    private String blockOfCode;

    public BlockOfCode(String blockOfCode) {
        this.blockOfCode = blockOfCode;
    }

    public String getBlockOfCode() {
        return blockOfCode;
    }

    public void setBlockOfCode(String blockOfCode) {
        this.blockOfCode = blockOfCode;
    }

    @Override
    public String getTextElement() {
        return blockOfCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockOfCode that = (BlockOfCode) o;
        return Objects.equals(blockOfCode, that.blockOfCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockOfCode);
    }

    @Override
    public String toString() {
        return "BlockOfCode{" +
                "blockOfCode='" + blockOfCode + '\'' +
                '}';
    }
}
