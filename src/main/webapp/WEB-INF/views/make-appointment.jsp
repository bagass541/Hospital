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
            <select id="doctorSelect" name="doctorSelect">           
            </select>
            
            <label><br>Выберите дату приема:</label>
            <select id="dateSelect" name="dateSelect">   
            </select>
            
            <label><br>Выберите время приема:</label>
            <select id="timeSelect" name="timeSelect">
            </select>
            <button type="submit" class="button-check-in">Записаться</button>
        </form>
    </div>
</body>
<script>
var doctorTypeSelect = document.getElementById("doctorTypeSelect");
var doctorSelect = document.getElementById("doctorSelect");
var dateSelect = document.getElementById("dateSelect");
var timeSelect = document.getElementById("timeSelect");

doctorTypeSelect.addEventListener("change", function() {
  var selectedType = doctorTypeSelect.value;

  

  var xhr = new XMLHttpRequest();
  xhr.open("GET", "/appointment/doctors?type=" + selectedType, true);

  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      var doctors = JSON.parse(xhr.responseText);
	
      doctorSelect.innerHTML = "";
      dateSelect.innerHTML = "";
      timeSelect.innerHTML = ""; 
      
      var firstOption = document.createElement("option");
      doctorSelect.add(firstOption);
      for (var i = 0; i < doctors.length; i++) {
        var option = document.createElement("option");
        option.text = doctors[i].fio;
        option.value = doctors[i].id;
        console.log("Set option value" + option.value);
        doctorSelect.add(option);
      }

      // Добавляем обработчик события изменения выбранного врача
      	doctorSelect.addEventListener("change", function() {
    	console.log("Selected index:" + doctorSelect.selectedIndex);

    	
   		var selectedDoctorId = doctorSelect.value;
		console.log("Doctor id:" + selectedDoctorId);
       

        var dateXhr = new XMLHttpRequest();
        dateXhr.open("GET", "/appointment/dates?doctorId=" + selectedDoctorId, true);

        dateXhr.onreadystatechange = function() {
          if (dateXhr.readyState === 4 && dateXhr.status === 200) {
            var dates = JSON.parse(dateXhr.responseText);
            dateSelect.innerHTML = "";
            timeSelect.innerHTML = ""; 
            
            const formatterDate = new Intl.DateTimeFormat('ru-RU', { day: '2-digit', month: '2-digit', year: 'numeric' });
        	dateSelect.add(firstOption)        
            for (var j = 0; j < dates.length; j++) {           
            	var dateOption = document.createElement("option");
            	var dateObject = new Date(dates[j]);
                dateOption.text = formatterDate.format(dateObject);
                dateOption.value = dates[j];
                dateSelect.add(dateOption);
            	        
            }

            // Добавляем обработчик события изменения выбранной даты
            dateSelect.addEventListener("change", function() {
              var selectedDate = dateSelect.value;

             //timeSelect.innerHTML = ""; 

              var timeXhr = new XMLHttpRequest();
              timeXhr.open("GET", "/appointment/times?doctorId=" + selectedDoctorId + "&date=" + selectedDate, true);

              timeXhr.onreadystatechange = function() {
                if (timeXhr.readyState === 4 && timeXhr.status === 200) {
                  var times = JSON.parse(timeXhr.responseText);
                  
                  timeSelect.innerHTML = ""; 
                  
                  const formatterTime = new Intl.DateTimeFormat('ru-RU', { hour: '2-digit', minute: '2-digit'});
				  timeSelect.add(firstOption)
                  for (var k = 0; k < times.length; k++) {
                	var timeOption = document.createElement("option");
                	var timeObject = new Date("1970-01-01T" + times[k]);
                	console.log(timeObject);
                    timeOption.text = formatterTime.format(timeObject);
                    timeOption.value = times[k];
                    timeSelect.add(timeOption);
                	                 
                  }
                }
              };

              timeXhr.send();
            });
          }
        };

        dateXhr.send();
      });
    }
  };

  xhr.send();
});
</script>

</html>
