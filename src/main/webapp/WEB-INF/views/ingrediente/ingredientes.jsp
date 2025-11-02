<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="io.github.nutria.nutria.model.Ingrediente" %>

<%
  List<Ingrediente> ingredientesList = (List<Ingrediente>) request.getAttribute("ingredientesList");
  int totalIngredientes = (int) request.getAttribute("totalIngredientes");
  int totalPages = (int) request.getAttribute("totalPages");
  int currentPage = (int) request.getAttribute("currentPage");
  List<String> nomesList = (List<String>) request.getAttribute("nomesList");
  String filtro = (String) request.getAttribute("filtro");
  String buscaParam = (filtro != null && !filtro.trim().isEmpty()) ? "&busca=" + filtro : "";
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Ingredientes - Nutria</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tables.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
  <link rel="icon" href="${pageContext.request.contextPath}/assets/img/favicon.svg" type="image/x-icon">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/logo.svg" type="image/x-icon">
</head>
<body>
<%@include file="../components/mensagemSucesso.jsp" %>
<div class="page-container">
  <jsp:include page="/WEB-INF/views/components/sidebar.jsp">
    <jsp:param name="activePage" value="ingrediente"/>
  </jsp:include>
  <main class="main-content">
    <header class="page-header">
      <h1>Ingredientes</h1>
      <p>Gerencie os dados dos ingredientes.</p>
    </header>

    <section class="stats-card">
      <div class="stat-item">
        <span>Total</span>
        <strong><%= totalIngredientes %></strong>
      </div>
    </section>

    <section class="table-section">
      <header class="table-header">
        <h2>Ingredientes</h2>
        <div class="table-actions">
          <form action="${pageContext.request.contextPath}/ingrediente/listar" method="get" class="search-bar">
            <i class="fa-solid fa-magnifying-glass"></i>
            <input type="search" name="busca" placeholder="Buscar" value="<%= filtro != null ? filtro : "" %>">
          </form>

          <button class="btn-action btn-filter" id="btn-filter">
            <i class="fa-solid fa-filter" name="filter"></i>
          </button>

          <a href="${pageContext.request.contextPath}/ingrediente/adicionar" class="btn btn-primary">
            <i class="fa-solid fa-plus"></i>
            Adicionar novo ingrediente
          </a>
        </div>
      </header>

      <div class="delete-popup" style="display: none">
        <h2>Empresa</h2>
        <div class="delete-popup-content">
          <form action="${pageContext.request.contextPath}/ingrediente/excluir" method="post">
            <input type="hidden" name="acao" value="deletarPorNome">
            <select name="opcao" id="selectEmpresa">
              <% for (int i = 0; i < nomesList.size(); i++) { %>
              <option value="<%= nomesList.get(i)%>" id="empresa-opcao"><%= nomesList.get(i)%></option>
              <% } %>
            </select>

            <button type="submit" class="btn-action deletar-por-empresa">Deletar todos os registros</button>
          </form>
        </div>
      </div>

      <div class="table-container">
        <table>
          <thead>
          <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Ações</th>
          </tr>
          </thead>
          <tbody>
          <% for (Ingrediente ingrediente : ingredientesList) { %>
          <tr>
            <td><%= ingrediente.getId() %></td>
            <td><%= ingrediente.getNome() %></td>
            <td class="action-buttons">
              <a href="${pageContext.request.contextPath}/ingrediente/editar?id=<%= ingrediente.getId() %>" class="btn-action btn-edit">
                <i class="fa-solid fa-pencil"></i>
              </a>

              <!-- deletar por id -->
              <form action="${pageContext.request.contextPath}/ingrediente/excluir" method="post" style="display:inline-block">
                <input type="hidden" name="input-id" value="<%= ingrediente.getId() %>">
                <button type="submit" class="btn-action btn-delete">
                  <i class="fa-solid fa-trash-can"></i>
                </button>
              </form>

            </td>
          </tr>
          <% } %>
          </tbody>
        </table>
      </div>

      <footer class="table-footer">
        <span>Página <%= currentPage %> de <%= totalPages %></span>
        <nav class="pagination">
          <a href="${pageContext.request.contextPath}/ingrediente/listar?page=<%= currentPage - 1 %><%= buscaParam %>"
             class="arrow <%= currentPage <= 1 ? "disabled" : "" %>">
            <i class="fa-solid fa-chevron-left"></i>
          </a>

          <span class="current-page"><%= currentPage %></span>

          <a href="${pageContext.request.contextPath}/ingrediente/listar?page=<%= currentPage + 1 %><%= buscaParam %>"
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
      <form id="delete-form" action="${pageContext.request.contextPath}/ingrediente/excluir" method="post">
        <input type="hidden" name="input-id" id="input-id">
        <input type="submit" class="btn btn-danger" value="Excluir">
      </form>
    </div>
  </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/popup.js"></script>

</body>
</html>
