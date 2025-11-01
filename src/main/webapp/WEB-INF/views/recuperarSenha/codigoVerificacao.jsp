<%--
  Created by IntelliJ IDEA.
  User: giuliamanara-ieg
  Date: 30/10/2025
  Time: 08:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Código de verificação - Nutria</title>
    <link rel="stylesheet" href="assets/css/recuperarSenha.css">
    <link rel="shortcut icon" href="./assets/img/logo.svg" type="image/x-icon">

</head>
<body>
<main>
    <%@include file="../components/messagemErro.jsp" %>
    <section class="main-content">
        <img src="assets/img/Nutria.svg" alt="logo" class="logo-image">
        <section class="login-container">
                <h1>Código de verificação</h1>
                <p>Digite o código que enviamos em seu e-mail para redefinir a senha.</p>


            <form class="login-form" method="post" action="${pageContext.request.contextPath}/verificar-codigo">
                <input type="text" inputmode="numeric" maxlength="6" placeholder="------" name="codigo" class="login-input" required>
                <% if (request.getAttribute("error") != null) { %>
                <div class="error">
                    <%= request.getAttribute("error") %>
                </div>
                <%} %>
                <button type="submit" class="login-button">Continuar</button>
            </form>
        </section>
    </section>
</main>

</body>
</html>
