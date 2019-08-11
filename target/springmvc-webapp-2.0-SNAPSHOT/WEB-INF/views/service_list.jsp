<%--
  Created by IntelliJ IDEA.
  User: Hau_hypress
  Date: 8/9/2019
  Time: 11:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <ul>
        <c:forEach items="${service_list}" var="item">
            <li>
                <a href="#">
                    <c:out value="${item}"></c:out>
                </a>
            </li>
        </c:forEach>
    </ul>
</html>
