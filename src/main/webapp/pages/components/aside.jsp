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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/aside.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<div class="sidebar">
    <img id="nutria" src="${pageContext.request.contextPath}/assets/img/logo.svg" alt="">
    <div class="user">
        <i class="fa-solid fa-user-circle"></i>
        <span><%
            String nome = (String) session.getAttribute("adminName");
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
        <li class="active"><i class="fa-solid fa-house"></i> Início</li>
        <li><i class="fa-solid fa-users"></i> Usuários</li>
        <li><i class="fa-solid fa-box"></i> Produtos</li>
        <li><i class="fa-solid fa-user-gear"></i> Administradores</li>
        <li><i class="fa-solid fa-carrot"></i> Ingredientes</li>
        <li><i class="fa-solid fa-list"></i> Tabela Nutricional</li>
        <li><i class="fa-solid fa-utensils"></i> Receitas</li>
        <li><i class="fa-solid fa-clipboard-list"></i> Ingredientes da Receita</li>
    </ul>

    <form class="logout" action="${pageContext.request.contextPath}/logout" method="post">
        <button type="submit">Encerrar sessão</button>
        <i class="fa-solid fa-right-from-bracket"></i>
    </form>
</div>

</body>
</html>
