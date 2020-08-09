package com.labforward.api.eln.modules.entry.controller;

import com.labforward.api.eln.modules.entry.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class EntryControllerBaseTest {

    protected final String ENTRY_ID = "EntryId";
    protected final String ENTRY_ID_OTHER = "EntryId2";
    protected final String ENTRY_TITLE = "EntryTitle";
    protected final String ENTRY_CONTENT = "EntryContent";

    protected final String NOT_EMPTY_VALIDATION_MESSAGE = "must not be empty";

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected EntryService service;
}
