package com.zsc.mall1.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    //    System.out.println("addInterceptors");
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/login")
                .excludePathPatterns("/usermanager")
                .excludePathPatterns("/bookmanager")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/adminmanager")
                .excludePathPatterns("/prosample/**");
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
