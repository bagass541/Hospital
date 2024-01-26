<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Наша структура</title>
    <link rel="stylesheet" href="../../style.css">
    <link rel="stylesheet" href="../../font-awesome/css/font-awesome.min.css">
</head>

<body>
    <div class="container-view-structure container">
        <nav class="nav-head">
            <ul>
                <li class="logo-main"><img class="logo-img" src = "../../../images/logo.png" >
                    <a href="/">Гомельская центрльная городская<br>клиническая поликлиника</a></li>
                <li class="../about"><a href="about-us">О нас</a></li>
                <li><a href='../procedures'>Платные услуги</a></li>
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
            <nav class="view-list" >
                <ul id="listArticles">
                    <li id="filials"><a href="filials">Филиалы ГЦГКП</a></li>
                    <li id="polyclinics"><a href="polyclinics">Поликлиники г. Гомеля</a></li>
                    <li id="hospitals"><a href="hospitals">Больницы</a></li>
                    <li id="child-institutions"><a href="child-institutions">Детские учреждения</a></li>
                    <li id="stomatologies"><a href="stomatologies">Стоматологии</a></li>
                    <li id="pharmacies"><a href="pharmacies">Аптеки</a></li>
                </ul>
            </nav>
        </div>
        <div class="list-structure-elements">      
        <div class="h1-structure-header" >
        <h1  id="heading">${h1Name}</h1>
        </div>
        <div class="content">
        	<form method="post" id="deleteForm">          
            	<nav class="content-list">
                	<ul>
                		<c:forEach var="element" items="${structureElements}">               
                			<li><a>${element.name}</a>
                				<sec:authorize access="hasRole('ROLE_ADMIN')">
                					<button class="button-procedure" value="${element.id}" name="structureElementId"><i class="fa fa-trash"></i></button>
                				</sec:authorize>  
                			</li>
                		</c:forEach>                      
                	</ul>
           		</nav>
            </form>  
        </div>
        </div>		        
    </div>    
</body>
<script>
	var ulList = document.getElementById('listArticles');
	var deleteForm = document.getElementById('deleteForm');
	
	var currentUrl = window.location.href;
	var urlParts = currentUrl.split('/');
	var elementType = urlParts[urlParts.length - 1];

	var article = document.getElementById(elementType);
	// Проверяем содержимое заголовка и применяем соответствующий стиль к кнопке
	switch(elementType) {
		case 'filials':			
			article.classList.add("article-selected");
			deleteForm.action = "/structure/filials/deleteElement";
			break;
		case 'polyclinics':
			article.classList.add("article-selected");
			deleteForm.action = "/structure/polyclinics/deleteElement";
			break;
		case 'hospitals':
			article.classList.add("article-selected");
			deleteForm.action = "/structure/hospitals/deleteElement";
			break;
		case 'child-institutions':
			article.classList.add("article-selected");
			deleteForm.action = "/structure/child-institutions/deleteElement";
			break;
		case 'stomatologies':
			article.classList.add("article-selected");
			deleteForm.action = "/structure/stomatologies/deleteElement";
			break;
		case 'pharmacies':
			article.classList.add("article-selected");
			deleteForm.action = "/structure/pharmacies/deleteElement";
			break;
	};
</script>
</html>