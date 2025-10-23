<%--.
    Este JSP agora espera receber os dados prontos de um Servlet.
    Atributos esperados:
    - "receitasList": Uma lista de objetos Receita a serem exibidos.
    - "totalReceitas": O número total de receitas (int).
    - "totalPages": O número total de páginas (int).
    - "currentPage": O número da página atual (int).
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="io.github.nutria.nutria.model.Receita" %>

<%
    List<Receita> receitasList = (List<Receita>) request.getAttribute("receitasList");
    int totalReceitas = (int) request.getAttribute("totalReceitas");
    int totalPages = (int) request.getAttribute("totalPages");
    int currentPage = (int) request.getAttribute("currentPage");
    String ordem = (String) request.getAttribute("ordem");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Receitas - Nutria</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tables.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/img/favicon.svg" type="image/x-icon">
</head>
<body>
<div class="page-container">
    <jsp:include page="/WEB-INF/views/components/sidebar.jsp">
        <jsp:param name="activePage" value="receita"/>
    </jsp:include>
    <main class="main-content">
        <header class="page-header">
            <h1>Receitas</h1>
            <p>Gerencie os dados das receitas.</p>
        </header>

        <section class="stats-card">
            <div class="stat-item">
                <span>Total</span>
                <strong><%= totalReceitas %></strong>
            </div>
        </section>

        <section class="table-section">
            <header class="table-header">
                <h2>Receita</h2>
                <div class="table-actions">
                    <div class="search-bar">
                        <i class="fa-solid fa-magnifying-glass"></i>
                        <input type="search" placeholder="Buscar">
                    </div>
                    <a href="${pageContext.request.contextPath}/receita/adicionar" class="btn btn-primary">
                        <i class="fa-solid fa-plus"></i>
                        Adicionar nova receita
                    </a>
                </div>
            </header>

            <div class="table-container">
                <table>
                    <thead>
                    <tr>
                        <th><a href="${pageContext.request.contextPath}/receita/listar?page=<%= currentPage %>&ordem=id">ID</a></th>
                        <th><a href="${pageContext.request.contextPath}/receita/listar?page=<%= currentPage %>&ordem=porcao">Porção</a></th>
                        <th><a href="${pageContext.request.contextPath}/receita/listar?page=<%= currentPage %>&ordem=id_produto">ID-Produto</a></th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (Receita receita : receitasList) { %>
                    <tr>
                        <td><%= receita.getId() %></td>
                        <td><%= receita.getPorcao() %></td>
                        <td><%= receita.getIdProduto() %></td>
                        <td class="action-buttons">
                            <a href="${pageContext.request.contextPath}/receita/editar?id=<%= receita.getId() %>" class="btn-action btn-edit">
                                <i class="fa-solid fa-pencil"></i>
                            </a>
                            <button class="btn-action btn-delete" data-id="<%= receita.getId() %>">
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
                    <a href="?page=<%= currentPage - 1 %>&ordem=<%= ordem %>"
                       class="arrow <%= currentPage <= 1 ? "disabled" : "" %>">
                        <i class="fa-solid fa-chevron-left"></i>
                    </a>
                    <span class="current-page"><%= currentPage %></span>
                    <a href="?page=<%= currentPage + 1 %>&ordem=<%= ordem %>"
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
            <form id="delete-form" action="${pageContext.request.contextPath}/receita/excluir" method="post">
                <input type="hidden" name="input-id" id="input-id">
                <input type="submit" class="btn btn-danger" value="Excluir">
            </form>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/popup.js"></script>

</body>
</html>