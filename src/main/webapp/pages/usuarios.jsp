<!-- <%--
  Created by IntelliJ IDEA.
  User: enzomota-ieg
  Date: 08/09/2025
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="io.github.nutria.nutria.dao.UsuarioDAO" %>
<%@ page import="io.github.nutria.nutria.model.Usuario" %>


<%
    UsuarioDAO dao = new UsuarioDAO();
%> -->


<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../assets/css/usuarios.css">
    <title>Usuários - CRUD</title>
    <link rel="icon" href="../assets/img/favicon.svg" type="image/x-icon">
</head>
<body>
<!-- Menu lateral -->
<%@include file="aside.jsp"%>
<!-- Conteúdo principal -->
<main id="main-content">
        <div class="main-title">
            <h1>Usuários</h1>
            <p>Gerencie os dados dos seus usuários.</p>
        </div>

        <div class="users-counter">
            <p>Total</p>
            <p>
            <%

            %>
        </div>


        <!-- Barra de pesquisa por usuários -->
            <div class="find-elements">
                <div class="find-bar">
                    <img src="../assets/img/find.svg" alt="Buscar usuários">
                    <input type="search" name="find" id="find" placeholder="Buscar">
                </div>
                <a href=""><img src="../assets/img/filtro.svg.png" alt=""></a>

                <!-- Botão para adicionar um novo usuário -->
                <!-- Checkbox escondido -->
                <input type="checkbox" id="addUser-popup" hidden>

                <!-- Overlay do popup, será exibida com CSS quando o checkbox estiver marcado -->
                <div class="overlay">
                    <!-- Botão que ativa o checkbox -->
                    <label for="addUser-popup" class="add-user-btn">Adicionar novo usuário</label>
                </div>

                <!-- Container do popup -->
                <div class="popup-container">
                    <div id="newUser-popup">
                        <h1>Adicionar novo usuário</h1>

                        <form>
                            <div class="photo">
                                <label for="photo">URL da foto (Opcional)</label>
                                <input type="url" name="photo" class="photo" placeholder="https://...">
                            </div>

                            <div class="user-name">
                                <label for="name">Nome completo</label>
                                <input type="text" name="name" class="name" placeholder="Insira o nome completo">
                            </div>

                            <div class="user-email">
                                <label for="email">Endereço de e-mail</label>
                                <input type="email" name="email" class="email" placeholder="Insira o endereço de e-mail">
                            </div>

                            <div class="user-password">
                                <label for="password">Senha</label>
                                <input type="password" name="password" class="password" placeholder="Insira a senha">
                            </div>

                            <div class="user-phone">
                                <label for="phone">Telefone</label>
                                <input type="tel" name="phone" class="phone" placeholder="Insira o telefone">
                            </div>

                            <div class="user-company">
                                <label for="company">Empresa (Opcional)</label>
                                <input type="text" name="company" class="company" placeholder="Insira a empresa">
                            </div>

                            <button type="submit" class="submit-btn">Adicionar</button>
                        </form>
                    </div>
                </div>

            </div>

            <!-- Grid de usuários -->

            <div class="users-table">
                <table>

                    <!-- Cabeçalho da tabela -->
                    <tr>
                    <tr>

                        <th>Foto</th>
                        <th>ID</th>
                        <th>Nome completo</th>
                        <th>Endereço de e-mail</th>
                        <th>Senha</th>
                        <th>Telefone</th>
                        <th>Empresa</th>
                        <th>Ações</th>
                    </tr>

                    <!-- Criação de Usuários -->
