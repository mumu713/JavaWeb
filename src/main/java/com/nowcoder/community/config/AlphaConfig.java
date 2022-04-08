package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration//普通配置类
public class AlphaConfig {

    @Bean//bean名称为方法名即为simpleDateFormat；此方法返回的值将被装配到容器中
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
