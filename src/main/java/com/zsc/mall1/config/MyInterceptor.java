package com.zsc.mall1.config;

import com.zsc.mall1.util.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

            //System.out.println("preHandle");
            String token  = request.getHeader("Authorization");
            if(token==null||"".equals(token)){
                response.sendRedirect("/login");
                return false;
            }else {
                String s = JwtUtil.parseJWT(token);
                if(s==null){
                    response.sendRedirect("/login");
                    return false;
                }else {
                    return true;
                }
            }

    }
}
