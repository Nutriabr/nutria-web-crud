<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<%
  Object id = request.getAttribute("id");
  String nome = (String) request.getAttribute("nome");
  String contextPath = request.getContextPath();
%>

<html>
<head>
  <title>Editar Ingrediente</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="<%= contextPath %>/assets/css/forms.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<%@include file="../components/messagemErro.jsp"%>
<main>
  <jsp:include page="/WEB-INF/views/components/sidebar.jsp">
    <jsp:param name="activePage" value="ingrediente"/>
  </jsp:include>
  <div class="container">
    <div class="main-content">
      <h1>Editar Ingrediente</h1>
      <p>Preencha as novas informações do ingrediente.</p>
    </div>


    <form class="form-content" name="forms-add-ingrediente" action="<%= contextPath %>/ingrediente/editar" method="post">
      <h2>Informações do Ingrediente</h2>

      <input type="hidden" name="id" value="<%= id %>">

      <label for="nome-input">Nome</label>
      <input class="parameter-input" type="text" id="nome-input" name="nome" value="<%= nome %>">

      <div class="submit-content">
        <a href="<%= contextPath %>/ingrediente/listar" id="btn-cancel">Cancelar</a>
        <input id="submit-btn" type="submit" value="Salvar Alterações">
      </div>
    </form>

  </div>
</main>
<script src="${pageContext.request.contextPath}/assets/js/validator.js"></script>

</body>
</html>
