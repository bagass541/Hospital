<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
        <title>Контакты</title>
        <link rel="stylesheet" href="style.css">
</head>

<body>
    <div class="container-contacts container">
        <nav class="nav-head">
            <ul>
                <li class="logo-main"><img class="logo-img" src = "../../images/logo.png" >
                    <a href="/">Гомельская центрльная городская<br>клиническая поликлиника</a></li>
                <li class="about"><a href="about-us">О нас</a></li>
                <li><a href='procedures'>Платные услуги</a></li>
                <li><a href='structure'>Наша структура</a></li>
                <li><a href='contacts'>Контакты</a></li>
                 <sec:authorize access="isAnonymous()">    
                <li class="enter"><a href='sign'>Вход</a></li>	
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                <li class="pers-acc"><a href='personal-account'>Личный кабинет</a></li>
                </sec:authorize>	
                  <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR')">
                <li class="admin-panel"><a href='admin-panel'>Админ-панель</a></li>
                </sec:authorize>
            </ul>
        </nav>
       <div class="content-contacts">
        <h1>Контакты</h1>
        <div class="numbers">
            <img class="img-phone" src="../../images/phone.png">
            <p>+375 (25) 333-22-11<br>+375 (25) 444-33-22</p>
        </div>
        <div class="vkontakte">
            <img class="img-vk" src="../../images/vk.png">
            <p>@klin.poliklinika</p>
        </div>
        <div class="instagram">
            <img class="img-inst" src="../../images/insta.png">
            <p>@klin.poliklinika</p>
        </div>
       </div>
    </div>
</body>
</html>