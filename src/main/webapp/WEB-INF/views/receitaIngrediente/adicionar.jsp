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
    <title>Menu de Adicionar Receita Ingrediente</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/forms.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<%@include file="../components/messagemErro.jsp" %>
<main>
    <jsp:include page="/WEB-INF/views/components/sidebar.jsp">
        <jsp:param name="activePage" value="receitaIngrediente"/>
    </jsp:include>
    <div class="container">
        <div class="main-content">
            <h1>Adicionar Receita Ingrediente</h1>
            <p>Preencha as informações da nova Receita.</p>
        </div>
        <form class="form-content" name="forms-add-admin" action="${pageContext.request.contextPath}/receitasIngredientes/adicionar"
              method="post">
            <h2>Informações da receita</h2>
            <label for="id-receita-input">ID Receita</label>
            <input class="parameter-input" type="number" id="id-receita-input" name="idReceita"
                   placeholder="Insira o ID da receita" required>

            <label for="id-ingrediente-input">ID Ingrediente</label>
            <input class="parameter-input" type="number" id="id-ingrediente-input"  name="idIngrediente"
                   placeholder="Insira o ID do Ingrediente" required>


            <label for="quantity-input">Quantidade</label>
            <input class="parameter-input" type="number" id="quantity-input" name="quantity"
                   placeholder="Insira a quantidade" required>

            <div class="submit-content">
                <a href="${pageContext.request.contextPath}/usuario/listar" id="btn-cancel">Cancelar</a>
                <input id="submit-btn" type="submit" value="Adicionar">
            </div>
        </form>
    </div>
</main>
<script src="${pageContext.request.contextPath}/assets/js/validator.js"></script>
</body>
</html>
