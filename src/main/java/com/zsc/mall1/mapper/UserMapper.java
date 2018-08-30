package com.zsc.mall1.mapper;

import com.zsc.mall1.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    /**
     *  private Integer id;
     *     private String  username;
     *     private String password;
     *     private String email;
     *     private String gmt_create;
     *     private String gmt_modified;
     */
    @Select("select * from user where username like CONCAT(CONCAT('%', #{name}),'%')")
    List<User> getSimpleUserByName(String name);

    @Select("select * from user ")
    List<User> getList();

    @Select("select * from user where id=#{id}")
    User getUserById(Integer id);

    @Insert("Insert into user(username,password,email,gmt_create,gmt_modified) " +
            "values(#{username},#{password},#{email},#{gmt_create},#{gmt_modified})")
    int insert(User user);

    @Update("update user set username=#{username}," +
            "email=#{email} ,gmt_create = #{gmt_create},gmt_modified=#{gmt_modified} where id = #{id}")
    void update(User user);

    @Delete("delete from user where id = #{id}")
    void delete(Integer id);

}
