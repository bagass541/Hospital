<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Гомельская центральная городская клиническая поликлиника</title>
    <link rel="stylesheet" href="../../style.css">
</head>

<body>
	<div class="container-auth container">
		<div class="logo-auth">
			<img class="logo-img-auth" src = "images/logo.png" >
			<h1>Гомельская центрльная городская<br>клиническая поликлиника</h1>
		</div>

		

    	<form class="login-form" action="/login" method="post">
			<div class="h2-login">
				<h2>Вход</h2>
			</div>

			<div class="input-username">
				<input type="text" class="input-text" id="username" name="username" placeholder="Имя пользователя" required>
			</div>

			<div class="input-password">
				<input type="password" id="password" class="input-text" name="password" placeholder="Пароль" required>
			</div>
			<div> 
				<button type="submit" class="button-OK">Подтвердить</button>
			</div>
	</form>
	</div>
</body>
</html>