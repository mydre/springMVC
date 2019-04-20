package com.imooc.springmvc.handler;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class MyHandler implements Controller {
    @Override
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView(  );
        modelAndView.addObject("name","Tom");
        modelAndView.setViewName( "show" );//也可以理解为设定jsp页面的文件名
        return modelAndView;
    }
}
