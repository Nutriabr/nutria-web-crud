<%--
  Created by IntelliJ IDEA.
  User: luismedeiros-ieg
  Date: 10/10/2025
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>Adicionar novo adminstrador</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<%@include file="../aside.jsp" %>
<div class="container">
    <h1>Adicionar administrador</h1>
    <p>Preencha as informações de novo administrador.</p>
    <form name="forms-add-admin" action="${pageContext.request.contextPath}/admin/adicionar" method="post">
        <h2>Informações do administrador</h2>
        <label>
            Nome
            <input type="text" id="name-input" name="name" placeholder="Insira o nome">
        </label>
        <label>
            E-mail
            <input type="email" id="email-input" oninput="validateForm()" name="email"
                   placeholder="Insira o endereço de email">
        </label>
        <label>
            Senha
            <input type="password" id="password-input" name="password" placeholder="Insira a senha">
        </label>
        <label>
            Telefone
            <input type="text" id="phone-input" name="phone" placeholder="XX XXXXX-XXXX">
        </label>
        <label>
            Data de nascimento
            <input type="date" id="birth-input" name="birth" placeholder="Insira sua data de nascimenot">
        </label>
        <label>
            Cargo
            <input type="text" id="role-input" name="role" placeholder="Insira seu cargo">
        </label>
        <label>
            Foto
            <input type="text" id="picture-input" name="picture" placeholder="Insira a URL da foto">
        </label>

        <span id="errorFeedback"></span>

        <input type="submit" value="Adicionar">
    </form>

    <%
        String errorFeedback = (String) request.getAttribute("errorMessage");

        if (errorFeedback != null && !errorFeedback.isEmpty()) {
    %>

    <div class="message-error">
        <p><strong>Erro: </strong> <%= errorFeedback %></p>
    </div>
    <%
        }
    %>
</div>

<script src="${pageContext.request.contextPath}/assets/js/validator.js"></script>
</body>
</html>
