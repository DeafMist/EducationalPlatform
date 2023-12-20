package com.github.deafmist.educationalplatform.service;

import com.github.deafmist.educationalplatform.dto.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAll();

    Optional<Course> findById(Long id);

    void update(Course course);

    void deleteById(Long id);

    List<Course> findByTitleWithPrefix(String prefix);
}
