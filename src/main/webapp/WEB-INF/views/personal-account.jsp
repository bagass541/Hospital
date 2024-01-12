<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                <li><a href='services'>Платные услуги</a></li>
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
                <p>ФИО: Богословский А. Н.</p>
                <p>Телефон: +375255552233</p>
            </div>
            <div class="reservations">
               <table class="table-reservations"> 
                <tr>
                    <th>Врач</th>
                    <th>Дата</th>
                </tr>
                <tr>
                    <td>Смирнов А. Н.</td>
                    <td>14.01.24</td>
                </tr>
                <tr>
                    <td>Королев А. Н.</td>
                    <td>14.02.24</td>
                </tr>
               </table>
            </div>
            
        </div>
        <div class="side">
            <div class="label-information">Информация</div>
            <div class="label-reservations">Записи</div>
        </div>
    </div>