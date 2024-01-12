<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Наша структура</title>
    <link rel="stylesheet" href="../../style.css">
</head>

<body>
    <div class="container-view-structure container">
        <nav class="nav-head">
            <ul>
                <li class="logo-main"><img class="logo-img" src = "../../images/logo.png" >
                    <a href="/">Гомельская центрльная городская<br>клиническая поликлиника</a></li>
                <li class="about"><a href="about-us">О нас</a></li>
                <li><a href='services'>Платные услуги</a></li>
                <li><a href='list-structure'>Наша структура</a></li>
                <li><a href='contacts'>Контакты</a></li>
                <sec:authorize access="isAnonymous()">    
                <li class="enter"><a href='sign'>Вход</a></li>	
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                <li class="pers-acc"><a href='personal-account'>Личный кабинет</a></li>
                </sec:authorize>
            </ul>
        </nav>

        <div class="articles">
            <h1>Наша структура</h1>
            <nav class="view-list">
                <ul>
                    <li><a href="#">Филиалы ГЦГКП</a></li>
                    <li><a href="#">Поликлиники г. Гомеля</a></li>
                    <li><a href="#">Больницы</a></li>
                    <li><a href="#">Детские учреждения</a></li>
                    <li><a href="#">Стоматологии</a></li>
                    <li><a href="#">Аптеки</a></li>
                </ul>
            </nav>
        </div>

        <div class="content">
            <h1>Филиалы ГЦГКП</h1>
            <nav class="content-list">
                <ul>
                    <li><a href="#">ГУЗ «Гомельская центральная городская клиническая поликлиника» филиал №6</a></li>
                    <li><a href="#">ГУЗ «Гомельская центральная городская клиническая поликлиника» филиал №12</a></li>
                </ul>
            </nav>
        </div>
    </div>
    
</body>
</html>