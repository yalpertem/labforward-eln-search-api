package com.labforward.api.eln.modules.text.service;


import com.labforward.api.eln.modules.text.entity.StringPosition;
import org.springframework.stereotype.Service;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

@Service
public class TextParseServiceImpl implements TextParseService {

    /**
     * Splits the text and returns index of each word.
     * <p>
     * Uses {@link java.lang.Character #isLetterOrDigit(char)}
     * to decide whether a string is actually a word by checking the first character.
     * Uses {@link BreakIterator} for iteration in text.
     *
     * @return word and its index in text
     */
    public List<StringPosition> getWords(String text) {
        if (text == null) {
            throw new IllegalArgumentException();
        }

        List<StringPosition> splitted = new ArrayList<>();
        BreakIterator boundary = BreakIterator.getWordInstance();
        boundary.setText(text);

        int start = boundary.first();
        int end = boundary.next();

        while (end != BreakIterator.DONE) {
            if (Character.isLetterOrDigit(text.charAt(start))) {
                String word = text.substring(start, end);
                splitted.add(new StringPosition(word, start));
            }
            start = end;
            end = boundary.next();
        }
        return splitted;
    }
}
