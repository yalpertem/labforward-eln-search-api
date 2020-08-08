package com.labforward.api.eln.modules.entry.repository;

import com.labforward.api.eln.modules.entry.entity.Entry;

import java.util.List;

public interface EntryRepository {

    List<Entry> getEntries();

    Entry getEntry(String id);

    Entry createEntry(Entry entry);

    Entry updateEntry(String id, Entry entry);

    void deleteEntry(String id);
}
