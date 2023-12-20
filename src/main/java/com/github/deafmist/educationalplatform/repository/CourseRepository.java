package com.github.deafmist.educationalplatform.repository;

import com.github.deafmist.educationalplatform.dto.Course;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository {
    List<Course> findAll();

    Optional<Course> findById(Long id);

    void save(Course course);

    void deleteById(Long id);

    List<Course> findByTitleWithPrefix(String prefix);
}
