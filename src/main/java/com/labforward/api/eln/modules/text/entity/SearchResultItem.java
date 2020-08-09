package com.labforward.api.eln.modules.text.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class SearchResultItem {

    @NotEmpty
    private String item;

    @Min(value = 1)
    private int frequency;

    @Min(value = 0)
    private int distance;

    @NotNull
    private List<Integer> occurrences;

    public SearchResultItem() {

    }

    public SearchResultItem(String item, int distance, int frequency, List<Integer> occurrences) {
        this.item = item;
        this.distance = distance;
        this.frequency = frequency;
        this.occurrences = occurrences;
    }

    public SearchResultItem(String item, int distance, List<Integer> occurrences) {
        this.item = item;
        this.distance = distance;
        this.frequency = 1;
        this.occurrences = occurrences;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
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
        SearchResultItem that = (SearchResultItem) o;
        return frequency == that.frequency &&
                distance == that.distance &&
                item.equals(that.item) &&
                occurrences.equals(that.occurrences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, frequency, distance, occurrences);
    }
}