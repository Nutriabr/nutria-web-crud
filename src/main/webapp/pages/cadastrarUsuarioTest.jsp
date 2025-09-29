<%--
  Created by IntelliJ IDEA.
  User: luismedeiros-ieg
  Date: 06/09/2025
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teste de cadastro do usuario</title>
</head>
<body>
<form action="/nutria_war_exploded/usuarios/inserir" method="post">
    <input type="text" name="nome" placeholder="Nome" required><br>
    <input type="email" name="email" placeholder="Email" required><br>
    <input type="password" name="senha" placeholder="Senha" required><br>
    <input type="text" name="telefone" placeholder="Telefone" required><br>
    <input type="text" name="empresa" placeholder="Empresa" ><br>
    <input type="text" name="foto" placeholder="foto"><br>
    <button type="submit">Cadastrar</button>
</form>
</body>
</html>
