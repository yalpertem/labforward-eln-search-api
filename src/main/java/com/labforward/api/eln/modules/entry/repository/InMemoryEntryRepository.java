package com.labforward.api.eln.modules.entry.repository;

import com.labforward.api.eln.modules.entry.entity.Entry;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * InMemory implementation of EntryRepository for CRUD operations
 */
@Repository
public class InMemoryEntryRepository implements EntryRepository {

    // Thread-safe data store to avoid concurrent editing errors
    private ConcurrentMap<String, Entry> entryMap;

    public InMemoryEntryRepository() {
        entryMap = new ConcurrentHashMap<>();
    }

    @Override
    public List<Entry> getEntries() {
        Collection<Entry> entries = this.entryMap.values();
        return new ArrayList<>(entries);
    }

    @Override
    public Entry getEntry(String id) {
        return this.entryMap.get(id);
    }

    @Override
    public Entry createEntry(Entry entry) {
        entry.setId(UUID.randomUUID().toString());
        upsert(entry);
        return entry;
    }

    @Override
    public Entry updateEntry(String id, Entry entry) {
        upsert(entry);
        return entry;
    }

    @Override
    public void deleteEntry(String id) {
        this.entryMap.remove(id);
    }

    private void upsert(Entry entry) {
        this.entryMap.put(entry.getId(), entry);
    }
}
