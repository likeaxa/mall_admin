package com.zsc.mall1.mapper;

import com.zsc.mall1.bean.Admin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AdminMapper {

    @Select("select * from admins")
    List<Admin> getList();


    @Insert("Insert into admin(password,adminname) values(#{password},#{adminName})")
    int insert(Admin user);


    @Delete("delete from admin where id = #{id}")
    void delete(Integer id);

}
