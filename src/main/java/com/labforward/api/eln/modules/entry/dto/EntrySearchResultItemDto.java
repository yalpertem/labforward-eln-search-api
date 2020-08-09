package com.labforward.api.eln.modules.entry.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class EntrySearchResultItemDto {

    @NotEmpty
    private String word;

    @Min(value = 1)
    private int frequency;

    @Min(value = 0)
    private int distance;

    @NotNull
    private List<Integer> occurrences;

    public EntrySearchResultItemDto() {

    }

    public EntrySearchResultItemDto(String word, int distance, int frequency, List<Integer> occurrences) {
        this.word = word;
        this.distance = distance;
        this.frequency = frequency;
        this.occurrences = occurrences;
    }

    public EntrySearchResultItemDto(String word, int distance, List<Integer> occurrences) {
        this.word = word;
        this.distance = distance;
        this.frequency = 1;
        this.occurrences = occurrences;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<Integer> getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(List<Integer> occurrences) {
        this.occurrences = occurrences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntrySearchResultItemDto that = (EntrySearchResultItemDto) o;
        return frequency == that.frequency &&
                distance == that.distance &&
                word.equals(that.word) &&
                occurrences.equals(that.occurrences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, frequency, distance, occurrences);
    }
}
