package com.labforward.api.eln.modules.entry.service;

import com.labforward.api.eln.common.exception.ArgumentConflictException;
import com.labforward.api.eln.common.exception.NotFoundException;
import com.labforward.api.eln.modules.entry.entity.Entry;
import com.labforward.api.eln.modules.entry.repository.EntryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EntryServiceImplTest {

    protected static final String ENTRY_ID = "EntryId";
    protected static final String ENTRY_ID_OTHER = "EntryId2";
    protected static final String ENTRY_TITLE = "EntryTitle";
    protected static final String ENTRY_TITLE_NEW = "EntryTitleNew";
    protected static final String ENTRY_CONTENT = "EntryContent";

    private EntryService service;

    private EntryRepository repositoryMock;

    @BeforeEach
    public void setUp() {
        this.repositoryMock = mock(EntryRepository.class);
        this.service = new EntryServiceImpl(repositoryMock);
    }

    @Test
    void getEntries_ReturnsListOfEntries() {
        List<Entry> expected = new ArrayList<>();
        when(repositoryMock.getEntries()).thenReturn(expected);

        List<Entry> actual = service.getEntries();

        assertSame(expected, actual);
    }

    @Test
    void getEntry_EntryExists_ReturnsEntry() {
        Entry expected = new Entry(this.ENTRY_ID, this.ENTRY_TITLE, this.ENTRY_CONTENT);
        when(repositoryMock.getEntry(anyString())).thenReturn(expected);

        Entry actual = service.getEntry(this.ENTRY_ID);

        assertSame(expected, actual);
    }

    @Test()
    void getEntry_IdDoesNotExist_ThrowsNotFoundException() {
        List<Entry> emptyList = new ArrayList<>();
        when(repositoryMock.getEntries()).thenReturn(emptyList);

        assertThrows(NotFoundException.class,
                () -> service.getEntry(this.ENTRY_ID));
    }

    @Test
    void createEntry_ValidEntry_ReturnsCreatedEntry() {
        Entry expected = new Entry(this.ENTRY_ID, this.ENTRY_TITLE, this.ENTRY_CONTENT);
        when(repositoryMock.createEntry(any(Entry.class))).thenReturn(expected);

        Entry actual = service.createEntry(expected);

        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getContent(), actual.getContent());
    }

    @Test
    public void updateEntry_EntryIdsDoesNotMatch_ThrowsArgumentConflictException() {
        Entry entry = new Entry(this.ENTRY_ID, this.ENTRY_TITLE, this.ENTRY_CONTENT);

        assertThrows(ArgumentConflictException.class,
                () -> service.updateEntry(this.ENTRY_ID_OTHER, entry));
    }

    @Test
    void updateEntry_EntryExists_ReturnsUpdatedEntry() {
        Entry previous = new Entry(this.ENTRY_ID, this.ENTRY_TITLE, this.ENTRY_CONTENT);
        Entry expected = new Entry(this.ENTRY_ID, this.ENTRY_TITLE_NEW, this.ENTRY_CONTENT);
        when(repositoryMock.getEntry(anyString())).thenReturn(previous);
        when(repositoryMock.updateEntry(anyString(), any(Entry.class))).thenReturn(expected);

        Entry actual = service.updateEntry(this.ENTRY_ID, expected);

        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getContent(), actual.getContent());
    }

    @Test
    void updateEntry_EntryDoesNotExist_ThrowsNotFoundException() {
        Entry entry = new Entry(this.ENTRY_ID, this.ENTRY_TITLE, this.ENTRY_CONTENT);
        when(repositoryMock.getEntry(anyString())).thenReturn(null);

        assertThrows(NotFoundException.class,
                () -> service.updateEntry(this.ENTRY_ID, entry));
    }

    @Test
    void deleteEntry_EntryDoesNotExist_ThrowsNotFoundException() {
        when(repositoryMock.getEntry(anyString())).thenReturn(null);

        assertThrows(NotFoundException.class,
                () -> service.deleteEntry(this.ENTRY_ID));
    }
}