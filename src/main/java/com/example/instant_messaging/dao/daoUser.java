package com.example.instant_messaging.dao;


import com.example.instant_messaging.bean.Message;
import com.example.instant_messaging.bean.User;
import com.example.instant_messaging.bean.friend;
import org.apache.ibatis.annotations.*;

import java.util.Date;

import java.util.ArrayList;

@Mapper
public interface daoUser {
    @Select("select*from user where id=#{id}")
    public User selectById(Integer id);

    @Insert("INSERT INTO `chat`.`user`(`name`, `password`, `img`) VALUES (#{name},#{passWord},#{img})")
    public void insertUser(String name, String passWord, String img);

    @Select("select*from user where name=#{name}")
    public User selectByName(String name);

    @Select("SELECT*FROM friend WHERE friend.userID1 = #{id}")
    public ArrayList<friend> selectFriend(Integer id);

    @Select("SELECT*FROM friend WHERE friend.userID1 = #{userID} and friend.userID2 = #{friendID}")
    public friend friendIsEmpty(Integer userID, Integer friendID);

    @Insert("INSERT INTO `chat`.`friend`(`userID1`, `userID2`) VALUES (#{userID1}, #{userID2})")
    public void insertFiend(int userID1, int userID2);

    @Delete("DELETE FROM `chat`.`friend` WHERE `userID1` = #{userID1} and `userID2`=#{userID2}")
    public void deleteFriend(Integer userID1, Integer userID2);

    @Select("SELECT * FROM `chat` where (`from`=#{from} and `to`=#{to})OR(`from`=#{to} AND `to`=#{from})")
    public ArrayList<Message> selectMessage(Integer from, Integer to);

    @Insert("INSERT INTO `chat`.`chat`(`from`, `to`, `message`, `Date`) VALUES (#{from}, #{to}, #{message}, #{date})")
    public void insertMessage(Integer from, Integer to, String message, Date date);
    @Update("UPDATE `chat`.`user` SET `name` = #{username}, `password` = #{password}, `img` = #{img} WHERE `id` = #{id}")
    public void updateUser(Integer id,String username,String password,String img);
}