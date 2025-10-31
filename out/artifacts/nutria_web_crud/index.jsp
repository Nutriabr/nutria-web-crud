<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Área secreta</title>
    <link rel="stylesheet" href="assets/css/login.css">
</head>
<body>
<main>
    <section class="main-content">
        <img src="assets/img/Nutria.svg" alt="logo" class="logo-image">
        <section class="login-container">
            <h1>Bem vindo(a) de volta!</h1>
            <p>Insira suas credenciais para acessar sua conta</p>

            <form class="login-form" method="post" action="${pageContext.request.contextPath}/login">
                <input type="email" placeholder="Endereço de email" name="email" class="login-input" required>
                <input type="password" placeholder="Senha" name="senha" class="login-input" required>
                <% if (request.getAttribute("error") != null) { %>
                <div class="error">
                    <%= request.getAttribute("error") %>
                </div>
                <%} %>
                <button type="submit" class="login-button">Login</button>
            </form>
        </section>
    </section>
</main>
</body>
</html>