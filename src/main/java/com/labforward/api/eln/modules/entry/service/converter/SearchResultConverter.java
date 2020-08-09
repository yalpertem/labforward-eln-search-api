package com.labforward.api.eln.modules.entry.service.converter;

import com.labforward.api.eln.modules.entry.entity.EntrySearchResult;
import com.labforward.api.eln.modules.text.entity.SearchResult;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Responsible for converting @{@link com.labforward.api.eln.modules.text.entity.SearchResult} to
 * the @{@link com.labforward.api.eln.modules.entry.entity.EntrySearchResult}
 */
@Component
public class SearchResultConverter {

    @Autowired
    private ModelMapper modelMapper;

    public EntrySearchResult convertToEntrySearchResult(SearchResult searchResult) {
        return modelMapper.map(searchResult, EntrySearchResult.class);
    }
}
