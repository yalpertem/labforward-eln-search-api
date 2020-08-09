package com.labforward.api.eln.modules.text.service;

import com.labforward.api.eln.modules.text.entity.SearchResult;
import com.labforward.api.eln.modules.text.entity.SearchResultItem;
import com.labforward.api.eln.modules.text.entity.StringPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class TextSearchServiceImpl implements TextSearchService {

    private TextParseService parseService;

    private TextSimilarityService similarityService;

    @Autowired
    public TextSearchServiceImpl(TextSimilarityService similarityService,
                                 TextParseService parseService) {
        this.similarityService = similarityService;
        this.parseService = parseService;
    }

    @Override
    public SearchResult search(String text, String searchWord) {
        if (text == null || searchWord == null) {
            throw new IllegalArgumentException();
        }

        List<StringPosition> positions = parseService.getWords(text);
        Map<String, SearchResultItem> frequencyMap = new HashMap<>();

        for (StringPosition stringPosition : positions) {
            int distance = similarityService.getDistance(
                    stringPosition.getWord(), searchWord);
            if (distance >= 0) {
                ProcessFrequency(frequencyMap, stringPosition, distance);
            }
        }
        SearchResult searchResult = new SearchResult(
                new ArrayList<>(frequencyMap.values()));

        return searchResult;
    }

    private void ProcessFrequency(Map<String, SearchResultItem> map,
                                  StringPosition stringPosition, int distance) {

        String word = stringPosition.getWord();
        if (map.containsKey(word)) {
            IncreaseFrequencyCountInMap(map, stringPosition, word);
        } else {
            InsertToMap(map, stringPosition, word, distance);
        }
    }

    private void IncreaseFrequencyCountInMap(Map<String, SearchResultItem> map,
                                             StringPosition stringPosition, String word) {
        SearchResultItem item = map.get(word);
        item.setFrequency(item.getFrequency() + 1);
        item.getOccurrences().add(stringPosition.getIndex());
        map.put(word, item);
    }


    private void InsertToMap(Map<String, SearchResultItem> map,
                             StringPosition stringPosition, String word, int distance) {
        int position = stringPosition.getIndex();
        List<Integer> occurrences = new ArrayList<>();
        occurrences.add(position);
        SearchResultItem item = new SearchResultItem(word, distance, occurrences);
        map.put(word, item);
    }
}
