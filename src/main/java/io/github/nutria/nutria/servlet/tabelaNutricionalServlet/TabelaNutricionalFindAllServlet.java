package io.github.nutria.nutria.servlet.tabelaNutricionalServlet;

import io.github.nutria.nutria.service.TabelaNutricionalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "TabelaNutricionalController", value = "TabelaNutricionalFindAll")
public class TabelaNutricionalFindAllServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        TabelaNutricionalService service = new TabelaNutricionalService();

        if (path.equals("/tabela_nutricional")) {
            response.getWriter().write(service.findAll().toString());
        }
    }
}
