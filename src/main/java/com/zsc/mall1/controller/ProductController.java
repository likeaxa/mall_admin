package com.zsc.mall1.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsc.mall1.bean.MyPageInfo;
import com.zsc.mall1.bean.ProSam;
import com.zsc.mall1.bean.Product;
import com.zsc.mall1.bean.User;
import com.zsc.mall1.service.ProductService;
import com.zsc.mall1.service.Result;
import com.zsc.mall1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ProductController {

    private ProductService productService;


    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("admin/product")
    public MyPageInfo<Product> getProduct(@RequestParam(value = "bookName",defaultValue = "请输入需要查找图书") String productname ,
                                        @RequestParam(value = "start", defaultValue = "1") int start,
                                        @RequestParam(value = "size", defaultValue = "5") int size) throws Exception{

        if(Result.getStatusCode()==401){
            MyPageInfo<Product> myPageInfo = new MyPageInfo<>();
            myPageInfo.setStatusCode(Result.getStatusCode());
            return myPageInfo;
        }
         if(!productname.equals("请输入需要查找图书")){
             PageHelper.startPage(start,size,"id desc");
             List<Product> users = productService.getSimpleProductByName(productname);
             MyPageInfo<Product> myPageInfo = new MyPageInfo<>(users,10);
             myPageInfo.setStatusCode(Result.getStatusCode());
             return myPageInfo;

         }else {
             PageHelper.startPage(start,size,"id desc");
             List<Product> users= productService.getAllProduct();
             MyPageInfo<Product> myPageInfo = new MyPageInfo<>(users,10);//5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
             myPageInfo.setStatusCode(Result.getStatusCode());
             return myPageInfo;
         }

    }

    @GetMapping("/search/productName")
    public List<Product> searchProductName(@RequestParam(value = "productName") String productName){

        return  productService.getSimProByName(productName);
    }
    @GetMapping("/hotadmin")
    public List<Product> getHotProduct(){
        return  productService.getHotProduct();
    }
    @GetMapping("/prosample")
    public List<ProSam> proSample(@RequestParam(value = "productId") Integer id){
        System.out.println(id);

        //Todo
        //通过商品id拿到相识的商品的id和相识度
        // 通过商品id拿到商品名字
        //返回相识商品名字name和相识度value

       // List<ProSam> proSams=productService.getComProductById(id);
        List<ProSam> res = new ArrayList<>();

        ProSam proSam = new ProSam();
        proSam.setValue(1.0);
        proSam.setName("java");
        ProSam proSam1 = new ProSam();
        proSam1.setValue(2.0);
        proSam1.setName("c++");
        ProSam proSam3 = new ProSam();
        proSam3.setValue(8.0);
        proSam3.setName("程序设计");
        res.add(proSam);
        res.add(proSam1);
        res.add(proSam3);

        return res;
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

    @GetMapping("/productcom")//商品推荐
    public String productCom(@RequestParam(value = "productId") int productId){

        //TODO
        return "map";
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
