package com.example.techstore.controller;

import com.example.techstore.base.RestApiV1;
import com.example.techstore.base.VsResponseUtil;
import com.example.techstore.constant.UrlConstant;
import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.request.ReviewCreateDto;
import com.example.techstore.domain.dto.request.ReviewUpdateDto;
import com.example.techstore.security.CurrentUser;
import com.example.techstore.security.UserPrincipal;
import com.example.techstore.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestApiV1
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Tag(name = "review-controller")
    @Operation(summary = "API get review by id")
    @GetMapping(UrlConstant.Review.GET_BY_ID)
    public ResponseEntity<?> getReviewById(@PathVariable String id) {
        return VsResponseUtil.success(reviewService.getById(id));
    }

    @Tag(name = "review-controller")
    @Operation(summary = "API search review")
    @GetMapping(UrlConstant.Review.GET_ALL)
    public ResponseEntity<?> searchReview(@RequestParam(name = "productId", required = false) String productId,
                                          @RequestParam(name = "star", required = false) Integer star,
                                          @Valid @ParameterObject PaginationFullRequestDto paginationFullRequestDto) {
        return VsResponseUtil.success(reviewService.search(productId, star, paginationFullRequestDto));
    }

    @Tag(name = "review-controller")
    @Operation(summary = "API create review")
    @PostMapping(UrlConstant.Review.CREATE)
    public ResponseEntity<?> createReview(@Valid @ModelAttribute ReviewCreateDto reviewCreateDto,
                                          @Parameter(name = "principal", hidden = true)
                                          @CurrentUser UserPrincipal user) {
        return VsResponseUtil.success(reviewService.create(user.getId(), reviewCreateDto));
    }

    @Tag(name = "review-controller")
    @Operation(summary = "API update review by id")
    @PatchMapping(UrlConstant.Review.UPDATE)
    public ResponseEntity<?> updateReviewById(@PathVariable String id,
                                              @Valid @ModelAttribute ReviewUpdateDto reviewUpdateDto,
                                              @Parameter(name = "principal", hidden = true)
                                              @CurrentUser UserPrincipal user) {
        return VsResponseUtil.success(reviewService.updateById(user.getId(), id, reviewUpdateDto));
    }

    @Tag(name = "review-controller")
    @Operation(summary = "API delete review by id")
    @DeleteMapping(UrlConstant.Review.DELETE)
    public ResponseEntity<?> deleteReviewById(@PathVariable String id,
                                              @Parameter(name = "principal", hidden = true)
                                              @CurrentUser UserPrincipal user) {
        return VsResponseUtil.success(reviewService.deleteById(user.getId(), id));
    }

}
