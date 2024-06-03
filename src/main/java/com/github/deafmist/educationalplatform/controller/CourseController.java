package com.github.deafmist.educationalplatform.controller;

import com.github.deafmist.educationalplatform.dto.ApiError;
import com.github.deafmist.educationalplatform.dto.CourseDto;
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
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("")
    public List<CourseDto> getCourses() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public CourseDto getCourse(@PathVariable Long id) {
        return courseService.findById(id);
    }

    @GetMapping("/filter")
    public List<CourseDto> getCoursesByPrefix(@RequestParam(name = "titlePrefix", required = false) String prefix) {
        return courseService.findByTitleWithPrefix(requireNonNullElse(prefix, ""));
    }

    @PutMapping("/{id}")
    public void updateCourse(@PathVariable Long id, @Valid @RequestBody UpdateCourseRequest request) {
        courseService.update(id, request);
    }

    @PostMapping
    public void createCourse(@Valid @RequestBody UpdateCourseRequest request) {
        courseService.create(request);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
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
