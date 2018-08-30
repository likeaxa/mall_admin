package com.zsc.mall1.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsc.mall1.bean.Product;
import com.zsc.mall1.service.ProductService;
import com.zsc.mall1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("admin/product")
    public PageInfo<Product> getProduct(@RequestParam(value = "bookName",defaultValue = "请输入需要查找图书") String productname ,
                                        @RequestParam(value = "start", defaultValue = "1") int start,
                                        @RequestParam(value = "size", defaultValue = "5") int size) throws Exception{

         if(!productname.equals("请输入需要查找图书")){
             PageHelper.startPage(start,size,"id desc");
             List<Product> users = productService.getSimpleProductByName(productname);
             return new PageInfo<>(users,10);
         }else {
             PageHelper.startPage(start,size,"id desc");
             List<Product> users= productService.getAllProduct();
             return  new PageInfo<>(users,5); //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
         }



    }

    @GetMapping("/admin/productId")
    public Product getUserById(@RequestParam(value = "productId") Integer id){
        return productService.getProductById(id);
    }

    @PutMapping("/admin/product")
    public String  insertProduct(@RequestBody Product product){

     //   System.out.println(product.getProduct_name());

        boolean flag =productService.insertProduct(product);
        return resultToView(flag);
    }

    @PostMapping("/admin/product")
    public  String updateProduct(@RequestBody Product product){
       // System.out.println(product.getProduct_name());
        boolean flag =productService.updateProduct(product);
        return resultToView(flag);
    }
    @DeleteMapping("/admin/product")
    public String deleterProduct(@RequestParam(value = "productId") Integer id){
      //  System.out.println(id);
        boolean flag =productService.deleteProduct(id);
        return resultToView(flag);
    }

    private String  resultToView(boolean flag) {
        if(flag){
            return "ok";
        }
        else {
            return "-1";
        }
    }
}
