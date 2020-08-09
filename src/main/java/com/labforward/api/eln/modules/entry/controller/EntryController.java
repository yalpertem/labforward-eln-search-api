package com.labforward.api.eln.modules.entry.controller;

import com.labforward.api.eln.modules.entry.dto.EntryDto;
import com.labforward.api.eln.modules.entry.dto.converter.EntryDtoConverter;
import com.labforward.api.eln.modules.entry.entity.Entry;
import com.labforward.api.eln.modules.entry.service.EntryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Endpoints for Crud operations on Entry entities
 */
@RestController
@RequestMapping("/api")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @Autowired
    private EntryDtoConverter converter;

    @GetMapping("/entries")
    @ResponseBody
    @ApiOperation(value = "Returns the list of entries")
    public List<EntryDto> getEntries() {
        List<Entry> entries = this.entryService.getEntries();
        return entries.stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/entries/{id}")
    @ResponseBody
    @ApiOperation(value = "Returns the entry by id")
    public EntryDto getEntry(@PathVariable String id) {
        Entry entry = this.entryService.getEntry(id);
        return converter.convertToDto(entry);
    }

    @PostMapping(value = "/entries")
    @ResponseBody
    @ApiOperation(value = "Creates a new entry")
    public EntryDto createEntry(
            @Valid @RequestBody EntryDto entryDto, HttpServletRequest httpRequest) {
        Entry entry = converter.convertToEntity(entryDto);
        Entry addedEntry = this.entryService.createEntry(entry);
        return converter.convertToDto(addedEntry);
    }

    @PutMapping(value = "/entries/{id}")
    @ResponseBody
    @ApiOperation(value = "Updates the details of the entry")
    public EntryDto updateEntry(@PathVariable String id, @Valid @RequestBody Entry request) {
        Entry entry = this.entryService.updateEntry(id, request);
        return converter.convertToDto(entry);
    }

    @DeleteMapping(value = "/entries/{id}")
    @ApiOperation(value = "Deletes the entry by id")
    public void deleteEntry(@PathVariable String id) {
        this.entryService.deleteEntry(id);
    }
}
