<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
        <title>Главная страница</title>
        <link rel="stylesheet" href="style.css">
</head>

<body>
    <div class="container-main container">
        <nav class="nav-head">
            <ul>
                <li class="logo-main"><img class="logo-img" src = "images/logo.png">
                    <a href="/">Гомельская центрльная городская<br>клиническая поликлиника</a></li>
                <li class="about"><a href="about-us">О нас</a></li>
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
        <div>
            <img class="baby-img" src = "images/baby.jpg">
        </div>
        <div class="slogan">
            <h1>Доверьте ваше здоровье<br>профессионалам!</h1>
            <input type="button" onclick="window.location.href = '/appointment'" class="button-sign" value="Запись к врачу"></input>
            <ul>         
                <li>Лучшие врачи</li>
                <li>Качество</li>
                <li>Надежность</li>
                <li>Внимательность</li>
            </ul>  
        </div>    
    </div>
</body>
</html>