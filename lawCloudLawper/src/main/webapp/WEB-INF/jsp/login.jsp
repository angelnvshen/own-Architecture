<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CHANEL
  Date: 2017/4/8
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<form action="login.do" method="post">
    <table>
        <%--<%=((Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION")).getMessage()%>;--%>
            <c:if test="${param.auth eq 'failure'}">
                <div class="error">
                    <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
                </div>
            </c:if>
        <tr>
            <td> 用户名：</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td> 密码：</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value=" 登录 "/>
                <input type="reset" value=" 重置 "/>
            </td>
        </tr>
    </table>
    <%--<input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />--%>
</form>
</body>
</html>
