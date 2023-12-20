package com.github.deafmist.educationalplatform.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateCourseRequest(
        @NotBlank(message = "Course author has to be filled")
        String author,
        @NotBlank(message = "Course title has to be filled")
        String title
) {
}
