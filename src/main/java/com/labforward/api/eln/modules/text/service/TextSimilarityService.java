package com.labforward.api.eln.modules.text.service;

/**
 * Responsible of text similarity calculations
 */
public interface TextSimilarityService {

    /**
     * Returns the Levenshtein Distance between two @String items
     *
     * @param left
     * @param right
     * @return Levenshtein Distance
     */
    int getDistance(String left, String right);
}
