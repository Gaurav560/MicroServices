package com.telusko.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Integer CompanyId);

    boolean addReview(Integer companyId, Review review);
    Review getReviewById(Integer companyId, Integer reviewId);
    Review updateReviewById(Review review,Integer companyId, Integer reviewId);
    boolean deleteReviewById(Integer companyId,Integer reviewId);

}
