<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<%
  Object id = request.getAttribute("id");
  String porcao = (String) request.getAttribute("porcao");
  Object idProduto = request.getAttribute("idProduto");
  String contextPath = request.getContextPath();
%>

<html>
<head>
  <title>Editar Receita</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="<%= contextPath %>/assets/css/forms.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<%@include file="../components/messagemErro.jsp"%>
<main>
  <jsp:include page="/WEB-INF/views/components/sidebar.jsp">
    <jsp:param name="activePage" value="receita"/>
  </jsp:include>
  <div class="container">
    <div class="main-content">
      <h1>Editar Receita</h1>
      <p>Preencha as novas informações da receita.</p>
    </div>


    <form class="form-content" name="forms-add-receita" action="<%= contextPath %>/receita/editar" method="post">
      <h2>Informações da Receita</h2>

      <input type="hidden" name="id" value="<%= id %>">

      <label for="porcao-input">Porção</label>
      <input class="parameter-input" type="text" id="porcao-input" name="porcao" value="<%= porcao %>">

      <input type="hidden" name="idProduto" value="<%= idProduto %>">

      <div class="submit-content">
        <a href="<%= contextPath %>/receita/listar" id="btn-cancel">Cancelar</a>
        <input id="submit-btn" type="submit" value="Salvar Alterações">
      </div>
    </form>

    <%--        <% } %>--%>
  </div>
</main>
<script src="${pageContext.request.contextPath}/assets/js/validator.js"></script>

</body>
</html>
