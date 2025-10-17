<%--
    Refatorado por seu Desenvolvedor Frontend.
    Este JSP agora espera receber os dados prontos de um Servlet.
    Atributos esperados:
    - "receitaIngredienteList": Uma lista de objetos ReceitaIngrediente a serem exibidos.
    - "totalReceitasIngrediente": O número total de Receitas Ingredientes (int).
    - "totalPages": O número total de páginas (int).
    - "currentPage": O número da página atual (int).
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="io.github.nutria.nutria.model.ReceitaIngrediente" %>

<%
    List<ReceitaIngrediente> receitaIngredienteList = (List<ReceitaIngrediente>) request.getAttribute("receitaIngredienteList");
    int totalReceitasIngrediente = (int) request.getAttribute("totalReceitasIngrediente");
    int totalPages = (int) request.getAttribute("totalPages");
    int currentPage = (int) request.getAttribute("currentPage");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Receitas Ingrediente - Nutria</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tables.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/img/favicon.svg" type="image/x-icon">
</head>
<body>
<div class="page-container">
    <jsp:include page="/WEB-INF/views/components/sidebar.jsp">
        <jsp:param name="activePage" value="receitaIngrediente"/>
    </jsp:include>



    <main class="main-content">
        <header class="page-header">
            <h1>Receitas Ingrediente</h1>
            <p>Gerencie os dados das Receitas Ingrediente.</p>
        </header>

        <section class="stats-card">
            <div class="stat-item">
                <span>Total</span>
                <strong><%= totalReceitasIngrediente %></strong>
            </div>
        </section>

        <section class="table-section">
            <header class="table-header">
                <h2>Receita Ingrediente</h2>
                <div class="table-actions">
                    <div class="search-bar">
                        <i class="fa-solid fa-magnifying-glass"></i>
                        <input type="search" placeholder="Buscar">
                    </div>
                    <a href="${pageContext.request.contextPath}/receitasIngredientes/adicionar" class="btn btn-primary">
                        <i class="fa-solid fa-plus"></i>
                        Adicionar nova Receita Ingrediente
                    </a>
                </div>
            </header>

            <div class="table-container">
                <table>
                    <thead>
                    <tr>
                        <th>ID Receita</th>
                        <th>ID Ingrediente</th>
                        <th>quantidade</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (ReceitaIngrediente receitaIngrediente : receitaIngredienteList) { %>
                    <tr>
                        <td><%= receitaIngrediente.getIdReceita() %></td>
                        <td><%= receitaIngrediente.getIdIngrediente() %></td>
                        <td><%= receitaIngrediente.getQuantidade() %></td>
                        <td class="action-buttons">
                            <a href="${pageContext.request.contextPath}/receitasIngredientes/editar?idReceita=<%= receitaIngrediente.getIdReceita() %>&idIngrediente=<%= receitaIngrediente.getIdIngrediente() %>" class="btn-action btn-edit">
                                <i class="fa-solid fa-pencil"></i>
                            </a>
                            <button class="btn-action btn-delete" data-id="<%%>" data-name="<%%>">
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
        <p>Você não poderá recuperar o registro de <strong id="delete-receitaIngrediente-name"></strong>após excluir.</p>
        <div class="popup-actions">
            <button class="btn btn-secondary" id="cancel-delete-btn">Cancelar</button>
            <form id="delete-form" action="${pageContext.request.contextPath}/receitasIngredientes/excluir" method="post">
                <input type="hidden" name="id" id="receitasIngredientes-admin-id">
                <button type="submit" class="btn btn-danger">Excluir</button>
            </form>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/popup.js"></script>

</body>
</html>