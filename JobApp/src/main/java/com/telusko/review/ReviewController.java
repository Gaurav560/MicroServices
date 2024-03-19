package com.telusko.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Integer companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }


    @PostMapping("/review")
    public ResponseEntity<String> addReview(@RequestBody Review review, @PathVariable Integer companyId) {
        if (reviewService.addReview(companyId, review)) {
            return new ResponseEntity<>("Review Added", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("COMPANY NOT FOUND SO,Review not Added", HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping("/review/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Integer companyId, @PathVariable Integer reviewId) {
        Review review = reviewService.getReviewById(companyId, reviewId);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/review/{reviewId}")
    public ResponseEntity<Review> updateReviewById(@RequestBody Review review, @PathVariable Integer companyId, @PathVariable Integer reviewId) {
        Review review1 = reviewService.updateReviewById(review, companyId, reviewId);

        if (review1 != null) {
            return new ResponseEntity<>(review1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Integer companyId, @PathVariable Integer reviewId) {
        if (reviewService.deleteReviewById(companyId, reviewId)) {
            return new ResponseEntity<>("review deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("review not found", HttpStatus.NOT_FOUND);
        }

    }

}
