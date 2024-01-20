<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Запись к врачу</title>
    <link rel="stylesheet" href="style.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>

<body>
    <div class="container-appoinment container">
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
        
        <form class="form-appointment" method="post">
            <h1>Запись к врачу</h1>
            <label>Выберите специализацию врача:</label>
            <select id="doctorTypeSelect">
            	<option></option>
              	<c:forEach var="type" items="${doctorTypes}">
                	<option>${type.translate}</option>
                </c:forEach>      
            </select>
            <label><br>Выберите врача:</label>
            <select id="doctorSelect">              
            </select>
            <label><br>Выберите дату приема:</label>
            <select>
                <option>14.01.24</option>
            </select>
            <label><br>Выберите время приема:</label>
            <select>
                <option>14:00</option>
            </select>
        </form>
    </div>
</body>
<script>
  // Получаем ссылки на элементы <select>
  var doctorTypeSelect = document.getElementById("doctorTypeSelect");
  var doctorSelect = document.getElementById("doctorSelect");

  // Обработчик события изменения выбранного типа врача
  doctorTypeSelect.addEventListener("change", function() {
    var selectedType = doctorTypeSelect.value;
    
    // Очищаем список врачей
    doctorSelect.innerHTML = "";
    
    // Делаем AJAX-запрос для получения списка врачей на основе выбранного типа
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/appointment/doctors?type=" + selectedType, true);
    
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4 && xhr.status === 200) {
        var doctors = JSON.parse(xhr.responseText);
        
        // Добавляем <option> в список врачей на основе полученных данных
        for (var i = 0; i < doctors.length; i++) {
          var option = document.createElement("option");
          option.text = doctors[i].fio;
          doctorSelect.add(option);
        }
      }
    };
    
    xhr.send();
  });
</script>

</html>
