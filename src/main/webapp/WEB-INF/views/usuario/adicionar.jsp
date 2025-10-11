<%--
  Created by IntelliJ IDEA.
  User: enzomota-ieg
  Date: 11/10/2025
  Time: 02:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu de Adicionar Usuários</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/adicionar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<main>
    <%@include file="../components/aside.jsp" %>
    <div class="container">
        <div class="main-content">
            <h1>Adicionar administrador</h1>
            <p>Preencha as informações de novo administrador.</p>
        </div>
        <form class="form-content" name="forms-add-admin" action="${pageContext.request.contextPath}/usuario/adicionar"
              method="post">
            <h2>Informações do usuário</h2>
            <label for="name-input">Nome</label>
            <input class="parameter-input" type="text" id="name-input" name="name" placeholder="Insira o nome">

            <label for="email-input">E-mail</label>
            <input class="parameter-input" type="email" id="email-input" oninput="validateForm()" name="email"
                   placeholder="Insira o endereço de email">
            <span id="errorFeedback"></span>


            <label for="password-input">Senha</label>
            <input class="parameter-input" type="password" id="password-input" name="password"
                   placeholder="Insira a senha">

            <label for="phone-input">Telefone</label>
            <input class="parameter-input" type="text" id="phone-input" name="phone" placeholder="XX XXXXX-XXXX">

            <label for="company-input">Empresa</label>
            <input class="parameter-input" type="text" id="company-input" name="company"
                   placeholder="Insira sua data de nascimenot">

            <label for="role-input">Cargo</label>
            <input class="parameter-input" type="text" id="role-input" name="role" placeholder="Insira seu cargo">

            <label for="picture-input">Foto</label>
            <input class="parameter-input" type="text" id="picture-input" name="picture"
                   placeholder="Insira a URL da foto">

            <input id="submit-btn" type="submit" value="Adicionar">
        </form>

        <%
            String errorFeedback = (String) request.getAttribute("errorMessage");

            if (errorFeedback != null && !errorFeedback.isEmpty()) {
        %>

        <div class="message-error">
            <p><strong>Erro: </strong> <%= errorFeedback %>
            </p>
        </div>
        <%
            }
        %>
    </div>
</main>
<script src="${pageContext.request.contextPath}/assets/js/validator.js"></script>
</body>
</html>
