package com.labforward.api.eln.modules.text.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TextSimilarityServiceImplTest {

    @Autowired
    private TextSimilarityServiceImpl service;

    @Test
    public void getDistance_LeftIsNull_ThrowsIllegalArgumentException() {
        String right = "test";

        assertThrows(IllegalArgumentException.class,
                () -> service.getDistance(null, right));
    }

    @Test
    public void getDistance_RightIsNull_ThrowsIllegalArgumentException() {
        String left = "test";

        assertThrows(IllegalArgumentException.class,
                () -> service.getDistance(left, null));
    }

    @Test
    public void getDistance_EqualStrings_ReturnsZero() {
        int actual = service.getDistance("test", "test");
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void getDistance_RightIsSingleCharacterLonger_ReturnsOne() {
        int actual = service.getDistance("test", "testy");
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void getDistance_LeftIsSingleCharacterLonger_ReturnsOne() {
        int actual = service.getDistance("test", "tes");
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void getDistance_SingleDifferentCharacter_ReturnsOne() {
        int actual = service.getDistance("test", "tesx");
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void getDistance_SingleDifferentCase_ReturnsOne() {
        int actual = service.getDistance("test", "Test");
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void getDistance_NotSimilarStrings_ReturnsMinusOne() {
        int actual = service.getDistance("test", "notsimilar");
        int expected = -1;
        assertEquals(expected, actual);
    }
}