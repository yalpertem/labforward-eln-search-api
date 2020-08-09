package com.labforward.api.eln.modules.entry.service;

import com.labforward.api.eln.common.exception.NotFoundException;
import com.labforward.api.eln.modules.entry.entity.Entry;
import com.labforward.api.eln.modules.entry.entity.EntrySearchResult;
import com.labforward.api.eln.modules.entry.service.converter.SearchResultConverter;
import com.labforward.api.eln.modules.text.entity.SearchResult;
import com.labforward.api.eln.modules.text.service.TextSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntrySearchServiceImpl implements EntrySearchService {

    @Autowired
    private EntryService entryService;

    @Autowired
    private TextSearchService textSearchService;

    @Autowired
    private SearchResultConverter converter;

    @Autowired
    public EntrySearchServiceImpl(EntryService entryService,
                                  TextSearchService textSearchService,
                                  SearchResultConverter converter) {
        this.entryService = entryService;
        this.textSearchService = textSearchService;
        this.converter = converter;
    }

    @Override
    public EntrySearchResult search(String entryId, String searchWord) {
        Entry entry = entryService.getEntry(entryId);
        if (entry == null) {
            throw new NotFoundException();
        }
        SearchResult searchResult = textSearchService.search(entry.getContent(), searchWord);
        return converter.convertToEntrySearchResult(searchResult);
    }
}
