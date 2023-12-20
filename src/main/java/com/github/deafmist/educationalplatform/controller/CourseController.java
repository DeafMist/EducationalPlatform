package com.github.deafmist.educationalplatform.controller;

import com.github.deafmist.educationalplatform.dto.ApiError;
import com.github.deafmist.educationalplatform.dto.Course;
import com.github.deafmist.educationalplatform.dto.UpdateCourseRequest;
import com.github.deafmist.educationalplatform.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Objects.requireNonNullElse;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("")
    public List<Course> courseTable() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Long id) {
        return courseService.findById(id).orElseThrow();
    }

    @GetMapping("/filter")
    public List<Course> getCoursesByPrefix(@RequestParam(name = "titlePrefix", required = false) String prefix) {
        return courseService.findByTitleWithPrefix(requireNonNullElse(prefix, ""));
    }

    @PutMapping("/{id}")
    public void updateCourse(@PathVariable Long id, @Valid @RequestBody UpdateCourseRequest request) {
        Course course = courseService.findById(id).orElseThrow();
        Course response = new Course(id, request.author(), request.title());
        courseService.update(response);
    }

    @PostMapping
    public Course createCourse(@Valid @RequestBody UpdateCourseRequest request) {
        Course course = new Course(request.author(), request.title());
        courseService.update(course);
        return course;
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteById(id);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleNoSuchElementException(NoSuchElementException ex) {
        LocalDateTime now = LocalDateTime.now();
        return new ResponseEntity<>(
                new ApiError(
                        OffsetDateTime.of(
                                now,
                                ZoneId.of("Europe/Moscow").getRules().getOffset(now)
                        ),
                        ex.getMessage()
                ),
                HttpStatus.NOT_FOUND
        );
    }
}
