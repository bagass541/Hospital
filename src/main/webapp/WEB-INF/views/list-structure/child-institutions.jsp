<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
                <li class="logo-main"><img class="logo-img" src = "../../../images/logo.png" >
                    <a href="/">Гомельская центрльная городская<br>клиническая поликлиника</a></li>
                <li class="../about"><a href="about-us">О нас</a></li>
                <li><a href='../services'>Платные услуги</a></li>
                <li><a href='../structure'>Наша структура</a></li>
                <li><a href='../contacts'>Контакты</a></li>
                <sec:authorize access="isAnonymous()">    
                <li class="enter"><a href='../sign'>Вход</a></li>	
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                <li class="pers-acc"><a href='../personal-account'>Личный кабинет</a></li>
                </sec:authorize>
            </ul>
        </nav>

        <div class="articles">
            <h1>Наша структура</h1>
            <nav class="view-list">
                <ul>
                    <li><a href="filials">Филиалы ГЦГКП</a></li>
                    <li><a href="polyclinics">Поликлиники г. Гомеля</a></li>
                    <li><a href="hospitals">Больницы</a></li>
                    <li class="chosenElement"><a href="child-institutions">Детские учреждения</a></li>
                    <li><a href="stomatologies">Стоматологии</a></li>
                    <li><a href="pharmacies">Аптеки</a></li>
                </ul>
            </nav>
        </div>
        <div class="list-structure-elements">      
        <div class="h1-structure-header">
         <h1>Детские учреждения</h1>
        </div>
        <div class="content">          
            <nav class="content-list">
                <ul>
                <c:forEach var="childInstitution" items="${childInstitutionList}">
                	<li><a>${childInstitution.name}</a></li>
                </c:forEach>                         
                </ul>
            </nav>
        </div>
        </div>		        
    </div>    
</body>
</html>