package com.zsc.mall1.service;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zsc.mall1.bean.ProSam;
import com.zsc.mall1.bean.Product;
import com.zsc.mall1.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {


    private  ProductMapper productMapper;
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

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

    public List<Product> getSimProByName(String productName) {
        return productMapper.getSimProductByName(productName);
    }

    public List<ProSam> getComProductById(Integer id) {

        String url = "127.0.0.1:8080/api/similar/{"+id.toString()+"}";

        String string = restTemplate.getForObject(url, String.class);

        //string转换成ProSam对象类型数组
        Gson gs = new Gson();
        return gs.fromJson(string,new TypeToken<List<ProSam>>(){}.getType());
    }

    public List<Product> getHotProduct() {
        List<Product> products= productMapper.getHotProduct();

        for(Product product : products){
            product.setAvg(Double.parseDouble(String.format("%.2f", product.getAvg())));
        }
        return products;
    }
}
