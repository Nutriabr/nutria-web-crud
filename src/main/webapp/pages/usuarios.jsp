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
<main>
    <div class="hero-content">
        <div class="main-icons">
            <img src="../assets/img/Foto usuario sem fundo.svg" alt="Usuário">
        </div>

        <hr>

        <div class="hero-title">
            <h1>Usuários</h1>
            <p>Gerencie os dados dos seus usuários.</p>
        </div>

        <div class="users-counter">
            <p>Total</p>
            <p>
            <%
                String totalUsuarios = (String)request.getAttribute("totalUsuarios");
//               out.println(totalUsuarios);
               System.out.println(totalUsuarios);
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
                    <div class="newUser-popup">
                        <h1>Adicionar novo usuário</h1>

                        <form>
                            <div class="photo">
                                <label for="photo">URL da foto (Opcional)</label>
                                <input type="url" name="photo" id="photo" placeholder="https://...">
                            </div>

                            <div class="user-name">
                                <label for="name">Nome completo</label>
                                <input type="text" name="name" id="name" placeholder="Insira o nome completo">
                            </div>

                            <div class="user-email">
                                <label for="email">Endereço de e-mail</label>
                                <input type="email" name="email" id="email" placeholder="Insira o endereço de e-mail">
                            </div>

                            <div class="user-password">
                                <label for="password">Senha</label>
                                <input type="password" name="password" id="password" placeholder="Insira a senha">
                            </div>

                            <div class="user-phone">
                                <label for="phone">Telefone</label>
                                <input type="tel" name="phone" id="phone" placeholder="Insira o telefone">
                            </div>

                            <div class="user-company">
                                <label for="company">Empresa (Opcional)</label>
                                <input type="text" name="company" id="company" placeholder="Insira a empresa">
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
            <a href="usuarios.jsp?page=<%= page1 - 1 %>">anterior</a>
            <% } %>

            <% for (int i = 1; i <= totalPaginas; i++) { %>
            <a href="usuarios.jsp?page=<%= i %>"><%= i %></a>
            <% } %>

            <!-- Seta para direita (avançar página) -->
            <% if (page1 < totalPaginas) { %>
            <a href="usuarios.jsp" id="proxima">proxima</a>
            <a style="color: red">Bom dia</a>
            <% } %>

        </div>
    </div>
</main>
</body>
</html>
