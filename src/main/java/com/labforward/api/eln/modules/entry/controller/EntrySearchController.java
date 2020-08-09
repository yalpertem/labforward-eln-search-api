package com.labforward.api.eln.modules.entry.controller;

import com.labforward.api.eln.modules.entry.dto.EntrySearchResultDto;
import com.labforward.api.eln.modules.entry.dto.converter.EntrySearchResultDtoConverter;
import com.labforward.api.eln.modules.entry.entity.EntrySearchResult;
import com.labforward.api.eln.modules.entry.service.EntrySearchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

/**
 * Endpoints for search operations in an Entry entity
 */
@RestController
@RequestMapping("/api")
@Validated
public class EntrySearchController {

    @Autowired
    private EntrySearchService entrySearchService;

    @Autowired
    private EntrySearchResultDtoConverter converter;

    @GetMapping("/entries/{id}/search/{word}")
    @ResponseBody
    @ApiOperation(value = "Returns search result for the occurrences of the word and the similar words")
    public EntrySearchResultDto search(@PathVariable @NotEmpty String id,
                                       @PathVariable @NotEmpty String word) {
        EntrySearchResult searchResult = entrySearchService.search(id, word);
        return converter.convertToDto(searchResult);
    }
}