package com.labforward.api.eln.modules.text.entity;

import java.util.List;

public class SearchResult {

    private List<SearchResultItem> searchResultItems;

    public SearchResult() {
    }

    public SearchResult(List<SearchResultItem> searchResultItems) {
        this.searchResultItems = searchResultItems;
    }

    public List<SearchResultItem> getSearchResultItems() {
        return searchResultItems;
    }

    public void setSearchResultItems(List<SearchResultItem> searchResultItems) {
        this.searchResultItems = searchResultItems;
    }
}