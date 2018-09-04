package com.zsc.mall1.config;

import com.zsc.mall1.service.Result;
import com.zsc.mall1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {




    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

            //System.out.println("preHandle");
            String token  = request.getHeader("Authorization");
            if(token==null||"".equals(token)){
                System.out.println("token null");
                Result.setStatusCode(401);
            }else {
                if(JwtUtil.parseJWT(token)==null){
                    Result.setStatusCode(401);
                }else {
                    Result.setStatusCode(200);
                }
            }

        return true;
    }
}
