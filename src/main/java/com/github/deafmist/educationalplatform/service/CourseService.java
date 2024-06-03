package com.github.deafmist.educationalplatform.service;

import com.github.deafmist.educationalplatform.dto.CourseDto;
import com.github.deafmist.educationalplatform.dto.UpdateCourseRequest;

import java.util.List;

public interface CourseService {
    List<CourseDto> findAll();

    List<CourseDto> findByTitleWithPrefix(String prefix);

    CourseDto findById(Long id);

    void update(Long id, UpdateCourseRequest courseDto);

    void create(UpdateCourseRequest courseDto);

    void delete(Long id);
}
