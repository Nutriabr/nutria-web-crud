<%@page import="io.github.nutria.nutria.model.ReceitaIngrediente" %>
<%--
  Created by IntelliJ IDEA.
  User: enzomota-ieg
  Date: 11/10/2025
  Time: 02:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Object id = request.getAttribute("id");
    Double quantidade = (Double) request.getAttribute("quantidade");
    String errorMessage = (String) request.getAttribute("errorMessage");
%>

<html>
<head>
    <title>Editar Receita Ingrediente</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/forms.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<%@include file="../components/messagemErro.jsp" %>
<main>
    <%@include file="../components/sidebar.jsp" %>
    <div class="container">
        <div class="main-content">
            <h1>Editar receita ingrediente</h1>
            <p>Preencha as novas informações da receita ingrediente.</p>
        </div>
        <form class="form-content" name="forms-edit-receitaIngrediente" action="${pageContext.request.contextPath}/receitasIngredientes/editar"
              method="post">
            <h2>Informações da receita ingrediente</h2>
            <input type="hidden" name="id" value="<%= id %>">

            <label for="quantidade-input">Quantidade</label>
            <input class="parameter-input" type="number" id="quantidade-input" name="quantidade"
                   value="<%= quantidade%>" placeholder="Insira a quantidade" step="0.1">
            <span id="errorFeedback"></span>

            <div class="submit-content">
                <a href="${pageContext.request.contextPath}/receitasIngredientes/listar" ><button id="btn-cancel">Cancelar</button></a>
                <input id="submit-btn" type="submit" value="Editar">
            </div>

        </form>

    </div>
</main>
<script src="${pageContext.request.contextPath}/assets/js/validador.js"></script>
</body>
</html>
