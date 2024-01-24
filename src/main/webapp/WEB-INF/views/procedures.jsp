<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Платные услуги</title>
    <link rel="stylesheet" href="../../style.css">
</head>

<body>
    <div class="container-services container">
        <nav class="nav-head">
            <ul>
                <li class="logo-main"><img class="logo-img" src = "../../images/logo.png" >
                    <a href="/">Гомельская центрльная городская<br>клиническая поликлиника</a></li>
                <li class="about"><a href="about-us">О нас</a></li>
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
        <div class="table-content">
            <h1>Платные услуги</h1>
            <table class="table-services">
                <thead>
                    <tr>
                        <th class="no-border-left">Название</th>
                        <th>Время проведения, минут</th>
                        <th class="no-border-right">Стоимость, BYN</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="no-border-left">ЭКГ</td>
                        <td>60</td>
                        <td class="no-border-right">20</td>
                    </tr>
                    <tr>
                        <td class="no-border-left">Флюорография</td>
                        <td>15</td>
                        <td class="no-border-right">15</td>
                    </tr>
                    <tr>
                        <td class="no-border-left">Тонография</td>
                        <td>45</td>
                        <td class="no-border-right">45</td>
                    </tr>
                    <tr>
                        <td class="no-border-left">УЗИ</td>
                        <td>20</td>
                        <td class="no-border-right">100</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</body>    
</html>