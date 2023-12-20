package com.github.deafmist.educationalplatform.dto;

public record Course(Long id, String author, String title) {
    public Course(String author, String title) {
        this(null, author, title);
    }
}
