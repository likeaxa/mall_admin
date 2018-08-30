package com.zsc.mall1.service;


import com.zsc.mall1.bean.Product;
import com.zsc.mall1.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {


    private  ProductMapper productMapper;

    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public List<Product>  getAllProduct(){

        try {
            List<Product> products=productMapper.getAllProduct();
            return   returnToController(products);
        }catch (Exception e){
            return  null;
        }
    }
    public List<Product> getSimpleProductByName(String name){
        try {
            List<Product> products= productMapper.getSimpleProductByName(name);
            return  returnToController(products);
        }catch (Exception e){
            return  null;
        }
    }
    public  Product getProductById(Integer id){
        try {
            Product product = productMapper.getProductById(id);
            return  returnToController(product);
        }catch (Exception e){
            return null;
        }
    }
    public boolean insertProduct(Product product){
        try {
            product.setCate_id(1);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            product.setGmt_modified(simpleDateFormat.format(new Date()));
            productMapper.insertProduct(product);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(Product product){
        try {
            product.setCate_id(1);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            product.setGmt_modified(simpleDateFormat.format(new Date()));
            productMapper.updateProduct(product);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }
    public boolean deleteProduct(Integer id){
        try {
            productMapper.deleteProduct(id);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }
    private <T> T returnToController(T value) {
        if(null==value){
            return null;
        }
        else {
            return value;
        }
    }
}
