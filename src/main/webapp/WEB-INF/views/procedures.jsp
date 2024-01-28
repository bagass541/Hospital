<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ru">

<head>
    <title>Платные услуги</title>
    <link rel="stylesheet" href="../../style.css">
    <link rel="stylesheet" href="../../font-awesome/css/font-awesome.min.css">
</head>

<body>
    <div class="container-procedures container">
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
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li class="admin-panel"><a href='admin-panel'>Админ-панель</a></li>
                </sec:authorize>
            </ul>
        </nav>
        <div class="table-content">
            <h1>Платные услуги</h1>
            
            	<table class="table-procedures">
                	<thead>
                    	<tr>
                        	<th class="no-border-left">Название</th>
                        	<th>Время проведения, минут</th>
                        	<th class="no-border-right">Стоимость, BYN</th>
                    	</tr>
                	</thead>
                	<tbody>                 
                   		<c:forEach var="procedure" items="${procedures}">
                   			<tr>
                   				<td class="no-border-left normal-column">${procedure.name}</td>
                   				<td class="normal-column">${procedure.minutes}</td>
                   				<td class="no-border-right normal-column">${procedure.price}</td>
                   			
                   				<sec:authorize access="hasRole('ROLE_ADMIN')">
                   					<td class="small-column">
                   						<form method="post" action="procedures/deleteProcedure">
                   							<button class="admin-button" value="${procedure.id}" name="procedureId"><i class="fa fa-trash"></i></button>
                   						</form>
                   					</td>                   			                    	
                   				</sec:authorize>
                   			</tr>
                   		</c:forEach>                   	                   		            	                 
                	</tbody>
            	</table>
            	   
            	<form class="form-add-procedure" method="post" action="procedures/addProcedure">
            		<sec:authorize access="hasRole('ROLE_ADMIN')">                	         
                		<input type="text" name="name" placeholder="Введите название услуги" class="input-procedure" required="required">
                		<input type="number" name="minutes" placeholder="Введите минуты" class="input-procedure" required="required">
                		<input type="number" name="price" placeholder="Введите цену" class="input-procedure" required="required">                   			                				
                		<button class="admin-button" type="submit">+</button>               				              			                   			                     	
                	</sec:authorize>
          		</form>  
        </div>
    </div>
</body>    
</html>