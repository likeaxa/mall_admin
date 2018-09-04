package com.zsc.mall1.mapper;

import com.zsc.mall1.bean.ProSam;
import com.zsc.mall1.bean.Product;
import com.zsc.mall1.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {

    @Select("select * from product where product_name like CONCAT(CONCAT('%', #{name}),'%')")
    List<Product> getSimpleProductByName(String name);

    @Select("select * from product")
    List<Product> getAllProduct();

    @Select("select * from product where id=#{id}")
    Product getProductById(int id);

    @Insert("insert into  product(gmt_create,gmt_modified,product_name,author,tags,Image,publisher,summary,price,cate_id) " +
            "values(#{gmt_create},#{gmt_modified},#{product_name},#{author},#{tags},#{image},#{publisher},#{summary},#{price},#{cate_id})")
    void insertProduct(Product product);

    @Update("update product set gmt_create=#{gmt_create},gmt_modified=#{gmt_modified},product_name=#{product_name}," +
            "author=#{author},tags=#{tags},image=#{image},publisher=#{publisher},summary=#{summary},price=#{price}" +
            " where id =#{id}" )
    void updateProduct(Product product);

    @Delete("delete from product where id = #{id}")
    void deleteProduct(Integer id);


    @Select("select id,product_name, avg,count,total ,image from product " +
            "where product_name like CONCAT(CONCAT('%', #{name}),'%')")
    List<Product> getSimProductByName(String productName);


    @Select("select id,product_name, avg,count,total ,image from product " +
            "where count>200 and cate_id=2 and avg > 4.6 order by count desc")
    List<Product> getHotProduct();
}
