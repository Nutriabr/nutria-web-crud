<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mensagem de erro</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/feedbackMessages.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%
    String errorFeedback = (String) request.getAttribute("errorMessage");

    if (errorFeedback != null && !errorFeedback.isEmpty()) {
%>
<div class="overlay-background">
    <div class="message">
        <i class="fa-solid fa-circle-exclamation"></i>
        <h2>Ocorreu um Erro</h2>
        <p><%= errorFeedback %></p>
        <button onclick="this.closest('.overlay-background').style.display='none'">Fechar</button>
    </div>
</div>
<%
    }
%>



</body>
</html>