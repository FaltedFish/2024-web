package com.sky.mapper;

import com.sky.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReviewMapper {

    @Select("select * from review")
    List<Review> getAll();

    @Select("insert into review (order_id, rating, review_text) VALUE (#{orderId},#{rating},#{reviewText})")
    void insert(Review review);
}