<%--                    <%--%>
<%--                        Integer pageObj = (Integer) request.getAttribute("page");--%>
<%--                        Integer totalPaginasObj = (Integer) request.getAttribute("totalPaginas");--%>
<%--                        int page1 = (pageObj != null) ? pageObj.intValue() : 1;--%>
<%--                        int totalPaginas = (totalPaginasObj != null) ? totalPaginasObj.intValue() : 1;--%>
<%--                    %>--%>

                    <%
                        Integer page1 = (Integer) request.getAttribute("page");
                        if (page1 == null) page1 = 1;
                        Integer totalPaginas = (Integer) request.getAttribute("totalPaginas");
                        if (totalPaginas == null) totalPaginas = 1;
                    %>

                    <!-- Usuário 1 -->
                    <% for (Usuario u : dao.findAll(page1)) { %>
                    <tr>
                        <td><%= u.getFoto()%></td>
                        <td><%= u.getId()%></td>
                        <td><%= u.getNome()%></td>
                        <td><%= u.getEmail()%></td>
                        <td>******</td>
                        <td><%= u.getTelefone()%></td>
                        <td><%= u.getEmpresa()%></td>
                        <td>
                            <input type="checkbox" id="editUser-popup" hidden>

                            <!-- Overlay do popup, será exibida com CSS quando o checkbox estiver marcado -->
                            <div class="overlay">
                                <!-- //Botão que ativa o checkbox// -->
                                <label for="editUser-popup" class="edit-user-btn"><img
                                        src="../assets/img/editar usuario.svg" alt="Editar usuário"></label>
                            </div>

                            <div class="popupEdit-container">
                                <div class="editUser-popup">
                                    <h1>Editar usuário</h1>

                                    <form action="/editarUsuario" method="post">
                                        <div class="photo">
                                            <label for="photo">URL da foto (Opcional)</label>
                                            <input type="url" name="photo" id="photo" class="photo" value="<%= u.getFoto()%>">
                                        </div>

                                        <div class="user-name">
                                            <label for="name">Nome completo</label>
                                            <input type="text" name="name" id="name" class="name" value="<%= u.getNome() %>">
                                        </div>

                                        <div class="user-email">
                                            <label for="email">Endereço de e-mail</label>
                                            <input type="email" name="email" id="email" class="email" value="<%= u.getEmail() %>">
                                        </div>

                                        <div class="user-password">
                                            <label for="password">Senha</label>
                                            <input type="hidden" name="id" id="password" class="password" value="<%= u.getId() %>">
                                        </div>

                                        <div class="user-phone">
                                            <label for="phone">Telefone</label>
                                            <input type="tel" name="phone" id="phone" class="phone">
                                        </div>

                                        <div class="user-company">
                                            <label for="company">Empresa (Opcional)</label>
                                            <input type="text" name="company" id="company" class="company">
                                        </div>

                                        <button type="reset" id="cancelEdit-btn">Cancelar</button>

                                        <button type="submit" class="submit-btn">Atualizar</button>
                                    </form>
                                </div>
                            </div>

                            <input type="checkbox" id="deleteUser-popup" hidden>

                            <div class="overlay">
                                <label for="deleteUser-popup" class="delete-user-btn"><img
                                        src="../assets/img/lixeira.svg" alt="Excluir usuário"></label>
                            </div>

                            <div class="popupDelete-container">
                                <div class="deleteUser-popup">
                                    <h1>Você tem certeza que deseja excluir este registro?</h1>

                                    <p>Você não poderá recuperar esse registro após excluir</p>

                                    <label for="deleteUser-popup" class="cancelDelete-user-btn">Cancelar</label>

                                    <label for="deleteUser-popup" class="delete-user-btn">Excluir</label>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <% }  %>
                </table>
            </div>

        <!-- Paginação -->
        <div class="pagination">
            <p>Página <%= page1 %> de <%= totalPaginas %></p>
        </div>

        <div class="arrows">
            <!-- Seta para esquerda (voltar página) -->
            <%--                        <img src="../assets/img/setaEsquerda.svg" alt="Página anterior">--%>
            <% if (page1 > 1) { %>
            <a href="usuarios.jsp?page=<%= page1 - 1 %>"><img src="../assets/img/setaEsquerda.svg"></a>
            <% } %>

            <% for (int i = 1; i <= totalPaginas; i++) { %>
            <a href="usuarios.jsp?page=<%= i %>"><%= i %></a>
            <% } %>

            <!-- Seta para direita (avançar página) -->
            <% if (page1 < totalPaginas) { %>
            <a href="usuarios.jsp" id="proxima"><img src="../assets/img/setaDireita.svg"></a>
            <% } %>

        </div>
</main>
</body>
</html>
