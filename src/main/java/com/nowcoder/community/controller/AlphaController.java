package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * 与网络端进行交互  接受返回  get post数据
 */
@Controller
@RequestMapping("alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;//可以直接调用service组件中的AlphaService

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){

        return "Hello Spring Boot.";
    }
    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    /**
     * 向浏览器发送数据
     */
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据 用request
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());//路径
        Enumeration<String> enumeration = request.getHeaderNames();//迭代器Enumeration接受request get到的数据

        while(enumeration.hasMoreElements()){//遍历迭代器
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));//用来接收输入数据（在url的末尾 用？加 code=。。。）name

        //返回响应数据 用response
        response.setContentType("text/html;charset=utf-8");
        try (
                PrintWriter writer = response.getWriter();
                ){
            writer.write("<h1>陈璐瑶是个撒子<h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //GET请求  只能在路径后面加？加值
    //***1***查询所有学生 路径： /students?current=1&limit=20   代表：在students网页中需要传入两个参数current和limit

    @RequestMapping(path = "/students",method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current",required = false,defaultValue = "1") int current,//如果没有默认值，则通过注解方式进行设置默认值
            @RequestParam(name = "limit",required = false,defaultValue = "10") int limit){//required 为可以没有默认值
        return "陈璐瑶小撒子、刘昊";
    }
    //***2*** 路径：/student/123   单个访问
    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)//设置/student路径后面的id属性 并根据id访问不同的学生
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){//可以根据id来返回值
        System.out.println(id);
        return "陈璐瑶小撒子";
    }

    //POST请求  浏览器向服务器提交数据
    //***1***
    @RequestMapping(path = "/student",method = RequestMethod.POST)  //使用static下的静态网页
    @ResponseBody
    public String saveStudent(String name , int num){
        return "成功";
    }

    //响应html数据
    //***1***
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","刘昊");
        modelAndView.setViewName("/demo/view");                      //用templates下的html动态网页
        return modelAndView;
    }

    //***2***  推荐！！！
    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model){//model数据直接装入Model中
        model.addAttribute("name","沈工大");
        model.addAttribute("name","铁西小清华");
        return "/demo/view";//直接返回路径
    }

    //响应JSON数据（异步请求）
    //java对象 -->  通过JSON字符串解析 -->  js对象(等等其他对象)
    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp(){
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","刘昊");
        emp.put("age","20");
        return emp;
    }

    @RequestMapping(path = "/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","刘昊");
        emp.put("age","20");
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","陈璐瑶小撒子");
        emp.put("age","21");
        list.add(emp);

        return list;
    }

}
