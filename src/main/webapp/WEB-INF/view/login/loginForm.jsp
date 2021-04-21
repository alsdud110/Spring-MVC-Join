<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="login.title" /></title>
</head>
<body>
    <form:form modelAttribute="loginCommand">
    <p>
        <label><spring:message code="id" />:<br>
        <form:input path="id" />
        <form:errors path="id"/>
        </label>
    </p>
    <p>
        <label><spring:message code="password" />:<br>
        <form:password path="pw" />
        <form:errors path="pw"/>
        </label>
    </p>
     <form:errors />
    <p>
        <label><spring:message code="rememberid" />:
        <form:checkbox path="rememberId"/> 
        </label>
    </p>
    <input type="submit" value="<spring:message code="login.btn" />">
    </form:form>
</body>
</html>
