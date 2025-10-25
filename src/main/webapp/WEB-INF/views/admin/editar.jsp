<%--
  Created by IntelliJ IDEA.
  User: luismedeiros-ieg
  Date: 10/10/2025
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<%
    Object id = request.getAttribute("id");
    String nome = (String) request.getAttribute("nome");
    String email = (String) request.getAttribute("email");
    String telefone = (String) request.getAttribute("telefone");
    Object nascimento = request.getAttribute("nascimento");
    String cargo = (String) request.getAttribute("cargo");
    String foto = (String) request.getAttribute("foto");
    String errorMessage = (String) request.getAttribute("errorMessage");

    String contextPath = request.getContextPath();
%>

<html>
<head>
    <title>Editar Administrador</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= contextPath %>/assets/css/forms.css">
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
            <h1>Editar Administrador</h1>
            <p>Preencha as novas informações do administrador.</p>
        </div>


        <form class="form-content" name="forms-add-admin" action="<%= contextPath %>/admin/editar" method="post">
            <h2>Informações do Administrador</h2>

            <input type="hidden" name="id" value="<%= id %>">

            <label for="name-input">Nome</label>
            <input class="parameter-input" type="text" id="name-input" name="nome" value="<%= nome %>">

            <label for="email-input">E-mail</label>
            <input class="parameter-input" type="email" id="email-input" name="email" oninput="validateForm()" value="<%= email %>">

            <label for="password-input">Nova Senha</label>
            <input class="parameter-input" type="password" id="password-input" name="senha" placeholder="Deixe em branco para não alterar">

            <label for="phone-input">Telefone</label>
            <input class="parameter-input" type="text" id="phone-input" name="telefone" value="<%= telefone %>">

            <label for="birth-input">Data de nascimento</label>
            <input class="parameter-input" type="date" id="birth-input" name="nascimento" value="<%= nascimento %>">

            <label for="role-input">Cargo</label>
            <input class="parameter-input" type="text" id="role-input" name="cargo" value="<%= cargo %>">

            <label for="picture-input">Foto (URL)</label>
            <input class="parameter-input" type="text" id="picture-input" name="foto" value="<%= foto %>">

            <div class="submit-content">
                <a href="<%= contextPath %>/admin/listar" id="btn-cancel">Cancelar</a>
                <input id="submit-btn" type="submit" value="Salvar Alterações">
            </div>
        </form>

<%--        <% } %>--%>
    </div>
</main>
<script src="${pageContext.request.contextPath}/assets/js/validador.js"></script>

</body>
</html>