<%--
  Created by IntelliJ IDEA.
  User: giovannasantos-ieg
  Date: 18/10/2025
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<%
    Object id = request.getAttribute("id");

    double valor_energetico = (Double) request.getAttribute("valor-energetico");
    double carboidratos = (Double) request.getAttribute("carboidratos");
    double acucares_totais = (Double) request.getAttribute("acucares-totais");
    double acucares_adicionados = (Double) request.getAttribute("acucares-adicionados");
    double proteinas = (Double) request.getAttribute("proteinas");
    double gorduras_totais = (Double) request.getAttribute("gorduras-totais");
    double gorduras_saturadas = (Double) request.getAttribute("gorduras_saturadas");
    double gorduras_trans = (Double) request.getAttribute("gorduras_trans");
    double fibra_alimentar = (Double) request.getAttribute("fibra-alimentar");
    double sodio = (Double) request.getAttribute("sodio");

    Double colesterol = (Double) request.getAttribute("colesterol");
    Double vitamina_a = (Double) request.getAttribute("vitamina-a");
    Double vitamina_c = (Double) request.getAttribute("vitamina-c");
    Double vitamina_d = (Double) request.getAttribute("vitamina-d");
    Double calcio = (Double) request.getAttribute("calcio");
    Double ferro = (Double) request.getAttribute("ferro");
    Double potassio = (Double) request.getAttribute("potassio");

    String errorMessage = (String) request.getAttribute("errorMessage");

    String contextPath = request.getContextPath();
%>

<html>
<head>
    <title>Editar Tabela Nutricional</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= contextPath %>/assets/css/forms.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<%@include file="../components/messagemErro.jsp"%>
<main>
    <jsp:include page="/WEB-INF/views/components/sidebar.jsp">
        <jsp:param name="activePage" value="tabelaNutricional"/>
    </jsp:include>
    <div class="container">
        <div class="main-content">
            <h1>Editar Tabela Nutricional</h1>
            <p>Preencha as novas informações da Tabela Nutricional.</p>
        </div>

        <form class="form-content" name="forms-add-tabela-nutricional" action="<%= contextPath %>/tabelaNutricional/editar" method="post">
            <h2>Informações da Tabela Nutricional</h2>

            <input type="hidden" name="id" value="<%= id %>">

            <label for="valor-energetico-input">Valor energético (kcal)</label>
            <input class="parameter-input" type="number" id="valor-energetico-input" name="valor-energetico" min="0" step="1" value="<%= valor_energetico %>">

            <label for="carboidratos-input">Carboidratos (g)</label>
            <input class="parameter-input" type="number" id="carboidratos-input" name="carboidratos" min="0" step="0.1" value="<%= carboidratos %>">

            <label for="acucares-totais-input">Açúcares totais (g)</label>
            <input class="parameter-input" type="number" id="acucares-totais-input" name="acucares-totais" min="0" step="0.1" value="<%= acucares_totais %>">

            <label for="acucares-adicionados-input">Açúcares adicionados (g)</label>
            <input class="parameter-input" type="number" id="acucares-adicionados-input" name="acucares-adicionados" min="0" step="0.1" value="<%= acucares_adicionados %>">

            <label for="proteinas-input">Proteínas (g)</label>
            <input class="parameter-input" type="number" id="proteinas-input" name="proteinas" min="0" step="0.1" value="<%= proteinas %>">

            <label for="gorduras-totais-input">Gorduras totais (g)</label>
            <input class="parameter-input" type="number" id="gorduras-totais-input" name="gorduras-totais" min="0" step="0.1" value="<%= gorduras_totais %>">

            <label for="gorduras-saturadas-input">Gorduras saturadas (g)</label>
            <input class="parameter-input" type="number" id="gorduras-saturadas-input" name="gorduras-saturadas" min="0" step="0.1" value="<%= gorduras_saturadas %>">

            <label for="gorduras-trans-input">Gorduras trans (g)</label>
            <input class="parameter-input" type="number" id="gorduras-trans-input" name="gorduras-trans" min="0" step="0.01" value="<%= gorduras_trans %>">

            <label for="fibra-alimentar-input">Fibra alimentar (g)</label>
            <input class="parameter-input" type="number" id="fibra-alimentar-input" name="fibra-alimentar" min="0" step="0.1" value="<%= fibra_alimentar %>">

            <label for="sodio-input">Sódio (mg)</label>
            <input class="parameter-input" type="number" id="sodio-input" name="sodio" min="0" step="1" value="<%= sodio %>">

            <label for="colesterol-input">Colesterol (mg)</label>
            <input class="parameter-input" type="number" id="colesterol-input" name="colesterol" min="0" step="0.01" value="<%= colesterol %>">

            <label for="vitamina-a-input">Vitamina A (µg)</label>
            <input class="parameter-input" type="number" id="vitamina-a-input" name="vitamina-a" min="0" step="0.01" value="<%= vitamina_a %>">

            <label for="vitamina-c-input">Vitamina C (mg)</label>
            <input class="parameter-input" type="number" id="vitamina-c-input" name="vitamina-c" min="0" step="0.01" value="<%= vitamina_c %>">

            <label for="vitamina-d-input">Vitamina D (µg)</label>
            <input class="parameter-input" type="number" id="vitamina-d-input" name="vitamina-d" min="0" step="0.01" value="<%= vitamina_d %>">

            <label for="calcio-input">Cálcio (mg)</label>
            <input class="parameter-input" type="number" id="calcio-input" name="calcio" min="0" step="0.01" value="<%= calcio %>">

            <label for="ferro-input">Ferro (mg)</label>
            <input class="parameter-input" type="number" id="ferro-input" name="ferro" min="0" step="0.01" value="<%= ferro %>">

            <label for="potassio-input">Potássio (mg)</label>
            <input class="parameter-input" type="number" id="potassio-input" name="potassio" min="0" step="0.01" value="<%= potassio %>">

            <div class="submit-content">
                <a href="<%= contextPath %>/tabelaNutricional/listar" id="btn-cancel">Cancelar</a>
                <input id="submit-btn" type="submit" value="Salvar Alterações">
            </div>
        </form>
    </div>
</main>
</body>
</html>
