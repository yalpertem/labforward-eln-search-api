package com.labforward.api.eln.modules.entry.service;

import com.labforward.api.eln.modules.entry.entity.EntrySearchResult;

public interface EntrySearchService {

    EntrySearchResult search(String entryId, String searchWord);

}