<%--.
    Este JSP agora espera receber os dados prontos de um Servlet.
    Atributos esperados:
    - "produtosList": Uma lista de objetos Produto a serem exibidos.
    - "totalProdutos": O número total de produtos (int).
    - "totalPages": O número total de páginas (int).
    - "currentPage": O número da página atual (int).
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="io.github.nutria.nutria.model.Produto" %>

<%
  List<Produto> produtosList = (List<Produto>) request.getAttribute("produtosList");
  int totalProdutos = (int) request.getAttribute("totalProdutos");
  int totalPages = (int) request.getAttribute("totalPages");
  int currentPage = (int) request.getAttribute("currentPage");
  String filtro = (String) request.getAttribute("filtro");
  String buscaParam = (filtro != null && !filtro.trim().isEmpty()) ? "&busca=" + filtro : "";
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Produtos - Nutria</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tables.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
  <link rel="icon" href="${pageContext.request.contextPath}/assets/img/favicon.svg" type="image/x-icon">
</head>
<body>
<div class="page-container">
  <jsp:include page="/WEB-INF/views/components/sidebar.jsp">
    <jsp:param name="activePage" value="produto"/>
  </jsp:include>
  <main class="main-content">
    <header class="page-header">
      <h1>Produtos</h1>
      <p>Gerencie os dados dos produtos.</p>
    </header>

    <section class="stats-card">
      <div class="stat-item">
        <span>Total</span>
        <strong><%= totalProdutos %></strong>
      </div>
    </section>

    <section class="table-section">
      <header class="table-header">
        <h2>Produtos</h2>
        <div class="table-actions">
          <form action="${pageContext.request.contextPath}/produto/listar" method="get" class="search-bar">
            <i class="fa-solid fa-magnifying-glass"></i>
            <input type="search" name="busca" placeholder="Buscar" value="<%= filtro != null ? filtro : "" %>">
          </form>
          <a href="${pageContext.request.contextPath}/produto/adicionar" class="btn btn-primary">
            <i class="fa-solid fa-plus"></i>
            Adicionar novo produto
          </a>
        </div>
      </header>

      <div class="table-container">
        <table>
          <thead>
          <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>ID-Usuario</th>
            <th>Nome-Usuário</th>
            <th>Email-Usuário</th>
            <th>Empresa-Usuário</th>
            <th>Ações</th>
          </tr>
          </thead>
          <tbody>
          <% for (Produto produto : produtosList) { %>
          <tr>
            <td><%= produto.getId() %></td>
            <td><%= produto.getNome() %></td>
            <td><%= produto.getIdUsuario() %></td>
            <td><%= produto.getNomeUsuario()%></td>
            <td><%= produto.getEmailUsuario()%></td>
            <td><%= produto.getEmpresaUsuario()%></td>
            <td class="action-buttons">
              <a href="${pageContext.request.contextPath}/produto/editar?id=<%= produto.getId() %>" class="btn-action btn-edit">
                <i class="fa-solid fa-pencil"></i>
              </a>
              <button class="btn-action btn-delete" data-id="<%= produto.getId() %>">
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
          <a href="${pageContext.request.contextPath}/produto/listar?page=<%= currentPage - 1 %><%= buscaParam %>"
             class="arrow <%= currentPage <= 1 ? "disabled" : "" %>">
            <i class="fa-solid fa-chevron-left"></i>
          </a>

          <span class="current-page"><%= currentPage %></span>

          <a href="${pageContext.request.contextPath}/produto/listar?page=<%= currentPage + 1 %><%= buscaParam %>"
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
      <form id="delete-form" action="${pageContext.request.contextPath}/produto/excluir" method="post">
        <input type="hidden" name="input-id" id="input-id">
        <input type="submit" class="btn btn-danger" value="Excluir">
      </form>
    </div>
  </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/popup.js"></script>

</body>
</html>