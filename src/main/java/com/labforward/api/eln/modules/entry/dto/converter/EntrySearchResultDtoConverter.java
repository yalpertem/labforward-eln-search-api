package com.labforward.api.eln.modules.entry.dto.converter;

import com.labforward.api.eln.modules.entry.dto.EntrySearchResultDto;
import com.labforward.api.eln.modules.entry.entity.EntrySearchResult;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntrySearchResultDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public EntrySearchResultDto convertToDto(EntrySearchResult searchResult) {
        EntrySearchResultDto searchResultDto = modelMapper.map(searchResult, EntrySearchResultDto.class);
        return searchResultDto;
    }
}
