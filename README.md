在web.xml中配置。
=
DispatcherServlet

配置对应的servlet-mapping
-------------------
配置Servlet的api

返回的是
然后spring的视图解析器将逻辑视图转换为物理视图，并且将数据（即model）填充进去           为逻辑视图添加数据
创建物理视图、
不忽略el表达式
在springmvc.xml中进行配置，也就是说，在配置文件中进行设置，使得物理视图和逻辑视图进行关联
将testHandler映射到java类当中，同样是在springmvc.xml中进行配置
配置视图解析器，即ViewResolver
当前的逻辑视图是show这个单词
通过注解方式完成Handler的定义
在springmvc.xml中开启包扫描
第二步让spring的ioc容器知道AnnotationHander是作为一个Handler存在的
需要注意的地方
定义第二种方法，也即是说，另外一种处理请求的Handler：
第三种方法：
实战操作：添加商品
1、定义页面add.jsp
2、定义Handler
这时会发现后台打印出来的数据是乱码的
解决：价格过滤器来处理中文乱码的问题，在web.xml中添加过滤器
<filter>
  <filter-name>encodingFilter</filter-name>
  <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
  <init-param>
    <param-name>encoding</param-name>
    <param-value>UTF-8</param-value>
  </init-param>
  <init-param>
    <param-name>forceEncoding</param-name>
    <param-value>true</param-value>
  </init-param>
</filter>
<filter-mapping>
  <filter-name>encodingFilter</filter-name>
  <url-pattern>/*</url-pattern>
</filter-mapping>
第二个问题：css为什么没有加载进来，因为DispatcherServlet拦截了所有的请求，／，包括去获取css文件。所以需要在web.xml中设置访问静态资源。
<servlet-mapping>
  <servlet-name>default</servlet-name>
  <url-pattern>*.css</url-pattern>
</servlet-mapping>
这是css正常起作用：
输出的结果也正常了：
Goods{price=5500.0, name='台式机电脑'}
修正代码：
@RequestMapping("/addGoods")
public ModelAndView addGoods(Goods goods){
    //System.out.println( goods );
    ModelAndView modelAndView = new ModelAndView(  );
    modelAndView.addObject( "goods",goods );
    modelAndView.setViewName( "show" );
    return modelAndView;
}
到show.jsp中进行数据的展示
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--不忽略el表达式，这样就能够使用el表达式了-->
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${goods.name}<br/>
${goods.price}<br/>
${goods.toString()}<br/>
</body>
</html>
