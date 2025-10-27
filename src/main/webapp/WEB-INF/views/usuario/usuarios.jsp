<%--
    Refatorado por seu Desenvolvedor Frontend.
    Este JSP agora espera receber os dados prontos de um Servlet.
    Atributos esperados:
    - "adminList": Uma lista de objetos Admin a serem exibidos.
    - "totalAdmins": O número total de administradores (int).
    - "totalPages": O número total de páginas (int).
    - "currentPage": O número da página atual (int).
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="io.github.nutria.nutria.model.Usuario" %>

<%
    List<Usuario> usuarioList = (List<Usuario>) request.getAttribute("usuarioList");
    int totalUsuarios = (int) request.getAttribute("totalUsuarios");
    int totalPages = (int) request.getAttribute("totalPages");
    int currentPage = (int) request.getAttribute("currentPage");
    String busca = (String) request.getAttribute("busca");
    String buscaParam = (busca != null && !busca.trim().isEmpty()) ? "&busca=" + busca : "";
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Usuários - Nutria</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tables.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/img/favicon.svg" type="image/x-icon">
</head>
<body>
<%@include file="../components/mensagemSucesso.jsp" %>
<div class="page-container">
    <jsp:include page="/WEB-INF/views/components/sidebar.jsp">
        <jsp:param name="activePage" value="usuario"/>
    </jsp:include>


    <main class="main-content">
        <header class="page-header">
            <h1>Usuários</h1>
            <p>Gerencie os dados dos usuários.</p>
        </header>

        <section class="stats-card">
            <div class="stat-item">
                <span>Total</span>
                <strong><%= totalUsuarios %></strong>
            </div>
        </section>

        <section class="table-section">
            <header class="table-header">
                <h2>Usuário</h2>
                <div class="table-actions">
                        <form id="form-busca"  action="${pageContext.request.contextPath}/usuario/listar" method="get" class="search-bar">
                            <i class="fa-solid fa-magnifying-glass"></i>
                            <input type="search" placeholder="Buscar" id="input-busca"  name="busca" value="<%= busca != null ? busca : "" %>">
                        </form>
                    <a href="${pageContext.request.contextPath}/usuario/adicionar" class="btn btn-primary">
                        <i class="fa-solid fa-plus"></i>
                        Adicionar novo usuário
                    </a>
                </div>
            </header>

            <div class="table-container">
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Endereço de email</th>
                        <th>Senha</th>
                        <th>Telefone</th>
                        <th>Empresa</th>
                        <th>Foto</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (Usuario usuario : usuarioList) { %>
                    <tr>
                        <td><%= usuario.getId() %></td>
                        <td><%= usuario.getNome() %></td>
                        <td><%= usuario.getEmail() %></td>
                        <td>•••••••</td>
                        <td><%= usuario.getTelefone() %></td>
                        <td><%= usuario.getEmpresa() %></td>
                        <td><img src="<%= usuario.getFoto() %>" alt="Foto de <%= usuario.getNome() %>" class="table-photo"></td>
                        <td class="action-buttons">
                            <a href="${pageContext.request.contextPath}/usuario/editar?id=<%= usuario.getId() %>" class="btn-action btn-edit">
                                <i class="fa-solid fa-pencil"></i>
                            </a>
                            <button class="btn-action btn-delete" data-id="<%= usuario.getId() %>" data-name="<%= usuario.getNome() %>">
                                <i class="fa-solid fa-trash-can"></i>
                            </button>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

            <footer class="table-footer">
                <span>Página <%= currentPage %> de <%= totalPages %></span>
                <nav class="pagination">
                    <a href="${pageContext.request.contextPath}/usuario/listar?page=<%= currentPage - 1 %><%= buscaParam %>"
                       class="arrow <%= currentPage <= 1 ? "disabled" : "" %>">
                        <i class="fa-solid fa-chevron-left"></i>
                    </a>

                    <span class="current-page"><%= currentPage %></span>

                    <a href="${pageContext.request.contextPath}/usuario/listar?page=<%= currentPage + 1 %><%= buscaParam %>"
                       class="arrow <%= currentPage >= totalPages ? "disabled" : "" %>">
                        <i class="fa-solid fa-chevron-right"></i>
                    </a>
                </nav>
            </footer>
        </section>
    </main>
</div>


<div class="overlay" id="delete-popup-overlay" style="display: none;">
    <div class="popup-container">
        <h1>Você tem certeza que deseja excluir este registro?</h1>
        <p>Você não poderá recuperar o registro de ID <strong id="delete-id"></strong> após excluir.</p>
        <div class="popup-actions">
            <button class="btn btn-secondary" id="cancel-delete-btn">Cancelar</button>
            <form id="delete-form" action="${pageContext.request.contextPath}/usuario/excluir" method="post">
                <input type="hidden" name="input-id" id="input-id">
                <input type="submit" class="btn btn-danger" value="Excluir">
            </form>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/popup.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/formAutoSubmit.js"></script>

</body>
</html>