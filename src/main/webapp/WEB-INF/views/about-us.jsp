<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ru">
<head>
        <title>О нас</title>
        <link rel="stylesheet" href="style.css">
</head>

<body>
    <div class="container-about-us container">
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
        <div class="text-about"> 
            <h1>О нас</h1>
            <p>Амбулаторно-поликлиническая служба представлена 15 поликлиниками, имеющими статус юридического лица 
                (ГУЗ «Гомельская центральная городская клиническая поликлиника», ГУЗ «Гомельская центральная городская детская 
                клиническая поликлиника», ГУЗ «Гомельская центральная городская стоматологическая поликлиника», ГУЗ «Гомельская 
                городская поликлиника №1», ГУЗ «Гомельская городская клиническая поликлиника №2», ГУЗ «Гомельская городская клиническая 
                поликлиника №3», ГУЗ «Гомельская городская клиническая поликлиника №4», ГУЗ «Гомельская городская клиническая поликлиника №5 
                им С.В.Голуховой», ГУЗ «Гомельская городская клиническая поликлиника №7», ГУЗ «Гомельская городская клиническая поликлиника №8», 
                ГУЗ «Гомельская городская клиническая поликлиника №9», ГУЗ «Гомельская городская клиническая поликлиника №10», ГУЗ «Гомельская 
                городская клиническая поликлиника №11»), при них 13 филиалов, 14 амбулаторий врача общей практики, 29 фельдшерско-акушерских 
                пунктов.
            </p>
        </div>
        <div class="image-about">
            <img class="image-hospital" src="../../images/hospital.png">
        </div>
    </div>
</body>
</html>