package com.labforward.api.eln.modules.entry.controller;


import com.labforward.api.eln.common.TestHelpers;
import com.labforward.api.eln.common.advisor.RestControllerExceptionAdvisor;
import com.labforward.api.eln.modules.entry.entity.Entry;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class EntryControllerCreateTest extends EntryControllerBaseTest {

    @Test
    public void createEntry_WithNullTitle_ReturnsValidationErrorForTitle() throws Exception {
        Entry entry = new Entry(null, this.ENTRY_CONTENT);

        mockMvc.perform(post("/api/entries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestHelpers.convertToJsonByteArray(entry))
        )
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message",
                        is(RestControllerExceptionAdvisor.VALIDATION_ERROR_MESSAGE)))
                .andExpect(jsonPath("$.validationErrors", hasSize(1)))
                .andExpect(jsonPath("$.validationErrors[0].field", is("title")))
                .andExpect(jsonPath("$.validationErrors[0].message",
                        Matchers.is(this.NOT_EMPTY_VALIDATION_MESSAGE)));
        verifyNoInteractions(service);
    }

    @Test
    public void createEntry_WithNullContent_ReturnsValidationErrorForTitle() throws Exception {
        Entry entry = new Entry(this.ENTRY_TITLE, null);

        mockMvc.perform(post("/api/entries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestHelpers.convertToJsonByteArray(entry))
        )
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message",
                        is(RestControllerExceptionAdvisor.VALIDATION_ERROR_MESSAGE)))
                .andExpect(jsonPath("$.validationErrors", hasSize(1)))
                .andExpect(jsonPath("$.validationErrors[0].field", is("content")))
                .andExpect(jsonPath("$.validationErrors[0].message",
                        Matchers.is(this.NOT_EMPTY_VALIDATION_MESSAGE)));
        verifyNoInteractions(service);
    }

    @Test
    public void createEntry_WithValidEntry_ReturnsOkWithCreatedEntry() throws Exception {
        Entry entry = new Entry(this.ENTRY_ID, this.ENTRY_TITLE, this.ENTRY_CONTENT);
        when(service.createEntry(any(Entry.class))).thenReturn(entry);

        mockMvc.perform(post("/api/entries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestHelpers.convertToJsonByteArray(entry))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.is(this.ENTRY_ID)))
                .andExpect(jsonPath("$.title", Matchers.is(this.ENTRY_TITLE)))
                .andExpect(jsonPath("$.content", Matchers.is(this.ENTRY_CONTENT)));
    }
}