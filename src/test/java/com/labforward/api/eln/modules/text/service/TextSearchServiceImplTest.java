package com.labforward.api.eln.modules.text.service;


import com.labforward.api.eln.modules.text.entity.SearchResult;
import com.labforward.api.eln.modules.text.entity.SearchResultItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TextSearchServiceImplTest {

    @Autowired
    private TextParseService parseService;

    private TextSimilarityService similarityServiceMock;

    private TextSearchService searchService;

    @BeforeEach
    public void setUp() {
        this.similarityServiceMock = mock(TextSimilarityService.class);
        this.searchService = new TextSearchServiceImpl(similarityServiceMock, parseService);
    }

    @Test
    public void search_TextIsNull_ThrowsIllegalArgumentException() {
        String searchWord = "sit";

        assertThrows(IllegalArgumentException.class,
                () -> searchService.search(null, searchWord));
    }

    @Test
    public void search_SearchWordIsNull_ThrowsIllegalArgumentException() {
        String text = "Lorem ipsum dolor";

        assertThrows(IllegalArgumentException.class,
                () -> searchService.search(text, null));
    }

    @Test
    public void search_NoSimilarWordsExist_ReturnsEmptyList() {
        String text = "Lorem Lorem Lorem";
        String searchWord = "Lorem";
        when(similarityServiceMock.getDistance(anyString(), anyString())).thenReturn(-1);

        SearchResult searchResult = searchService.search(text, searchWord);

        assertNotNull(searchResult);
        assertEquals(0, searchResult.getSearchResultItems().size());
    }

    @Test
    public void search_SameWordExists_ReturnsFrequencyList() {
        String text = "Lorem Lorem ipsum dolor";
        String searchWord = "Lorem";
        when(similarityServiceMock.getDistance(eq("Lorem"), eq(searchWord))).thenReturn(0);
        when(similarityServiceMock.getDistance(eq("ipsum"), eq(searchWord))).thenReturn(-1);
        when(similarityServiceMock.getDistance(eq("dolor"), eq(searchWord))).thenReturn(-1);

        SearchResult searchResult = searchService.search(text, searchWord);

        assertNotNull(searchResult);
        assertEquals(1, searchResult.getSearchResultItems().size());
        SearchResultItem searchResultItem = searchResult.getSearchResultItems().get(0);
        assertEquals(0, searchResultItem.getDistance());
        assertEquals(2, searchResultItem.getFrequency());
        assertEquals("Lorem", searchResultItem.getItem());
    }

    @Test
    public void search_SimilarWordExists_ReturnsFrequencyList() {
        String text = "Lorem Lorem ipsum dolor";
        String searchWord = "Lore";
        when(similarityServiceMock.getDistance(eq("Lorem"), eq(searchWord))).thenReturn(1);
        when(similarityServiceMock.getDistance(eq("ipsum"), eq(searchWord))).thenReturn(-1);
        when(similarityServiceMock.getDistance(eq("dolor"), eq(searchWord))).thenReturn(-1);

        SearchResult searchResult = searchService.search(text, searchWord);

        assertNotNull(searchResult);
        assertEquals(1, searchResult.getSearchResultItems().size());
        SearchResultItem searchResultItem = searchResult.getSearchResultItems().get(0);
        assertEquals(1, searchResultItem.getDistance());
        assertEquals(2, searchResultItem.getFrequency());
        assertEquals("Lorem", searchResultItem.getItem());
    }

    @Test
    public void search_BothSimilarAndSameWordsExist_ReturnsFrequencyList() {
        String text = "Lorem Lore ipsum dolor";
        String searchWord = "Lorem";
        when(similarityServiceMock.getDistance(eq("Lorem"), eq(searchWord))).thenReturn(0);
        when(similarityServiceMock.getDistance(eq("Lore"), eq(searchWord))).thenReturn(1);
        when(similarityServiceMock.getDistance(eq("ipsum"), eq(searchWord))).thenReturn(-1);
        when(similarityServiceMock.getDistance(eq("dolor"), eq(searchWord))).thenReturn(-1);

        SearchResult searchResult = searchService.search(text, searchWord);
        List<SearchResultItem> expected = Arrays.asList(
                new SearchResultItem("Lorem", 0, 1, Arrays.asList(0)),
                new SearchResultItem("Lore", 1, 1, Arrays.asList(6))
        );
        assertNotNull(searchResult);
        assertEquals(2, searchResult.getSearchResultItems().size());
        assertThat(expected, containsInAnyOrder(searchResult.getSearchResultItems().toArray()));
    }
}