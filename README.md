什么是SpringMVC？
=====
Spring框架的一个后续产品。  目前最好的实现MCV设计模式的框架。  Spring

pom依赖

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_1.png)

在web.xml中配置DispatcherServlet

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_2.png)

配置对应的servlet-mapping

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_3.png)

执行流程

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_4.png)

定义自己的Handler

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_5.png)

配置Servlet的api

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_6.png)

返回的是

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_7.png)

然后spring的视图解析器将逻辑视图转换为物理视图，并且将数据（即model）填充进去           为逻辑视图添加数据

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_8.png)

创建物理视图

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_9.png)

不忽略el表达式

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_11.png)

在springmvc.xml中进行配置，也就是说，在配置文件中进行设置，使得物理视图和逻辑视图进行关联

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_12.png)
![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_13.png)
将testHandler映射到java类当中，同样是在springmvc.xml中进行配置

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_14.png)


配置视图解析器，即ViewResolver

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_15.png)

当前的逻辑视图是show这个单词

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_16.png)

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_17.png)

通过注解方式完成Handler的定义

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_21.png)

在springmvc.xml中开启包扫描

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_19.png)

第二步让spring的ioc容器知道AnnotationHander是作为一个Handler存在的

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_20.png)

需要注意的地方

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_22.png)

定义第二种方法，也即是说，另外一种处理请求的Handler：

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_23.png)

第三种方法：

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_24.png)

实战操作：添加商品
1、定义页面add.jsp

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_26.png)

2、定义Handler

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_27.png)

这时会发现后台打印出来的数据是乱码的

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_28.png)

解决：价格过滤器来处理中文乱码的问题，在web.xml中添加过滤器

```xml
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
```
第二个问题：css为什么没有加载进来，因为DispatcherServlet拦截了所有的请求，／，包括去获取css文件。所以需要在web.xml中设置访问静态资源。

```xml
<servlet-mapping>
  <servlet-name>default</servlet-name>
  <url-pattern>*.css</url-pattern>
</servlet-mapping>
```
这时css正常起作用

![Image t](https://github.com/mydre/springMVC/blob/master/src/main/webapp/picture/Snip20190420_29.png)

输出的结果也正常了

```
Goods{price=5500.0, name='台式机电脑'}
```
修正代码：
```java
@RequestMapping("/addGoods")
public ModelAndView addGoods(Goods goods){
    //System.out.println( goods );
    ModelAndView modelAndView = new ModelAndView(  );
    modelAndView.addObject( "goods",goods );
    modelAndView.setViewName( "show" );
    return modelAndView;
}
```
到show.jsp中进行数据的展示
```java
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
