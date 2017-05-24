<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>login</title>
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.12.4.js"></script>
    <script>
        jQuery.prototype.serializeObject=function(){
            var a,o,h,i,e;
            a=this.serializeArray();
            o={};
            h=o.hasOwnProperty;
            for(i=0;i<a.length;i++){
                e=a[i];
                if(!h.call(o,e.name)){
                    o[e.name]=e.value;
                }
            }
            return o;
        };

        function check(){
            var txt_username = $.trim($("#username").attr("value"));
            var txt_password = $.trim($("#password").attr("value"));
            var isSuccess = 1;
            if(txt_username.length == 0) {
                console.log("xxxxxxxxxx");
                isSuccess = 0;
            }
            if (isSuccess == 0) {
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<form action="login.do" method="post" id="login_form"  onsubmit="return check()">
    <table>
        <tr>
            <td> 用户名：</td>
            <td><input type="text" name="username" id="username" value=""/></td>
        </tr>
        <tr>
            <td> 密码：</td>
            <td><input type="password" name="password" id="password"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value=" 登录 "/>
                <input type="reset" value=" 重置 "/>
            </td>
        </tr>
    </table>
    <input type="checkbox" name="remember-me" value="true">记住我
    <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
</form>
</body>
</html>
