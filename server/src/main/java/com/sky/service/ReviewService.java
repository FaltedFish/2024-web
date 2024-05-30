package com.sky.service;

import com.sky.entity.Review;
import com.sky.result.Result;

import java.util.List;

public interface ReviewService {
    Result<List<Review>> getAll();

    Result submit(Review review);
}
