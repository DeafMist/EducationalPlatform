package com.github.deafmist.educationalplatform.dto;

public record CourseDto(Long id, String author, String title) {
    public CourseDto(String author, String title) {
        this(null, author, title);
    }
}
