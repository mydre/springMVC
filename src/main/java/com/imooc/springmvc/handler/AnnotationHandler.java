package com.imooc.springmvc.handler;

import com.imooc.springmvc.entity.Goods;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Controller
public class AnnotationHandler {
    @RequestMapping("/modelAndViewTest")
    public ModelAndView modelAndViewTest(){
        ModelAndView modelAndView = new ModelAndView(  );
        modelAndView.addObject( "name","jemmy" );
        modelAndView.setViewName( "show" );
        return modelAndView;
    }

    @RequestMapping("/modelTest")
    public String modelTest(Model model){
        model.addAttribute( "name","mickle" );
        return "show";
    }

    @RequestMapping("/mapTest")
    public String mapTest(Map<String,String> map){
        map.put( "name","bob" );
        return "show";
    }


    @RequestMapping("/addGoods")
    public ModelAndView addGoods(Goods goods){
        //System.out.println( goods );
        ModelAndView modelAndView = new ModelAndView(  );
        modelAndView.addObject( "goods",goods );
        modelAndView.setViewName( "show" );
        return modelAndView;
    }
}
