<%--
    Refatorado por seu Desenvolvedor Frontend.
    Este JSP agora espera receber os dados prontos de um Servlet.
    Atributos esperados:
    - "tabelaNutricionalList": Uma lista de objetos TabelaNutricional a serem exibidos.
    - "totalTabelasNutricionais": O número total de tabelas nutricionais (int).
    - "totalPages": O número total de páginas (int).
    - "currentPage": O número da página atual (int).
--%>
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
  <link rel="icon" href="${pageContext.request.contextPath}/assets/img/favicon.svg" type="image/x-icon">
</head>
<body>
<div class="page-container">
    <jsp:include page="/WEB-INF/views/components/sidebar.jsp">
        <jsp:param name="activePage" value="tabela_nutricional"/>
    </jsp:include>

    <main class="main-content">
        <header class="page-header">
            <h1>Tabelas Nutricionais</h1>
            <p>Gerencie os dados das tabelas nutricionais.</p>
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
                    <a href="${pageContext.request.contextPath}/tabela_nutricional/adicionar" class="btn btn-primary">
                        <i class="fa-solid fa-plus"></i>
                        Adicionar nova tabela nutricional
                    </a>
                </div>
            </header>

            <div class="table-container">
                <table>
                    <thead>
                    <tr>
                        <th>ID Ingrediente</th>
                        <th>Valor Energético (KCAL)</th>
                        <th>Carboidratos (G)</th>
                        <th>Açúcares Totais (G)</th>
                        <th>Açúcares Adicionados (G)</th>
                        <th>Proteínas (G)</th>
                        <th>Gorduras Totais (G)</th>
                        <th>Gorduras Saturadas (G)</th>
                        <th>Gorduras Trans (G)</th>
                        <th>Fibra Alimentar (G)</th>
                        <th>Sódio (MG)</th>
                        <th>Colesterol (MG)</th>
                        <th>Vitamina A (MCG)</th>
                        <th>Vitamina C (MG)</th>
                        <th>Vitamina D (MCG)</th>
                        <th>Cálcio (MG)</th>
                        <th>Ferro (MG)</th>
                        <th>Potássio (MG)</th>
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
                            <a href="${pageContext.request.contextPath}/tabela_nutricional/editar?id=<%= tabelaNutricional.getIdIngrediente() %>" class="btn-action btn-edit">
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
    <p>Você não poderá recuperar o registro de <strong id="delete-usuario-name"></strong>após excluir.</p>
    <div class="popup-actions">
      <button class="btn btn-secondary" id="cancel-delete-btn">Cancelar</button>
      <form id="delete-form" action="${pageContext.request.contextPath}/usuario/excluir" method="post">
        <input type="hidden" name="id" id="usuario-admin-id">
        <button type="submit" class="btn btn-danger">Excluir</button>
      </form>
    </div>
  </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/popup.js"></script>

</body>
</html>