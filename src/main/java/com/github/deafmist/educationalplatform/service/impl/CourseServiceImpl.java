package com.github.deafmist.educationalplatform.service.impl;

import com.github.deafmist.educationalplatform.converter.DomainToApi;
import com.github.deafmist.educationalplatform.dto.CourseDto;
import com.github.deafmist.educationalplatform.dto.UpdateCourseRequest;
import com.github.deafmist.educationalplatform.repository.CourseRepository;
import com.github.deafmist.educationalplatform.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static com.github.deafmist.educationalplatform.converter.ApiToDomain.dtoToCourse;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseDto> findAll() {
        return courseRepository.findAll().stream()
                .map(DomainToApi::courseToDto)
                .toList();
    }

    @Override
    public CourseDto findById(Long id) {
        return courseRepository.findById(id)
                .map(DomainToApi::courseToDto)
                .orElseThrow();
    }

    @Override
    public void update(Long id, UpdateCourseRequest courseDto) {
        if (courseRepository.findById(id).isEmpty()) throw new NoSuchElementException();
        courseRepository.save(dtoToCourse(id, courseDto));
    }

    @Override
    public void create(UpdateCourseRequest courseDto) {
        courseRepository.save(dtoToCourse(courseDto));
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<CourseDto> findByTitleWithPrefix(String prefix) {
        return courseRepository.findByTitleLike(prefix + "%").stream()
                .map(DomainToApi::courseToDto)
                .toList();
    }
}
