<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="io.github.nutria.nutria.model.TabelaNutricional" %>

<%
  List<TabelaNutricional> tabelaNutricionalList = (List<TabelaNutricional>) request.getAttribute("tabelaNutricionalList");
  int totalTabelasNutricionais = (int) request.getAttribute("totalTabelasNutricionais");
  int totalPages = (int) request.getAttribute("totalPages");
  int currentPage = (int) request.getAttribute("currentPage");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Tabelas Nutricionais - Nutria</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tables.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/logo.svg" type="image/x-icon">
</head>
<body>
<%@include file="../components/mensagemSucesso.jsp" %>
<div class="page-container">
    <jsp:include page="/WEB-INF/views/components/sidebar.jsp">
        <jsp:param name="activePage" value="tabelaNutricional"/>
    </jsp:include>

    <main class="main-content">
        <header class="page-header">
            <h1>Tabelas Nutricionais</h1>
            <p>Gerencie os dados das Tabelas Nutricionais.</p>
        </header>

        <section class="stats-card">
            <div class="stat-item">
                <span>Total</span>
                <strong><%= totalTabelasNutricionais %></strong>
            </div>
        </section>

        <section class="table-section">
            <header class="table-header">
                <h2>Tabelas Nutricionais</h2>
                <div class="table-actions">
                    <div class="search-bar">
                        <i class="fa-solid fa-magnifying-glass"></i>
                        <input type="search" placeholder="Buscar">
                    </div>
                    <a href="${pageContext.request.contextPath}/tabelaNutricional/adicionar" class="btn btn-primary">
                        <i class="fa-solid fa-plus"></i>
                        Adicionar nova Tabela Nutricional
                    </a>
                </div>
            </header>

            <div class="table-container">
                <table>
                    <thead>
                    <tr>
                        <th>ID do ingrediente</th>
                        <th>Valor energético (kcal)</th>
                        <th>Carboidratos (g)</th>
                        <th>Açúcares totais (g)</th>
                        <th>Açúcares Adicionados (g)</th>
                        <th>Proteínas (g)</th>
                        <th>Gorduras totais (g)</th>
                        <th>Gorduras saturadas (g)</th>
                        <th>Gorduras trans (g)</th>
                        <th>Fibra alimentar (G)</th>
                        <th>Sódio (mg)</th>
                        <th>Colesterol (mg)</th>
                        <th>Vitamina A (µg)</th>
                        <th>Vitamina C (mg)</th>
                        <th>Vitamina D (µg)</th>
                        <th>Cálcio (mg)</th>
                        <th>Ferro (mg)</th>
                        <th>Potássio (mg)</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (TabelaNutricional tabelaNutricional : tabelaNutricionalList) { %>
                    <tr>
                        <td><%= tabelaNutricional.getIdIngrediente() %></td>
                        <td><%= tabelaNutricional.getValorEnergeticoKcal() %></td>
                        <td><%= tabelaNutricional.getCarboidratosG() %></td>
                        <td><%= tabelaNutricional.getAcucaresTotaisG() %></td>
                        <td><%= tabelaNutricional.getAcucaresAdicionadosG() %></td>
                        <td><%= tabelaNutricional.getProteinasG() %></td>
                        <td><%= tabelaNutricional.getGordurasTotaisG() %></td>
                        <td><%= tabelaNutricional.getGordurasSaturadasG() %></td>
                        <td><%= tabelaNutricional.getGordurasTransG() %></td>
                        <td><%= tabelaNutricional.getFibraAlimentarG() %></td>
                        <td><%= tabelaNutricional.getSodioMg() %></td>
                        <td><%= tabelaNutricional.getColesterolMg() %></td>
                        <td><%= tabelaNutricional.getVitaminaAMcg() %></td>
                        <td><%= tabelaNutricional.getVitaminaCMg() %></td>
                        <td><%= tabelaNutricional.getVitaminaDMcg() %></td>
                        <td><%= tabelaNutricional.getCalcioMg() %></td>
                        <td><%= tabelaNutricional.getFerroMg() %></td>
                        <td><%= tabelaNutricional.getPotassioMg() %></td>
                        <td class="action-buttons">
                            <a href="${pageContext.request.contextPath}/tabelaNutricional/editar?id=<%= tabelaNutricional.getIdIngrediente() %>" class="btn-action btn-edit">
                                <i class="fa-solid fa-pencil"></i>
                            </a>
                            <button class="btn-action btn-delete" data-id="<%= tabelaNutricional.getIdIngrediente() %>">
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
        <p>Você não poderá recuperar o registro de ID <strong id="delete-id"></strong> após excluir.</p>
        <div class="popup-actions">
            <button class="btn btn-secondary" id="cancel-delete-btn">Cancelar</button>
            <form id="delete-form" action="${pageContext.request.contextPath}/tabelaNutricional/excluir" method="post">
                <input type="hidden" name="input-id" id="input-id">
                <input type="submit" class="btn btn-danger" value="Excluir">
            </form>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/popup.js"></script>

</body>
</html>