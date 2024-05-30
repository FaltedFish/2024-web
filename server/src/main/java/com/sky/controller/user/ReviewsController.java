package com.sky.controller.user;

import com.sky.entity.Review;
import com.sky.result.Result;
import com.sky.service.ReviewService;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("reviewsController")
@RequestMapping("/user/reviews")
@Api(tags = "C端-评论接口")
public class ReviewsController {
    @Autowired
    ReviewService reviewService;
    @GetMapping("")
    @ApiOperation("获取全部评论")
    public Result<List<Review>> getAll(){
        return reviewService.getAll();
    }

    @PostMapping("/submit")
    @ApiOperation("获取全部评论")
    public Result submit(@RequestBody Review review){
        return reviewService.submit(review);
    }
}
