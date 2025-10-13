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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/forms.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<%@include file="../components/messagemErro.jsp"%>
<main>
    <jsp:include page="/WEB-INF/views/components/sidebar.jsp">
        <jsp:param name="activePage" value="admin"/>
    </jsp:include>
    <div class="container">
        <div class="main-content">
            <h1>Adicionar administrador</h1>
            <p>Preencha as informações de novo administrador.</p>
        </div>
        <form class="form-content" name="forms-add-admin" action="${pageContext.request.contextPath}/admin/adicionar"
              method="post">
            <h2>Informações do administrador</h2>
            <label for="name-input">Nome</label>
            <input class="parameter-input" type="text" id="name-input" name="name" placeholder="Insira o nome" required>

            <label for="email-input">E-mail</label>
            <input class="parameter-input" type="email" id="email-input" oninput="validateForm()" name="email"
                   placeholder="Insira o endereço de email" required>
            <span id="errorFeedback"></span>


            <label for="password-input">Senha</label>
            <input class="parameter-input" type="password" id="password-input" name="password"
                   placeholder="Insira a senha" required>

            <label for="phone-input">Telefone</label>
            <input class="parameter-input" type="text" id="phone-input" name="phone" placeholder="XX XXXXX-XXXX" required>

            <label for="birth-input">Data de nascimento</label>
            <input class="parameter-input" type="date" id="birth-input" name="birth"
                   placeholder="Insira sua data de nascimento" required>

            <label for="role-input">Cargo</label>
            <input class="parameter-input" type="text" id="role-input" name="role" placeholder="Insira seu cargo" required>

            <label for="picture-input">Foto</label>
            <input class="parameter-input" type="text" id="picture-input" name="picture"
                   placeholder="Insira a URL da foto">

            <div class="submit-content">
                <a href="${pageContext.request.contextPath}/admin/listar" id="btn-cancel">Cancelar</a>
                <input id="submit-btn" type="submit" value="Adicionar">
            </div>
        </form>
    </div>
</main>
<script src="${pageContext.request.contextPath}/assets/js/validator.js"></script>
</body>
</html>
