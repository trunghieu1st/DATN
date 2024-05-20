package com.example.techstore.controller;

import com.example.techstore.base.RestApiV1;
import com.example.techstore.base.VsResponseUtil;
import com.example.techstore.constant.UrlConstant;
import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.request.SlideRequestDto;
import com.example.techstore.service.SlideService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class SlideController {
    private final SlideService slideService;

    @Tag(name = "slide-controller")
    @Operation(summary = "API get slide by id")
    @GetMapping(UrlConstant.Slide.GET_BY_ID)
    public ResponseEntity<?> getSlideById(@PathVariable String id) {
        return VsResponseUtil.success(slideService.getById(id));
    }

    @Tag(name = "slide-controller")
    @Operation(summary = "API get all slide")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(UrlConstant.Slide.GET_ALL)
    public ResponseEntity<?> getAllSlide(@Valid @ParameterObject PaginationFullRequestDto paginationFullRequestDto) {
        return VsResponseUtil.success(slideService.getAll(paginationFullRequestDto));
    }

    @Tag(name = "slide-controller")
    @Operation(summary = "API get slide by status")
    @GetMapping(UrlConstant.Slide.GET_BY_STATUS)
    public ResponseEntity<?> getSlideByStatus(@Valid @ParameterObject PaginationFullRequestDto paginationFullRequestDto,
                                              @RequestParam(required = false, defaultValue = "true") Boolean status) {
        return VsResponseUtil.success(slideService.getByStatus(paginationFullRequestDto, status));
    }

    @Tag(name = "slide-controller")
    @Operation(summary = "API create slide")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(UrlConstant.Slide.CREATE)
    public ResponseEntity<?> createSlide(@Valid @ModelAttribute SlideRequestDto createDto) {
        return VsResponseUtil.success(slideService.create(createDto));
    }

    @Tag(name = "slide-controller")
    @Operation(summary = "API update slide")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping(UrlConstant.Slide.UPDATE)
    public ResponseEntity<?> updateSlide(@PathVariable String id, @Valid @ModelAttribute SlideRequestDto updateDto) {
        return VsResponseUtil.success(slideService.update(id, updateDto));
    }

    @Tag(name = "slide-controller")
    @Operation(summary = "API delete slide")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(UrlConstant.Slide.DELETE)
    public ResponseEntity<?> deleteSlide(@PathVariable String id) {
        return VsResponseUtil.success(slideService.deleteById(id));
    }
}
