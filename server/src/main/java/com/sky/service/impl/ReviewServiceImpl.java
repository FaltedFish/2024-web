package com.sky.service.impl;

import com.sky.entity.Review;
import com.sky.mapper.ReviewMapper;
import com.sky.result.Result;
import com.sky.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewMapper reviewMapper;
    @Override
    public Result<List<Review>> getAll() {
        List<Review> reviewList = reviewMapper.getAll();
        return Result.success(reviewList);
    }

    @Override
    public Result submit(Review review) {
        reviewMapper.insert(review);
        return Result.success();
    }
}
