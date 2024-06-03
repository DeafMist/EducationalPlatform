package com.github.deafmist.educationalplatform.converter;

import com.github.deafmist.educationalplatform.dto.CourseDto;
import com.github.deafmist.educationalplatform.dto.UpdateCourseRequest;
import com.github.deafmist.educationalplatform.model.Course;

public class ApiToDomain {
    public static Course dtoToCourse(CourseDto dto) {
        return new Course(
                dto.id(),
                dto.author(),
                dto.title()
        );
    }

    public static Course dtoToCourse(Long id, UpdateCourseRequest dto) {
        return new Course(
                id,
                dto.author(),
                dto.title()
        );
    }

    public static Course dtoToCourse(UpdateCourseRequest dto) {
        return new Course(
                dto.author(),
                dto.title()
        );
    }
}
