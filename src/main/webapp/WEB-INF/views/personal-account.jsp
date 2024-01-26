<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Личный кабинет</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <div class="container-account container">
        <nav class="nav-head">
            <ul>
                <li class="logo-main"><img class="logo-img" src = "../../images/logo.png" >
                    <a href="/">Гомельская центрльная городская<br>клиническая поликлиника</a></li>
                <li class="about"><a href="about">О нас</a></li>
                <li><a href='procedures'>Платные услуги</a></li>
                <li><a href='structure'>Наша структура</a></li>
                <li><a href='contacts'>Контакты</a></li>
                <sec:authorize access="isAnonymous()">    
                <li class="enter"><a href='sign'>Вход</a></li>	
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                <li class="pers-acc"><a href='personal-account'>Личный кабинет</a></li>
                </sec:authorize>
            </ul>
        </nav>
        <div class="account-content"> 
            <h1>Личный кабинет</h1>
            <div class="information"> 
                 <p>ФИО: ${fio}</p>
                 <p>Телефон: ${number}</p>
            </div>
            <div class="reservations">
               <table class="table-reservations"> 
                <tr>
                    <th>Врач</th>
                    <th>Дата</th>
                </tr>
                <c:forEach var="appointment" items="${appointments}" >
                	<tr>
                		<td>${appointment.doctor.fio}</td>
                		<fmt:parseDate value="${appointment.time}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" />
                		<td><fmt:formatDate value="${parsedDate}" pattern="dd-MM-yyyy HH:mm" /></td>
                	</tr>
                </c:forEach>
               </table>
            </div>
            
        </div>
        <div class="side">
            <div class="label-information">Информация</div>
            <div class="label-reservations">Записи</div>
        </div>
    </div>
</body>
</html>