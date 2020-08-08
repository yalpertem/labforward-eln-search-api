package com.labforward.api.eln.modules.entry.controller;

import com.labforward.api.eln.common.TestHelpers;
import com.labforward.api.eln.common.advisor.RestControllerExceptionAdvisor;
import com.labforward.api.eln.common.exception.ArgumentConflictException;
import com.labforward.api.eln.common.exception.NotFoundException;
import com.labforward.api.eln.modules.entry.entity.Entry;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EntryControllerUpdateTest extends EntryControllerBaseTest {

    @Test
    public void updateEntry_WithoutPathVariable_ReturnsMethodNotAllowed() throws Exception {
        Entry entry = new Entry(this.ENTRY_ID, this.ENTRY_CONTENT);

        mockMvc.perform(put("/api/entries/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestHelpers.convertToJsonByteArray(entry))
        )
                .andExpect(status().isMethodNotAllowed());
        verifyNoInteractions(service);
    }

    @Test
    public void updateEntry_WithNullTitle_ReturnsValidationErrorForTitle() throws Exception {
        Entry entry = new Entry(null, this.ENTRY_CONTENT);

        mockMvc.perform(put("/api/entries/{id}", this.ENTRY_ID)
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
    public void updateEntry_WithNullContent_ReturnsValidationErrorForTitle() throws Exception {
        Entry entry = new Entry(this.ENTRY_TITLE, null);

        mockMvc.perform(put("/api/entries/{id}", this.ENTRY_ID)
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
    public void updateEntry_WithNonExistingEntry_ReturnsNotFound() throws Exception {
        Entry entry = new Entry(this.ENTRY_ID, this.ENTRY_TITLE, this.ENTRY_CONTENT);
        when(service.updateEntry(anyString(), any(Entry.class)))
                .thenThrow(new NotFoundException());

        mockMvc.perform(put("/api/entries/{id}", this.ENTRY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestHelpers.convertToJsonByteArray(entry))
        )
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void updateEntry_EntryIdsDoesNotMatch_ReturnsUnprocessableEntity() throws Exception {
        Entry entry = new Entry(this.ENTRY_ID, this.ENTRY_TITLE, this.ENTRY_CONTENT);
        when(service.updateEntry(anyString(), any(Entry.class)))
                .thenThrow(new ArgumentConflictException());

        mockMvc.perform(put("/api/entries/{id}", this.ENTRY_ID_OTHER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestHelpers.convertToJsonByteArray(entry))
        )
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message",
                        is(ArgumentConflictException.MESSAGE)));
    }

    @Test
    public void updateEntry_WithValidEntry_ReturnsOkWithCreatedEntry() throws Exception {
        Entry entry = new Entry(this.ENTRY_ID, this.ENTRY_TITLE, this.ENTRY_CONTENT);
        when(service.updateEntry(anyString(), any(Entry.class))).thenReturn(entry);

        mockMvc.perform(put("/api/entries/{id}", this.ENTRY_ID)
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