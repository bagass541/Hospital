<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Пользователи</title>
    <link rel="stylesheet" href="../../style.css">
    <link rel="stylesheet" href="../../font-awesome/css/font-awesome.min.css">
</head>

<body>
    <div class="container-admin-panel container">
        <nav class="nav-head">
            <ul>
                <li class="logo-main"><img class="logo-img" src = "../../images/logo.png" >
                    <a href="/">Гомельская центрльная городская<br>клиническая поликлиника</a></li>
                <li class="../about"><a href="about-us">О нас</a></li>
                <li><a href='../procedures'>Платные услуги</a></li>
                <li><a href='../structure'>Наша структура</a></li>
                <li><a href='../contacts'>Контакты</a></li>                               
                <li class="admin-panel"><a href='../../admin-panel'>Админ-панель</a></li>
            </ul>
        </nav>
         <div class="admin-header"> 
            <h1>Админ-панель</h1>
        </div>
        <div class="admin-content">
        	<div class="div-find-input">
        		<input class="find-input" type="text" id="find-input" onkeyup="filterByFio()" placeholder="Поиск по ФИО">	
        	</div>  
        	<div class="div-doctor-add">
        		<form method="post" action="users/addUser">
        			<table class="input-users">
        				<tbody>
        					<tr>        						
        						<td><input type="text" name="username" placeholder="Логин" required="required"></td>
        						<td><input type="text" name="password" placeholder="Пароль" required="required"></td>
        						<sec:authorize access="hasRole('ROLE_ADMIN')">
        							<td><select name="role">
        								<c:forEach var="role" items="${roles}">
        									<option value="${role.authority}">${role.authority}</option>
        								</c:forEach>
        							</select></td>
        						</sec:authorize>
        						<sec:authorize access="hasRole('ROLE_DOCTOR')">
        							<td>
        								<input type="hidden" name="role" value="ROLE_USER" readonly="readonly">
        							</td>
        						</sec:authorize>
        						<td><input type="text" name="fio" placeholder="ФИО" required="required"></td>
        						<td><input type="text" name="numberPhone" placeholder="Номер телефона" required="required"></td>
        						<td><button class="admin-button" type="submit">+</button></td>
        					</tr>
        				</tbody>   				
        			</table>        				
        		</form>    		
        	</div>    	 	
            <table class="table-admin-panel" id="table-users">
                <thead class="thead-panel">
                    <tr>
                        <td>Логин</td>
                        <td>ФИО</td>
                        <td>Номер телефна</td>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="user" items="${users}">
                		<tr>
                    		<td>${user.username}</td>
                    		<td>${user.userInfo.fio}</td>
                    		<td>${user.userInfo.number}</td>
                    		<sec:authorize access="hasRole('ROLE_ADMIN')">
                    			<td>                   			
                   					<form method="post" action="users/deleteUser">
                   						<button class="admin-button" value="${user.id}" name="userId"><i class="fa fa-trash"></i></button>
                   					</form>                  				
                    			</td>
                    		</sec:authorize>
                    	</tr>
                    </c:forEach>
                </tbody>

            </table>
        </div>   
    </div>
</body>
 <script>
    function filterByFio() {
      var input, filter, table, tr, td, i, txtValue;
      input = document.getElementById("find-input");
      filter = input.value.toUpperCase();
      table = document.getElementById("table-users");
      tr = table.getElementsByTagName("tr");
      for (i = 1; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
        if (td) {
          txtValue = td.textContent || td.innerText;
          if (txtValue.toUpperCase().indexOf(filter) > -1) {
            tr[i].style.display = "";
          } else {
            tr[i].style.display = "none";
          }
        }       
      }
    }
    </script>
</html>