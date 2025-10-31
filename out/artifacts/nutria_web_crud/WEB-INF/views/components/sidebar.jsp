<%--
  Created by IntelliJ IDEA.
  User: luismedeiros-ieg
  Date: 09/10/2025
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Painel de Controle - Nutria</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sidebar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<%
    String activePage = request.getParameter("activePage");
%>
<div class="sidebar">
    <img id="nutria" src="${pageContext.request.contextPath}/assets/img/logo.svg" alt="">
    <div class="user">
        <i class="fa-solid fa-user-circle"></i>
        <span><%
            String nome = (String) session.getAttribute("adminNome");
            if (nome != null) {
                int primeiroEspaco = nome.indexOf(" ");
                int segundoEspaco = nome.indexOf(" ", primeiroEspaco + 1);

                if (primeiroEspaco != -1 && segundoEspaco != -1) {
                    out.print(nome.substring(0, segundoEspaco));
                } else {
                    out.print(nome);
                }

            } else {
                out.print("Administrador");
            }
        %></span>
    </div>

    <hr>

    <ul class="menu">
        <li class="<% if ("home".equals(activePage)) {out.print("active"); } %>"><i class="fa-solid fa-house"></i><a href="${pageContext.request.contextPath}/home">Início</a></li>
        <li class="<% if ("usuario".equals(activePage)) {out.print("active"); } %>"><i class="fa-solid fa-users"></i> <a href="${pageContext.request.contextPath}/usuario/listar">Usuários</a></li>
        <li class="<% if ("produto".equals(activePage)) {out.print("active"); } %>"><i class="fa-solid fa-box"></i><a href="${pageContext.request.contextPath}/produto/listar"> Produtos</a></li>
        <li class="<% if ("admin".equals(activePage)) {out.print("active"); } %>"><i class="fa-solid fa-user-gear"></i><a href="${pageContext.request.contextPath}/admin/listar"> Administradores</a></li>
        <li class="<% if ("ingrediente".equals(activePage)) {out.print("active"); } %>"><i class="fa-solid fa-carrot"></i><a href="${pageContext.request.contextPath}/ingrediente/listar"> Ingredientes</a></li>
        <li class="<% if ("tabela_nutricional".equals(activePage)) {out.print("active"); } %>"><i class="fa-solid fa-list"></i><a href="${pageContext.request.contextPath}/tabela_nutricional/listar"> Tabela Nutricional</a></li>
        <li class="<% if ("receita".equals(activePage)) {out.print("active"); } %>"><i class="fa-solid fa-utensils"></i><a href="${pageContext.request.contextPath}/receita/listar"> Receitas</a></li>
        <li class="<% if ("ingrediente_receita".equals(activePage)) {out.print("active"); } %>"><i class="fa-solid fa-clipboard-list"></i><a href="${pageContext.request.contextPath}/receitasIngredientes/listar"> Ingredientes da Receita</a></li>
    </ul>

    <form class="logout" action="${pageContext.request.contextPath}/logout" method="post">
        <button type="submit">Encerrar sessão</button>
        <i class="fa-solid fa-right-from-bracket"></i>
    </form>
</div>

</body>
</html>
