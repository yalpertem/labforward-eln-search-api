package com.labforward.api.eln.modules.text.service;

import com.labforward.api.eln.modules.text.entity.StringPosition;

import java.util.List;

/**
 * Responsible of text parse operations
 */
public interface TextParseService {

    List<StringPosition> getWords(String text);
}