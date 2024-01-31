<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Личный кабинет</title>
    <link rel="stylesheet" href="../../style.css">
    <link rel="stylesheet" href="../../font-awesome/css/font-awesome.min.css">
</head>

<body>
    <div class="container-admin-panel container">
        <nav class="nav-head">
            <ul>
                <li class="logo-main"><img class="logo-img" src = "../../images/logo.png" >
                    <a href="/">Гомельская центрльная городская<br>клиническая поликлиника</a></li>
                <li class="../about"><a href="about-us">О нас</a></li>
                <li><a href='../procedures'>Платные услуги</a></li>
                <li><a href='../structure'>Наша структура</a></li>
                <li><a href='../contacts'>Контакты</a></li>                               
                <li class="admin-panel"><a href='admin-panel'>Админ-панель</a></li>
            </ul>
        </nav>
         <div class="admin-header"> 
         	<fmt:parseDate value="${currDate}" pattern="yyyy-MM-dd" var="parsedDate" />
            <h1>Расписание <fmt:formatDate value="${parsedDate}" pattern="dd.MM.yy"/></h1>
        </div>
        <div class="admin-content">     	 	
            <table class="table-admin-panel" id="table-doctors">
                <thead class="thead-panel">
                    <tr>
                        <td>ФИО пациента</td>
                        <td>Время</td>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="appointment" items="${appointments}">
                		<tr>
                    		<td>${appointment.user.userInfo.fio}</td>
                    		<fmt:parseDate value="${appointment.time}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" />
                    		<td><fmt:formatDate value="${parsedDate}" pattern="HH:mm"/></td>
                    	</tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>   
    </div>
</body>
</html>