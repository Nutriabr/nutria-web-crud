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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/logo.svg" type="image/x-icon">

</head>
<body>
<jsp:include page="/WEB-INF/views/components/sidebar.jsp">
    <jsp:param name="activePage" value="home"/>
</jsp:include>
<div class="main-content">

    <div class="dashboard">
        <h2>Painel de controle</h2>
        <p>Gerencie seus dados de forma interativa.</p>

        <div class="cards">
            <div class="card">
                <div class="icon">
                    <h3>Usuários</h3>
                    <img src="${pageContext.request.contextPath}/assets/img/UserLaranja.png" alt="">
                </div>
                <span class="value"><%= usuarioDao.contarTodos()%></span>
                <div class="acess">
                    <a href="${pageContext.request.contextPath}/usuario/listar">Acessar</a>
                    <a href="${pageContext.request.contextPath}/usuario/listar"><img src="${pageContext.request.contextPath}/assets/img/setaVerde.png" alt=""></a>
                </div>
            </div>

            <div class="card">
                <div class="icon">
                    <h3>Produtos</h3>
                    <img src="${pageContext.request.contextPath}/assets/img/IconeDeProdutos.png" alt="">
                </div>
                <span class="value"><%= produtoDAO.contarTodos()%></span>
                <div class="acess">
                    <a href="${pageContext.request.contextPath}/produto/listar">Acessar</a>
                    <img src="${pageContext.request.contextPath}/assets/img/setaVerde.png" alt="">
                </div>
            </div>

            <div class="card">
                <div class="icon">
                    <h3>Administradores</h3>
                    <img src="${pageContext.request.contextPath}/assets/img/UserEngre.png" alt="">
                </div>
                <span class="value"><%= adminDao.contarTodos()%></span>
                <div class="acess">
                    <a href="${pageContext.request.contextPath}/admin/listar">Acessar</a>
                    <img src="${pageContext.request.contextPath}/assets/img/setaVerde.png" alt="">
                </div>
            </div>

            <div class="card">
                <div class="icon">
                    <h3>Ingredientes</h3>
                    <img src="${pageContext.request.contextPath}/assets/img/IconeIngredientes.png" alt="">
                </div>
                <span class="value"><%= ingredienteDAO.contarTodos()%></span>
                <div class="acess">
                    <a href="${pageContext.request.contextPath}/ingrediente/listar">Acessar</a>
                    <img src="${pageContext.request.contextPath}/assets/img/setaVerde.png" alt="">
                </div>
            </div>

            <div class="card">
                <div class="icon">
                    <h3>Receitas</h3>
                    <img src="${pageContext.request.contextPath}/assets/img/IconeReceita.png" alt="">
                </div>
                <span class="value"><%= receitaDAO.contarTodos()%></span>
                <div class="acess">
                    <a href="${pageContext.request.contextPath}/receita/listar">Acessar</a>
                    <img src="${pageContext.request.contextPath}/assets/img/setaVerde.png" alt="">
                </div>
            </div>

            <div class="card">
                <div class="icon">
                    <h3>Ingredientes da Receita</h3>
                    <img src="${pageContext.request.contextPath}/assets/img/IconeReceitaIngrediente.png" alt="">
                </div>
                <span class="value"><%= receitaIngredienteDAO.contarTodos()%></span>
                <div class="acess">
                    <a href="${pageContext.request.contextPath}/receitasIngredientes/listar">Acessar</a>
                    <a href="${pageContext.request.contextPath}/receitasIngredientes/listar">
                        <img src="${pageContext.request.contextPath}/assets/img/setaVerde.png" alt="">
                    </a>
                </div>
            </div>

            <div class="card">
                <div class="icon">
                    <h3>Tabelas Nutricionais</h3>
                    <img src="${pageContext.request.contextPath}/assets/img/IconeTabela.png" alt="">
                </div>
                <span class="value"><%= tabelaNutricionalDAO.contarTodos()%></span>
                <div class="acess">
                    <a href="${pageContext.request.contextPath}/tabelaNutricional/listar">Acessar</a>
                    <img src="${pageContext.request.contextPath}/assets/img/setaVerde.png" alt="">
                </div>
            </div>
        </div>

        <p class="info">Clique em “Acessar” para acessar cada seção do sistema</p>
    </div>
</div>
</body>
</html>
