package com.wei.worm.config;

import com.sun.org.apache.xerces.internal.xs.StringList;
import com.wei.worm.security.UserValidateInterecptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@SpringBootConfiguration
public class WebMvcConfigurerAdapter extends WebMvcConfigurationSupport {
    @Autowired
    private UserValidateInterecptor userValidateInterecptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(userValidateInterecptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html");


    }
}
