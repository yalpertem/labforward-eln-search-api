package com.labforward.api.eln.modules.text.service;

import com.labforward.api.eln.modules.text.entity.SearchResult;

/**
 * Responsible of text search operations
 */
public interface TextSearchService {

    /**
     * Returns @{@link SearchResult} containing the occurrences of {@code word} and similar words in {@code text}
     *
     * @param text the container
     * @param word to be searched
     * @return
     */
    SearchResult search(String text, String word);
}
