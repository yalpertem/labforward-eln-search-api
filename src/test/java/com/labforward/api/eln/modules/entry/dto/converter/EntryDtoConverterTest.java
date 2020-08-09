package com.labforward.api.eln.modules.entry.dto.converter;

import com.labforward.api.eln.modules.entry.dto.EntryDto;
import com.labforward.api.eln.modules.entry.entity.Entry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EntryDtoConverterTest {

    @Autowired
    EntryDtoConverter converter;

    @Test
    void convertToDto_EntityToDto_SetsAllFields() {
        Entry entry = new Entry();
        entry.setId(UUID.randomUUID().toString());
        entry.setTitle(randomAlphabetic(10));
        entry.setContent(randomAlphanumeric(100));

        EntryDto entryDto = converter.convertToDto(entry);

        assertNotNull(entryDto);
        assertEquals(entry.getId(), entryDto.getId());
        assertEquals(entry.getTitle(), entryDto.getTitle());
        assertEquals(entry.getContent(), entryDto.getContent());
    }

    @Test
    void convertToEntity_DtoToEntity_SetsAllFields() {
        EntryDto entryDto = new EntryDto();
        entryDto.setId(UUID.randomUUID().toString());
        entryDto.setTitle(randomAlphabetic(10));
        entryDto.setContent(randomAlphanumeric(100));

        Entry entry = converter.convertToEntity(entryDto);

        assertNotNull(entry);
        assertEquals(entryDto.getId(), entry.getId());
        assertEquals(entryDto.getTitle(), entry.getTitle());
        assertEquals(entryDto.getContent(), entry.getContent());
    }
}