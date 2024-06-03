package com.github.deafmist.educationalplatform.converter;

import com.github.deafmist.educationalplatform.dto.CourseDto;
import com.github.deafmist.educationalplatform.model.Course;

public class DomainToApi {
    public static CourseDto courseToDto(Course course) {
        return new CourseDto(
                course.getId(),
                course.getAuthor(),
                course.getTitle()
        );
    }
}
