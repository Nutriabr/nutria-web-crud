<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu de Adicionar Usuários</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/forms.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<%@include file="../components/messagemErro.jsp" %>
<main>
    <jsp:include page="/WEB-INF/views/components/sidebar.jsp">
        <jsp:param name="activePage" value="usuario"/>
    </jsp:include>
    <div class="container">
        <div class="main-content">
            <h1>Adicionar usuário</h1>
            <p>Preencha as informações do novo usuário.</p>
        </div>
        <form class="form-content" name="forms-add-admin" action="${pageContext.request.contextPath}/usuario/adicionar"
              method="post">
            <h2>Informações do usuário</h2>
            <label for="name-input">Nome</label>
            <input class="parameter-input" type="text" id="name-input" name="name"
                   placeholder="Insira o nome" required>

            <label for="email-input">E-mail</label>
            <input class="parameter-input" type="email" id="email-input" oninput="validateEmail()" name="email"
                    placeholder="Insira o endereço de email" required>
            <span id="errorFeedback"></span>


            <label for="password-input">Senha</label>
            <input class="parameter-input" type="password" id="password-input" oninput="validatePassword()" name="password"
                    placeholder="Insira a senha" required>
            <span id="erroFeedbackPassword"></span>

            <label for="phone-input">Telefone</label>
            <input class="parameter-input" type="text" id="phone-input" oninput="validatePhone()" name="phone"
                    placeholder="XX XXXXX-XXXX" required>

            <label for="company-input">Empresa</label>
            <input class="parameter-input" type="text" id="company-input" name="company"
                    placeholder="Insira sua empresa">

            <label for="picture-input">Foto</label>
            <input class="parameter-input" type="text" id="picture-input" name="picture"
                    placeholder="Insira a URL da foto">

            <div class="submit-content">
                <a href="${pageContext.request.contextPath}/usuario/listar" id="btn-cancel">Cancelar</a>
                <input id="submit-btn" type="submit" value="Adicionar">
            </div>
        </form>
    </div>
</main>
<script src="${pageContext.request.contextPath}/assets/js/validador.js"></script>
</body>
</html>
