package com.labforward.api.eln.modules.text.entity;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class StringPosition {

    @NotNull
    private String word;

    private int index;

    public StringPosition(String word) {
    }

    public StringPosition(String word, int index) {
        this.word = word;
        this.index = index;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringPosition that = (StringPosition) o;
        return index == that.index &&
                word.equals(that.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, index);
    }
}
