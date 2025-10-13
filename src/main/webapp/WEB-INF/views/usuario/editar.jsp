<%@ page import="io.github.nutria.nutria.model.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: enzomota-ieg
  Date: 11/10/2025
  Time: 02:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar usuário</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/forms.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<%@include file="../components/messagemErro.jsp" %>

<%
    Usuario usuario = (Usuario) request.getAttribute("usuario");
%>
<main>
    <%@include file="../components/sidebar.jsp" %>
    <div class="container">
        <div class="main-content">
            <h1>Editar usuário</h1>
            <p>Preencha as novas informações do usuário.</p>
        </div>
        <form class="form-content" name="forms-edit-usuario" action="${pageContext.request.contextPath}/usuario/atualizar"
              method="post">
            <h2>Informações do usuário</h2>
            <label for="name-input">Nome</label>
            <input class="parameter-input" type="text" id="name-input" name="name" value="<%= usuario.getNome()%>>" placeholder="Insira o nome">

            <label for="email-input">E-mail</label>
            <input class="parameter-input" type="email" id="email-input" oninput="validateForm()" name="email"
                   value="<%= usuario.getEmail()%>" placeholder="Insira o endereço de email">
            <span id="errorFeedback"></span>


            <label for="password-input">Senha</label>
            <input class="parameter-input" type="password" id="password-input" name="password"
                   value="<%= usuario.getSenha()%>" placeholder="Insira a senha">

            <label for="phone-input">Telefone</label>
            <input class="parameter-input" type="text" id="phone-input" name="phone"
                   value="<%= usuario.getTelefone()%>" placeholder="XX XXXXX-XXXX">

            <label for="company-input">Empresa</label>
            <input class="parameter-input" type="text" id="company-input" name="company"
                   value="<%= usuario.getEmpresa()%>" placeholder="Insira seu cargo">

            <label for="picture-input">Foto</label>
            <input class="parameter-input" type="text" id="picture-input" name="picture"
                   value="<%= usuario.getFoto()%>" placeholder="Insira a URL da foto">

            <div class="submit-content">
                <a href="${pageContext.request.contextPath}/usuario/listar" ><button id="btn-cancel">Cancelar</button></a>
                <input id="submit-btn" type="submit" value="Editar">
            </div>

        </form>
    </div>
</main>
<script src="${pageContext.request.contextPath}/assets/js/validator.js"></script>
</body>
</html>
