package com.github.deafmist.educationalplatform.service.impl;

import com.github.deafmist.educationalplatform.dto.Course;
import com.github.deafmist.educationalplatform.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
//    private final CourseRepository courseRepository;

//    @Autowired
//    public CourseServiceImpl(CourseRepository courseRepository) {
//        this.courseRepository = courseRepository;
//    }

    @Override
    public List<Course> findAll() {
//        return courseRepository.findAll();
        return null;
    }

    @Override
    public Optional<Course> findById(Long id) {
//        return courseRepository.findById(id);
        return null;
    }

    @Override
    public void update(Course course) {
//        courseRepository.save(course);
    }

    @Override
    public void deleteById(Long id) {
//        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> findByTitleWithPrefix(String prefix) {
//        return courseRepository.findByTitleWithPrefix(prefix);
        return null;
    }
}
