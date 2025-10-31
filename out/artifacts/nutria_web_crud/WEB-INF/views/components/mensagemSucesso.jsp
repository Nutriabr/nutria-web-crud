<%--
  Created by IntelliJ IDEA.
  User: enzomota-ieg
  Date: 25/10/2025
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mensagem de sucesso</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/feedbackMessages.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%
    String feedback = (String) session.getAttribute("successMessage");

    if (feedback != null && !feedback.isEmpty()) {
        session.removeAttribute("successMessage");
%>
<div class="overlay-background">
    <div class="message">
        <i class="fa-solid fa-circle-check"></i>
        <h2>Sucesso!</h2>
        <p><%= feedback %></p>
        <button onclick="this.closest('.overlay-background').style.display='none'">Fechar</button>
    </div>
</div>
<%
    }
%>
</body>
</html>