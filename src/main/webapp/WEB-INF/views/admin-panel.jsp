<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Личный кабинет</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <div class="container-admin-panel container">
        <nav class="nav-head">
            <ul>
                <li class="logo-main"><img class="logo-img" src = "../../images/logo.png" >
                    <a href="/">Гомельская центрльная городская<br>клиническая поликлиника</a></li>
                <li class="about"><a href="about-us">О нас</a></li>
                <li><a href='procedures'>Платные услуги</a></li>
                <li><a href='structure'>Наша структура</a></li>
                <li><a href='contacts'>Контакты</a></li>                               
                <li class="admin-panel"><a href='admin-panel'>Админ-панель</a></li>
            </ul>
        </nav>
        <div class="admin-header"> 
            <h1>Админ-панель</h1>
        </div>
        <div class="admin-content">
            <form action="admin-panel/doctors">
                <button class="button-doctors">Врачи</button>
            </form>
            <form action="admin-panel/users">
                <button class="button-users">Пользователи</button>
            </form>         
        </div>       
    </div>
</body>
</html>