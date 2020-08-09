package com.labforward.api.eln.modules.entry.dto;

import java.util.List;
import java.util.Objects;

public class EntrySearchResultDto {
    private List<EntrySearchResultItemDto> searchResultItems;

    public EntrySearchResultDto() {
    }

    public EntrySearchResultDto(List<EntrySearchResultItemDto> searchResultItems) {
        this.searchResultItems = searchResultItems;
    }

    public List<EntrySearchResultItemDto> getSearchResultItems() {
        return searchResultItems;
    }

    public void setSearchResultItems(List<EntrySearchResultItemDto> searchResultItems) {
        this.searchResultItems = searchResultItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntrySearchResultDto that = (EntrySearchResultDto) o;
        return searchResultItems.equals(that.searchResultItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchResultItems);
    }
}
