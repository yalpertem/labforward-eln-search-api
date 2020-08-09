package com.labforward.api.eln.modules.text.service;


import com.labforward.api.eln.modules.text.entity.StringPosition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TextParseServiceImplTest {

    @Autowired
    private TextParseServiceImpl service;

    @Test
    void getWords_TextIsNull_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> service.getWords(null));
    }

    @Test
    void getWords_TextContainsOnlySpaces_ReturnsEmptyList() {
        String text = "                  ";

        List<StringPosition> actual = service.getWords(text);

        assertNotNull(actual);
        assertEquals(0, actual.size());
    }

    @Test
    void getWords_NoPunctuationExist_ReturnsTextAndZeroIndex() {
        String text = "Stringwithoutspaces";

        List<StringPosition> expected = Arrays.asList(
                new StringPosition(text, 0));
        List<StringPosition> actual = service.getWords(text);

        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        assertThat(expected, contains(actual.toArray()));
    }

    @Test
    void getWords_PunctuationDoesExist_ReturnsPositions() {
        String text = "Some : spaces  ,exist some";

        List<StringPosition> expected = Arrays.asList(
                new StringPosition("Some", 0),
                new StringPosition("spaces", 7),
                new StringPosition("exist", 16),
                new StringPosition("some", 22));
        List<StringPosition> actual = service.getWords(text);

        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        assertThat(expected, contains(actual.toArray()));
    }

    @Test
    void getWords_PunctuationsAndDashesDoesExist_ReturnsWordsStartingWithLetterOrDigit() {
        String text = "this-counts this - not";

        List<StringPosition> expected = Arrays.asList(
                new StringPosition("this-counts", 0),
                new StringPosition("this", 12),
                new StringPosition("not", 19));
        List<StringPosition> actual = service.getWords(text);

        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        assertThat(expected, contains(actual.toArray()));
    }
}