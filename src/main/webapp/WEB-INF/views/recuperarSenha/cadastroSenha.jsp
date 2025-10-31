<%--
  Created by IntelliJ IDEA.
  User: giuliamanara-ieg
  Date: 30/10/2025
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nova senha - Área secreta</title>
    <link rel="stylesheet" href="assets/css/recuperarSenha.css">
    <link rel="shortcut icon" href="./assets/img/logo.svg" type="image/x-icon">

</head>
<body>
<main>
    <section class="main-content">
        <img src="assets/img/Nutria.svg" alt="logo" class="logo-image">
        <section class="login-container">
            <h1>Cadastro nova senha</h1>
            <p>Digite uma nova senha e insira duas vezes para confirmar.</p>

            <form class="login-form" method="post" action="${pageContext.request.contextPath}/login">
                <input type="password" placeholder="Digite uma senha" name="password" class="login-input" required>
                <input type="password" placeholder="Digite a senha novamente" name="password" class="login-input" required>

                <% if (request.getAttribute("error") != null) { %>
                <div class="error">
                    <%= request.getAttribute("error") %>
                </div>
                <%} %>
                <button type="submit" class="login-button">Confirmar</button>
            </form>
        </section>
    </section>
</main>
</body>
</html>
