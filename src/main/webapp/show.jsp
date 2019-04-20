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
