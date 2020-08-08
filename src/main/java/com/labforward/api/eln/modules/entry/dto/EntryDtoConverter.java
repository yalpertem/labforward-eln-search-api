package com.labforward.api.eln.modules.entry.dto;

import com.labforward.api.eln.modules.entry.entity.Entry;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntryDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public EntryDto convertToDto(Entry entry) {
        return modelMapper.map(entry, EntryDto.class);
    }

    public Entry convertToEntity(EntryDto entryDto) {
        return modelMapper.map(entryDto, Entry.class);
    }
}