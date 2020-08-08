package com.labforward.api.eln.modules.entry.dto;

import javax.validation.constraints.NotEmpty;

public class EntryDto {

    private String id;

    @NotEmpty()
    private String title;

    @NotEmpty()
    private String content;

    public EntryDto() {
    }

    public EntryDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public EntryDto(String id, String title, String content) {
        this(title, content);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
