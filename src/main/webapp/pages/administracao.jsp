<%@ page contentType="text/html;charset=UTF-8"  language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Painel de Controle - Nutria</title>
    <link rel="stylesheet" href="../assets/css/admin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<div class="sidebar">
    <img id="nutria" src="../assets/img/logo.svg" alt="">
    <div class="user">
        <i class="fa-solid fa-user-circle"></i>
        <span><%
            String nome = (String) session.getAttribute("adminName");
            if (nome != null) {
                int primeiroEspaco = nome.indexOf(" ");
                int segundoEspaco = nome.indexOf(" ", primeiroEspaco + 1);

                if (primeiroEspaco != -1 && segundoEspaco != -1 ) {
                    out.print(nome.substring(0, segundoEspaco));
                } else {
                    out.print(nome);
                }

            } else {
                out.print("Administrador");
            }
        %></span>
    </div>

    <hr>

    <ul class="menu">
        <li class="active"><i class="fa-solid fa-house"></i> Início</li>
        <li><i class="fa-solid fa-users"></i> Usuários</li>
        <li><i class="fa-solid fa-box"></i> Produtos</li>
        <li><i class="fa-solid fa-user-gear"></i> Administradores</li>
        <li><i class="fa-solid fa-carrot"></i> Ingredientes</li>
        <li><i class="fa-solid fa-list"></i> Tabela Nutricional</li>
        <li><i class="fa-solid fa-utensils"></i> Receitas</li>
        <li><i class="fa-solid fa-clipboard-list"></i> Ingredientes da Receita</li>
    </ul>

    <form class="logout" action="${pageContext.request.contextPath}/logout" method="post">
        <button type="submit">Encerrar sessão</button>  <i class="fa-solid fa-right-from-bracket"></i>
    </form>
</div>

<div class="main-content">

    <div class="dashboard">
        <h2>Painel de controle</h2>
        <p>Gerencie seus dados de forma interativa.</p>

        <div class="cards">
            <div class="card">
                <div class="icon">
                    <h3>Usuários</h3>
                    <img src="../assets/img/UserLaranja.png" alt="">
                </div>
                <span class="value">243</span>
                <div class="acess">
                    <a href="usuarios.jsp">Acessar</a>
                    <a href="usuarios.jsp"><img src="../assets/img/setaVerde.png" alt=""></a>
                </div>
            </div>

            <div class="card">
                <div class="icon">
                    <h3>Produtos</h3>
                    <img src="../assets/img/IconeDeProdutos.png" alt="">
                </div>
                <span class="value">5500</span>
                <div class="acess">
                    <a href="#">Acessar</a>
                    <img src="../assets/img/setaVerde.png" alt="">
                </div>
            </div>

            <div class="card">
                <div class="icon">
                    <h3>Administradores</h3>
                    <img src="../assets/img/UserEngre.png" alt="">
                </div>
                <span class="value">15</span>
                <div class="acess">
                    <a href="#">Acessar</a>
                    <img src="../assets/img/setaVerde.png" alt="">
                </div>
            </div>

            <div class="card">
                <div class="icon">
                    <h3>Ingredientes</h3>
                    <img src="../assets/img/IconeIngredientes.png" alt="">
                </div>
                <span class="value">0</span>
                <div class="acess">
                    <a href="#">Acessar</a>
                    <img src="../assets/img/setaVerde.png" alt="">
                </div>
            </div>

            <div class="card">
                <div class="icon">
                    <h3>Loren ipsum</h3>
                    <img src="../assets/img/UserLaranja.png" alt="">
                </div>
                <span class="value">0</span>
                <div class="acess">
                    <a href="#">Acessar</a>
                    <img src="../assets/img/setaVerde.png" alt="">
                </div>
            </div>

            <div class="card">
                <div class="icon">
                    <h3>Loren ipsum</h3>
                    <img src="../assets/img/UserLaranja.png" alt="">
                </div>
                <span class="value">0</span>
                <div class="acess">
                    <a href="#">Acessar</a>
                    <img src="../assets/img/setaVerde.png" alt="">
                </div>
            </div>
        </div>

        <p class="info">Clique em “Acessar” para acessar cada seção do sistema</p>
    </div>
</div>
</body>
</html>
