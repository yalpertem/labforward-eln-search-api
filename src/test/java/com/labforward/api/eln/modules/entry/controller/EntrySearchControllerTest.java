package com.labforward.api.eln.modules.entry.controller;

import com.labforward.api.eln.common.exception.NotFoundException;
import com.labforward.api.eln.modules.entry.entity.EntrySearchResult;
import com.labforward.api.eln.modules.entry.entity.EntrySearchResultItem;
import com.labforward.api.eln.modules.entry.service.EntrySearchService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EntrySearchControllerTest {

    protected final String ENTRY_ID = "EntryId";

    protected final String SEARCH_WORD = "word";
    protected final int SEARCH_FREQUENCY = 2;
    protected final int SEARCH_DISTANCE = 0;
    protected final List<Integer> SEARCH_OCCURRENCES = Arrays.asList(4, 10);

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected EntrySearchService service;

    @Test
    public void search_IdDoesNotExist_ReturnsNotFound() throws Exception {
        when(service.search(anyString(), anyString())).
                thenThrow(new NotFoundException());

        mockMvc.perform(get("/api/entries/{id}/search/{word}", this.ENTRY_ID, SEARCH_WORD))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(NotFoundException.MESSAGE)));
    }

    @Test
    public void search_WordIsNotPassed_ReturnsNotFound() throws Exception {
        mockMvc.perform(get("/api/entries/{id}/search", this.ENTRY_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getEntries_NoSearchResultFound_ReturnsEmptyResponse() throws Exception {
        EntrySearchResult result = new EntrySearchResult(new ArrayList<>());
        when(service.search(anyString(), anyString())).thenReturn(result);

        mockMvc.perform(get("/api/entries/{id}/search/{word}", this.ENTRY_ID, SEARCH_WORD))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.searchResultItems", is(Matchers.empty())));
    }

    @Test
    public void getEntries_SearchResultFound_ReturnsSearchResult() throws Exception {
        List<EntrySearchResultItem> searchResultItems = Arrays.asList(new EntrySearchResultItem(
                this.SEARCH_WORD, this.SEARCH_DISTANCE, this.SEARCH_FREQUENCY, this.SEARCH_OCCURRENCES));
        EntrySearchResult result = new EntrySearchResult(searchResultItems);

        when(service.search(anyString(), anyString())).thenReturn(result);

        mockMvc.perform(get("/api/entries/{id}/search/{word}", this.ENTRY_ID, SEARCH_WORD))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.searchResultItems", hasSize(result.getSearchResultItems().size())))
                .andExpect(jsonPath("$.searchResultItems[0].word", is(this.SEARCH_WORD)))
                .andExpect(jsonPath("$.searchResultItems[0].distance", is(this.SEARCH_DISTANCE)))
                .andExpect(jsonPath("$.searchResultItems[0].frequency", is(this.SEARCH_FREQUENCY)))
                .andExpect(jsonPath("$.searchResultItems[0].occurrences", hasSize(this.SEARCH_OCCURRENCES.size())))
                .andExpect(jsonPath("$.searchResultItems[0].occurrences[*]", is(oneOf(this.SEARCH_OCCURRENCES))));
    }
}
