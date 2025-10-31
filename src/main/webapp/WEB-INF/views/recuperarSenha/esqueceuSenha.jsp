<%--
  Created by IntelliJ IDEA.
  User: giuliamanara-ieg
  Date: 28/10/2025
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Esqueci a senha - Nutria</title>
    <link rel="stylesheet" href="assets/css/recuperarSenha.css">
    <link rel="shortcut icon" href="./assets/img/logo.svg" type="image/x-icon">

</head>
<body>
<main>
    <section class="main-content">
        <img src="assets/img/Nutria.svg" alt="logo" class="logo-image">
        <section class="login-container">
                <h1>Esqueceu a sua senha?</h1>
                <p>Insira seu endereço de e-mail e enviaremos um código para redefinir a senha.</p>

            <form class="login-form" method="get" action="${pageContext.request.contextPath}/codigoVerificacao">
                <input type="email" placeholder="Endereço de email" name="email" class="login-input" required>
                <% if (request.getAttribute("error") != null) { %>
                <div class="error">
                    <%= request.getAttribute("error") %>
                </div>
                <%} %>
                <button type="submit" class="login-button">Enviar código</button>
            </form>
        </section>
    </section>
</main>

</body>
</html>
