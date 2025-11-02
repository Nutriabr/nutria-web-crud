<%--
  Created by IntelliJ IDEA.
  User: marianafelis-ieg
  Date: 17/10/2025
  Time: 07:33
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>Adicionar nova receita</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/forms.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/logo.svg" type="image/x-icon">
</head>
<body>
<%@include file="../components/messagemErro.jsp"%>
<main>
    <jsp:include page="/WEB-INF/views/components/sidebar.jsp">
        <jsp:param name="activePage" value="receita"/>
    </jsp:include>
    <div class="container">
        <div class="main-content">
            <h1>Adicionar receita</h1>
            <p>Preencha as informações da nova receita.</p>
        </div>
        <form class="form-content" name="forms-add-admin" action="${pageContext.request.contextPath}/receita/adicionar"
              method="post">
            <h2>Informações da receita</h2>
            <label for="porcao-input">Porção*</label>
            <input class="parameter-input" type="text" id="porcao-input" name="porcao" placeholder="Insira a porção" required>
            <span id="errorFeedback"></span>
            <label for="id-produto-input">ID-Produto*</label>
            <input class="parameter-input" type="text" id="id-produto-input" name="id-produto" placeholder="Insira o ID Produto" required>
            <div class="submit-content">
                <a href="${pageContext.request.contextPath}/receita/listar" id="btn-cancel">Cancelar</a>
                <input id="submit-btn" type="submit" value="Adicionar">
            </div>
        </form>
    </div>
</main>
<script src="${pageContext.request.contextPath}/assets/js/validador.js"></script>
</body>
</html>