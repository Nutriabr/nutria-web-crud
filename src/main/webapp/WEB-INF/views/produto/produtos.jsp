<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="io.github.nutria.nutria.model.Produto" %>

<%
  List<Produto> produtosList = (List<Produto>) request.getAttribute("produtosList");
  int totalProdutos = (int) request.getAttribute("totalProdutos");
  int totalPages = (int) request.getAttribute("totalPages");
  int currentPage = (int) request.getAttribute("currentPage");
  List<String> emailsList = (List<String>) request.getAttribute("emailsList");
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
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/logo.svg" type="image/x-icon">
</head>
<body>
<%@include file="../components/mensagemSucesso.jsp" %>
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
            <input type="search" name="busca" placeholder="Buscar pelo nome ou ID" value="<%= filtro != null ? filtro : "" %>">
          </form>
          <a href="${pageContext.request.contextPath}/produto/adicionar" class="btn btn-primary">
            <i class="fa-solid fa-plus"></i>
            Adicionar novo produto
          </a>
        </div>
      </header>
      <div class="delete-popup" style="display: none">
        <h2>Emails</h2>
        <div class="delete-popup-content">
          <select name="opcao" id="selectEmpresa">
            <% for (String email : emailsList) { %>
            <option value="<%= email %>"><%= email %></option>
            <% } %>
          </select>

          <form action="${pageContext.request.contextPath}/produto/excluir" method="post">
            <input type="hidden" name="acao" value="deletarPorEmail">
            <button type="submit" class="btn-action deletar-por-empresa">Deletar todos os registros</button>
          </form>
        </div>
      </div>

      <div class="table-container">
        <table>
          <thead>
          <tr>
            <th>ID do Produto</th>
            <th>Nome do Produto</th>
            <th>ID do Usuario</th>
            <th>Nome do Usuário</th>
            <th>Email do Usuário</th>
            <th>Empresa do Usuário</th>
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


<div class="overlay" id="delete-all-popup-overlay" style="display: none;">
  <div class="popup-container">
    <h1>Você tem certeza que deseja excluir todos os registros dessa usuário?</h1>
    <p>Você não poderá recuperar nenhum registro do usuário <strong id="empresa-nome"></strong> após excluir.</p>
    <div class="popup-actions">
      <button class="btn btn-secondary" id="cancel-delete-all-btn">Cancelar</button>
      <form id="delete-all-form" action="${pageContext.request.contextPath}/usuario/excluir" method="post">
        <input type="hidden" name="input-empresa" id="input-empresa">
        <input type="submit" class="btn btn-danger" name="acao" value="Excluir">
      </form>
    </div>
  </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/popup.js"></script>

</body>
</html>