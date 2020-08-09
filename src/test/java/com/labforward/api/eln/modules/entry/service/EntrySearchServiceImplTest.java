package com.labforward.api.eln.modules.entry.service;

import com.labforward.api.eln.common.exception.NotFoundException;
import com.labforward.api.eln.modules.entry.service.converter.SearchResultConverter;
import com.labforward.api.eln.modules.text.service.TextSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EntrySearchServiceImplTest {

    protected final String ENTRY_ID = "EntryId";
    protected final String SEARCH_WORD = "Lorem";

    private EntrySearchService service;

    private EntryService entryServiceMock;

    private TextSearchService textSearchServiceMock;

    @BeforeEach
    public void setUp() {
        this.entryServiceMock = mock(EntryService.class);
        this.textSearchServiceMock = mock(TextSearchService.class);
        this.service = new EntrySearchServiceImpl(entryServiceMock,
                textSearchServiceMock, new SearchResultConverter());
    }

    @Test
    public void search_SearchedEntryNotFound_ThrowsNotFoundException() {
        when(entryServiceMock.getEntry(eq(this.ENTRY_ID))).thenReturn(null);

        assertThrows(NotFoundException.class,
                () -> service.search(this.ENTRY_ID, this.SEARCH_WORD));

    }

}