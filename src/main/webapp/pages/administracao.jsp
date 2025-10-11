<%@ page import="io.github.nutria.nutria.dao.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <%
        AdminDAO adminDao = new AdminDAO();
        IngredienteDAO ingredienteDAO = new IngredienteDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ReceitaDAO receitaDAO = new ReceitaDAO();
        ReceitaIngredienteDAO receitaIngredienteDAO = new ReceitaIngredienteDAO();
        TabelaNutricionalDAO tabelaNutricionalDAO = new TabelaNutricionalDAO();
        UsuarioDAO usuarioDao = new UsuarioDAO();
    %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Painel de Controle - Nutria</title>
    <link rel="stylesheet" href="../assets/css/admin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<%@include file="components/aside.jsp"%>

<div class="main-content">

    <div class="dashboard">
        <h2>Painel de controle</h2>
        <p>Gerencie seus dados de forma interativa.</p>

        <div class="cards">
            <div class="card">
                <div class="icon">
                    <h3>Usuários</h3>
                    <img src="../assets/img/UserLaranja.png" alt="">
                </div>
                <span class="value"><%= usuarioDao.countAll()%></span>
                <div class="acess">
                    <a href="usuarios.jsp">Acessar</a>
                    <a href="usuarios.jsp"><img src="../assets/img/setaVerde.png" alt=""></a>
                </div>
            </div>

            <div class="card">
                <div class="icon">
                    <h3>Produtos</h3>
                    <img src="../assets/img/IconeDeProdutos.png" alt="">
                </div>
                <span class="value"><%= produtoDAO.countAll()%></span>
                <div class="acess">
                    <a href="#">Acessar</a>
                    <img src="../assets/img/setaVerde.png" alt="">
                </div>
            </div>

            <div class="card">
                <div class="icon">
                    <h3>Administradores</h3>
                    <img src="../assets/img/UserEngre.png" alt="">
                </div>
                <span class="value"><%= adminDao.countAll()%></span>
                <div class="acess">
                    <a href="#">Acessar</a>
                    <img src="../assets/img/setaVerde.png" alt="">
                </div>
            </div>

            <div class="card">
                <div class="icon">
                    <h3>Ingredientes</h3>
                    <img src="../assets/img/IconeIngredientes.png" alt="">
                </div>
                <span class="value"><%= ingredienteDAO.countAll()%></span>
                <div class="acess">
                    <a href="#">Acessar</a>
                    <img src="../assets/img/setaVerde.png" alt="">
                </div>
            </div>

            <div class="card">
                <div class="icon">
                    <h3>Receitas</h3>
                    <img src="../assets/img/UserLaranja.png" alt="">
                </div>
                <span class="value"><%= receitaDAO.countAll()%></span>
                <div class="acess">
                    <a href="#">Acessar</a>
                    <img src="../assets/img/setaVerde.png" alt="">
                </div>
            </div>

            <div class="card">
                <div class="icon">
                    <h3>Receita Ingrediente</h3>
                    <img src="../assets/img/UserLaranja.png" alt="">
                </div>
                <span class="value"><%= receitaIngredienteDAO.countAll()%></span>
                <div class="acess">
                    <a href="#">Acessar</a>
                    <img src="../assets/img/setaVerde.png" alt="">
                </div>
            </div>

            <div class="card">
                <div class="icon">
                    <h3>Tabela Nutricional</h3>
                    <img src="../assets/img/UserLaranja.png" alt="">
                </div>
                <span class="value"><%= tabelaNutricionalDAO.countAll()%></span>
                <div class="acess">
                    <a href="#">Acessar</a>
                    <img src="../assets/img/setaVerde.png" alt="">
                </div>
            </div>
        </div>

        <p class="info">Clique em “Acessar” para acessar cada seção do sistema</p>
    </div>
</div>
</body>
</html>
