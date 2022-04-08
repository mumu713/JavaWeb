package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype")//可以创建多个实例     （singlet）单个实例
public class AlphaService {
    /**
     * service 依赖于dao 的依赖注入
     */
    @Autowired
    private AlphaDao alphaDao;

    public AlphaService(){
        System.out.println("实例化AlphaService");
    }
    @PostConstruct//构造之后调用
    public void init(){
        System.out.println("初始化AlphaService");
    }
    @PreDestroy//销毁之前调用
    public void destroy(){
        System.out.println("销毁AlphaService");
    }

    public String find(){
        return alphaDao.select();
    }
}
