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
  <title>Adicionar novo ingrediente</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/forms.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<%@include file="../components/messagemErro.jsp"%>
<main>
  <jsp:include page="/WEB-INF/views/components/sidebar.jsp">
    <jsp:param name="activePage" value="produto"/>
  </jsp:include>
  <div class="container">
    <div class="main-content">
      <h1>Adicionar ingrediente</h1>
      <p>Preencha as informações da novo ingrediente.</p>
    </div>
    <form class="form-content" name="forms-add-admin" action="${pageContext.request.contextPath}/ingrediente/adicionar"
          method="post">
      <h2>Informações do ingrediente</h2>
      <label for="nome-input">Nome</label>
      <input class="parameter-input" type="text" id="nome-input" name="nome" placeholder="Insira o nome" required>
      <span id="errorFeedback"></span>
      <div class="submit-content">
        <a href="${pageContext.request.contextPath}/ingrediente/listar" id="btn-cancel">Cancelar</a>
        <input id="submit-btn" type="submit" value="Adicionar">
      </div>
    </form>
  </div>
</main>
<script src="${pageContext.request.contextPath}/assets/js/validator.js"></script>
</body>
</html>