package com.labforward.api.eln.modules.entry.controller;


import com.labforward.api.eln.common.TestHelpers;
import com.labforward.api.eln.common.exception.NotFoundException;
import com.labforward.api.eln.modules.entry.entity.Entry;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EntryControllerDeleteTest extends EntryControllerBaseTest {

    @Test
    public void deleteEntry_WithNonExistingId_ReturnsNotFound() throws Exception {
        Entry entry = new Entry(this.ENTRY_ID, this.ENTRY_TITLE, this.ENTRY_CONTENT);
        doThrow(new NotFoundException())
                .when(service).deleteEntry(anyString());

        mockMvc.perform(delete("/api/entries/{id}", this.ENTRY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestHelpers.convertToJsonByteArray(entry))
        )
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deleteEntry_WithValidId_ReturnsOk() throws Exception {
        Entry entry = new Entry(this.ENTRY_ID, this.ENTRY_TITLE, this.ENTRY_CONTENT);

        mockMvc.perform(delete("/api/entries/{id}", this.ENTRY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestHelpers.convertToJsonByteArray(entry))
        )
                .andExpect(status().isOk());
    }

}