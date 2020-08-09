package com.labforward.api.eln.modules.entry.controller;

import com.labforward.api.eln.common.exception.NotFoundException;
import com.labforward.api.eln.modules.entry.entity.Entry;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EntryControllerGetTest extends EntryControllerBaseTest {

    @Test
    public void getEntries_RegularGetRequest_ReturnsOk() throws Exception {
        mockMvc.perform(get("/api/entries"))
                .andExpect(status().isOk());
    }

    @Test
    public void getEntries_EntriesExist_ReturnsEntries() throws Exception {
        Entry entry = new Entry(this.ENTRY_ID, this.ENTRY_TITLE, this.ENTRY_CONTENT);
        when(service.getEntries()).thenReturn(Arrays.asList(entry));

        mockMvc.perform(get("/api/entries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(this.ENTRY_ID)))
                .andExpect(jsonPath("$[0].title", is(this.ENTRY_TITLE)))
                .andExpect(jsonPath("$[0].content", is(this.ENTRY_CONTENT)));
    }

    @Test
    public void getEntry_EntryExists_ReturnsEntry() throws Exception {
        Entry entry = new Entry(this.ENTRY_ID, this.ENTRY_TITLE, this.ENTRY_CONTENT);
        when(service.getEntry(this.ENTRY_ID)).thenReturn(entry);

        mockMvc.perform(get("/api/entries/{id}", this.ENTRY_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(this.ENTRY_ID)))
                .andExpect(jsonPath("$.title", is(this.ENTRY_TITLE)))
                .andExpect(jsonPath("$.content", is(this.ENTRY_CONTENT)));
    }

    @Test
    public void getEntry_EntryDoesNotExist_ReturnsNotFound() throws Exception {
        when(service.getEntry(this.ENTRY_ID)).
                thenThrow(new NotFoundException());

        mockMvc.perform(get("/api/entries/{id}", this.ENTRY_ID))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(NotFoundException.MESSAGE)));
    }
}