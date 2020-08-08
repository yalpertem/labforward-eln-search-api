package com.labforward.api.eln.modules.entry.service;


import com.labforward.api.eln.common.exception.ArgumentConflictException;
import com.labforward.api.eln.common.exception.NotFoundException;
import com.labforward.api.eln.modules.entry.entity.Entry;
import com.labforward.api.eln.modules.entry.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {

    private EntryRepository entryRepository;

    @Autowired
    public EntryServiceImpl(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @Override
    public List<Entry> getEntries() {
        return entryRepository.getEntries();
    }

    @Override
    public Entry getEntry(String id) {
        Entry existing = entryRepository.getEntry(id);
        if (existing == null) {
            throw new NotFoundException();
        }
        return existing;
    }

    @Override
    public Entry createEntry(Entry entry) {
        return entryRepository.createEntry(entry);
    }

    @Override
    public Entry updateEntry(String id, Entry entry) {
        if (!id.equals(entry.getId())) {
            throw new ArgumentConflictException();
        }

        synchronized (this) {
            Entry existing = entryRepository.getEntry(id);
            if (existing == null) {
                throw new NotFoundException();
            }
            return entryRepository.updateEntry(id, entry);
        }
    }

    @Override
    public synchronized void deleteEntry(String id) {
        Entry existing = entryRepository.getEntry(id);
        if (existing == null) {
            throw new NotFoundException();
        }
        entryRepository.deleteEntry(id);
    }
}

