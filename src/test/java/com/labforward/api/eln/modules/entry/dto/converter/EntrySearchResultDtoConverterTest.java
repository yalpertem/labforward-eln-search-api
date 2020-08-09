package com.labforward.api.eln.modules.entry.dto.converter;

import com.labforward.api.eln.modules.entry.dto.EntrySearchResultDto;
import com.labforward.api.eln.modules.entry.dto.EntrySearchResultItemDto;
import com.labforward.api.eln.modules.entry.entity.EntrySearchResult;
import com.labforward.api.eln.modules.entry.entity.EntrySearchResultItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EntrySearchResultDtoConverterTest {

    private final int TEST_OCCURENCE_COUNT = 3;

    @Autowired
    EntrySearchResultDtoConverter converter;

    @Test
    public void convertToDto_EntityToDto_SetsAllFields() {
        EntrySearchResult expected = new EntrySearchResult();
        List<EntrySearchResultItem> expectedItems = Arrays.asList(
                new EntrySearchResultItem(randomAlphabetic(5), 1, 1, getRandomOccurrences()),
                new EntrySearchResultItem(randomAlphabetic(7), 0, 1, getRandomOccurrences())
        );
        expected.setSearchResultItems(expectedItems);

        EntrySearchResultDto actual = converter.convertToDto(expected);

        assertNotNull(actual);
        List<EntrySearchResultItemDto> actualItems = actual.getSearchResultItems();
        assertNotNull(actualItems);

        for (int i = 0; i < expectedItems.size(); i++) {
            EntrySearchResultItem expectedItem = expectedItems.get(i);
            EntrySearchResultItemDto actualItem = actualItems.get(i);
            assertEquals(expectedItem.getWord(), actualItem.getWord());
            assertEquals(expectedItem.getDistance(), actualItem.getDistance());
            assertEquals(expectedItem.getFrequency(), actualItem.getFrequency());
            assertThat(expectedItem.getOccurrences(),
                    contains(actualItem.getOccurrences().toArray()));
        }
    }

    private List<Integer> getRandomOccurrences() {
        Random r = new Random();
        List<Integer> occurrences = new ArrayList<>();
        for (int i = 0; i < TEST_OCCURENCE_COUNT; i++) {
            occurrences.add(r.nextInt());
        }
        return occurrences;
    }
}