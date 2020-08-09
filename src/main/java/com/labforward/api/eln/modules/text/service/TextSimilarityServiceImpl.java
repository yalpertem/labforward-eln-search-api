package com.labforward.api.eln.modules.text.service;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;

@Service
public class TextSimilarityServiceImpl implements TextSimilarityService {

    private final int MAX_LEVENSHTEIN_DISTANCE = 1;

    /**
     * Returns the Levenshtein distance between two strings using
     * Apache Commons Text library
     *
     * @param left
     * @param right
     * @return
     */
    @Override
    public int getDistance(String left, String right) {
        LevenshteinDistance levenshteinDistance =
                new LevenshteinDistance(this.MAX_LEVENSHTEIN_DISTANCE);
        int distance = levenshteinDistance.apply(left, right);
        return distance;
    }
}
