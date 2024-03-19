package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.result.Result;

public interface DishService {

    /**
     * 新增菜品及其口味
     * @param dishDTO
     * @return
     */
    public Result saveWithFlavor(DishDTO dishDTO);
}
