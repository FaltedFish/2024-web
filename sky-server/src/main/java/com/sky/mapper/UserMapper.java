package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.Map;

@Mapper
public interface UserMapper {

    /**
     * 根据openid选择用户
     * @param openid
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    User getByOpenId(String openid);

    void insert(User user);
    @Select("select * from user where id = #{userId}")
    User getById(Long userId);



    Integer countByMap(Map map);

    @Update("update user set amount=#{newBalance} where id=#{userId}")
    void setAmount(Long userId, BigDecimal newBalance);
}
