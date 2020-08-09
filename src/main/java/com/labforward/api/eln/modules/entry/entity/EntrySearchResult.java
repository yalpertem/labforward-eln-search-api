package com.labforward.api.eln.modules.entry.entity;

import java.util.List;
import java.util.Objects;

public class EntrySearchResult {
    private List<EntrySearchResultItem> searchResultItems;

    public EntrySearchResult() {
    }

    public EntrySearchResult(List<EntrySearchResultItem> searchResultItems) {
        this.searchResultItems = searchResultItems;
    }

    public List<EntrySearchResultItem> getSearchResultItems() {
        return searchResultItems;
    }

    public void setSearchResultItems(List<EntrySearchResultItem> searchResultItems) {
        this.searchResultItems = searchResultItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntrySearchResult that = (EntrySearchResult) o;
        return searchResultItems.equals(that.searchResultItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchResultItems);
    }
}
