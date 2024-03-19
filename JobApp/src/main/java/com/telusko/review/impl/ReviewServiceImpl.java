package com.telusko.review.impl;

import com.telusko.company.Company;
import com.telusko.company.CompanyService;
import com.telusko.review.Review;
import com.telusko.review.ReviewRepository;
import com.telusko.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }


    @Override
    public List<Review> getAllReviews(Integer CompanyId) {
        return reviewRepository.findByCompanyId(CompanyId);
    }


    @Override
    public boolean addReview(Integer companyId, Review review) {
        Company company = companyService.getCompany(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        } else return false;
    }

    @Override
    public Review getReviewById(Integer companyId, Integer reviewId) {
        Company company = companyService.getCompany(companyId);
        if (company != null) {
            Optional<Review> optionalReview = reviewRepository.findById(reviewId);
            if (optionalReview.isPresent()) {
                return optionalReview.get();
            }

        }

        return null;

    }

    @Override
    public Review updateReviewById(Review review, Integer companyId, Integer reviewId) {
        if (companyService.getCompany(companyId) != null) {
            Optional<Review> optionalReview = reviewRepository.findById(reviewId);
            if (optionalReview.isPresent()) {
                Review oldReview = optionalReview.get();
                //now update
                oldReview.setDescription(review.getDescription());
                oldReview.setTitle(review.getTitle());
                oldReview.setRating(review.getRating());

                // Returning the updated (and saved) review
                return reviewRepository.save(oldReview);
            }
        }
        return null;

    }

    @Override
    public boolean deleteReviewById(Integer companyId, Integer reviewId) {
        if (companyService.getCompany(companyId) != null) {
            Optional<Review> optionalReview = reviewRepository.findById(reviewId);
            optionalReview.ifPresent(reviewRepository::delete);
            return true;
        }
        return false;
    }


}
