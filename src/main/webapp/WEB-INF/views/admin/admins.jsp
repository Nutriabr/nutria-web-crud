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
<%@ page import="io.github.nutria.nutria.model.Admin" %>

<%
    List<Admin> adminList = (List<Admin>) request.getAttribute("adminList");
    int totalAdmins = (int) request.getAttribute("totalAdmins");
    int totalPages = (int) request.getAttribute("totalPages");
    int currentPage = (int) request.getAttribute("currentPage");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administradores - Nutria</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tables.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/img/favicon.svg" type="image/x-icon">
</head>
<body>
<div class="page-container">
    <jsp:include page="/WEB-INF/views/components/sidebar.jsp">
        <jsp:param name="activePage" value="admin"/>
    </jsp:include>

    <main class="main-content">
        <header class="page-header">
            <h1>Administradores</h1>
            <p>Gerencie os dados dos administradores.</p>
        </header>

        <section class="stats-card">
            <div class="stat-item">
                <span>Total</span>
                <strong><%= totalAdmins %></strong>
            </div>
        </section>

        <section class="table-section">
            <header class="table-header">
                <h2>Administradores</h2>
                <div class="table-actions">
                    <div class="search-bar">
                        <i class="fa-solid fa-magnifying-glass"></i>
                        <input type="search" placeholder="Buscar">
                    </div>
                    <a href="${pageContext.request.contextPath}/admin/adicionar" class="btn btn-primary">
                        <i class="fa-solid fa-plus"></i>
                        Adicionar novo administrador
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
                        <th>Data de nascimento</th>
                        <th>Cargo</th>
                        <th>Foto</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (Admin admin : adminList) { %>
                    <tr>
                        <td><%= admin.getId() %></td>
                        <td><%= admin.getNome() %></td>
                        <td><%= admin.getEmail() %></td>
                        <td>•••••••</td>
                        <td><%= admin.getTelefone() %></td>
                        <td><%= admin.getNascimento() %></td>
                        <td><%= admin.getCargo() %></td>
                        <td><img src="<%= admin.getFoto() %>" alt="Foto de <%= admin.getNome() %>" class="table-photo"></td>
                        <td class="action-buttons">
                            <a href="${pageContext.request.contextPath}/admin/editar?id=<%= admin.getId()%>" class="btn-action btn-edit">
                                <i class="fa-solid fa-pencil"></i>
                            </a>
                            <button class="btn-action btn-delete" data-id="<%= admin.getId() %>" data-name="<%= admin.getNome() %>">
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
                    <a href="?page=<%= currentPage - 1 %>" class="arrow <%= currentPage <= 1 ? "disabled" : "" %>">
                        <i class="fa-solid fa-chevron-left"></i>
                    </a>
                    <span class="current-page"><%= currentPage %></span>
                    <a href="?page=<%= currentPage + 1 %>" class="arrow <%= currentPage >= totalPages ? "disabled" : "" %>">
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
        <p>Você não poderá recuperar o registro de <strong id="delete-admin-name"></strong> após excluir.</p>
        <div class="popup-actions">
            <button class="btn btn-secondary" id="cancel-delete-btn">Cancelar</button>
            <form id="delete-form" action="${pageContext.request.contextPath}/admin/excluir" method="post">
                <input type="hidden" name="id" id="delete-admin-id">
                <button type="submit" class="btn btn-danger">Excluir</button>
            </form>
        </div>
    </div>
</div>


<script src="${pageContext.request.contextPath}/assets/js/popup.js"></script>

</body>
</html>