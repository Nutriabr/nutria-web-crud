<%--
  Created by IntelliJ IDEA.
  User: giiovannasantoss-ieg
  Date: 17/10/2025
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
  <title>Adicionar nova Tabela Nutricional</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/forms.css">
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
      <h1>Adicionar Tabela Nutricional</h1>
      <p>Preencha as informações da nova Tabela Nutricional.</p>
    </div>
    <form class="form-content" name="forms-add-tabela-nutricional" action="${pageContext.request.contextPath}/tabelaNutricional/adicionar"
          method="post">
      <h2>Informações da tabela nutricional</h2>

      <label for="id-ingrediente-input">ID do ingrediente</label>
      <input class="parameter-input" type="number" id="id-ingrediente-input" name="id-ingrediente" min="1" step="1" placeholder="Insira o ID do ingrediente" required>

      <label for="valor-energetico-input">Valor energético (kcal)</label>
      <input class="parameter-input" type="number" id="valor-energetico-input" name="valor-energetico" min="0" step="1" placeholder="Ex: 23" required>

      <label for="carboidratos-input">Carboidratos (g)</label>
      <input class="parameter-input" type="number" id="carboidratos-input" name="carboidratos" min="0" step="0.1" placeholder="Ex: 14.9" required>

      <label for="acucares-totais-input">Açúcares totais (g)</label>
      <input class="parameter-input" type="number" id="acucares-totais-input" name="acucares-totais" min="0" step="0.1" placeholder="Ex: 46.3" required>

      <label for="acucares-adicionados-input">Açúcares adicionados (g)</label>
      <input class="parameter-input" type="number" id="acucares-adicionados-input" name="acucares-adicionados" min="0" step="0.1" placeholder="Ex: 12.3" required>

      <label for="proteinas-input">Proteínas (g)</label>
      <input class="parameter-input" type="number" id="proteinas-input" name="proteinas" min="0" step="0.1" placeholder="Ex: 8.5" required>

      <label for="gorduras-totais-input">Gorduras totais (g)</label>
      <input class="parameter-input" type="number" id="gorduras-totais-input" name="gorduras-totais" min="0" step="0.1" placeholder="Ex: 2.3" required>

      <label for="gorduras-saturadas-input">Gorduras saturadas (g)</label>
      <input class="parameter-input" type="number" id="gorduras-saturadas-input" name="gorduras-saturadas" min="0" step="0.1" placeholder="Ex: 1.2" required>

      <label for="gorduras-trans-input">Gorduras trans (g)</label>
      <input class="parameter-input" type="number" id="gorduras-trans-input" name="gorduras-trans" min="0" step="0.01" placeholder="Ex: 0.03" required>

      <label for="fibra-alimentar-input">Fibra alimentar (g)</label>
      <input class="parameter-input" type="number" id="fibra-alimentar-input" name="fibra-alimentar" min="0" step="0.1" placeholder="Ex: 2.5" required>

      <label for="sodio-input">Sódio (mg)</label>
      <input class="parameter-input" type="number" id="sodio-input" name="sodio" min="0" step="1" placeholder="Ex: 230" required>

      <label for="colesterol-input">Colesterol (mg)</label>
      <input class="parameter-input" type="number" id="colesterol-input" name="colesterol" min="0" step="0.01" placeholder="Ex: 12.50">

      <label for="vitamina-a-input">Vitamina A (µg)</label>
      <input class="parameter-input" type="number" id="vitamina-a-input" name="vitamina-a" min="0" step="0.01" placeholder="Ex: 15.75">

      <label for="vitamina-c-input">Vitamina C (mg)</label>
      <input class="parameter-input" type="number" id="vitamina-c-input" name="vitamina-c" min="0" step="0.01" placeholder="Ex: 23.50">

      <label for="vitamina-d-input">Vitamina D (µg)</label>
      <input class="parameter-input" type="number" id="vitamina-d-input" name="vitamina-d" min="0" step="0.01" placeholder="Ex: 2.50">

      <label for="calcio-input">Cálcio (mg)</label>
      <input class="parameter-input" type="number" id="calcio-input" name="calcio" min="0" step="0.01" placeholder="Ex: 34.25">

      <label for="ferro-input">Ferro (mg)</label>
      <input class="parameter-input" type="number" id="ferro-input" name="ferro" min="0" step="0.01" placeholder="Ex: 4.75">

      <label for="potassio-input">Potássio (mg)</label>
      <input class="parameter-input" type="number" id="potassio-input" name="potassio" min="0" step="0.01" placeholder="Ex: 15.50">

      <div class="submit-content">
        <a href="${pageContext.request.contextPath}/tabelaNutricional/listar" id="btn-cancel">Cancelar</a>
        <input id="submit-btn" type="submit" value="Adicionar">
      </div>
    </form>
  </div>
</main>
</body>
</html>
