package io.github.nutria.nutria.servlet.tabelaNutricionalServlet;

import io.github.nutria.nutria.service.TabelaNutricionalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "TabelaNutricional", urlPatterns = {"/tabela_nutricional", "/tabela_nutricional/inserir"})
public class TabelaNutricionalServelet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TabelaNutricionalService service = new TabelaNutricionalService();
        String path = req.getServletPath();
        if (path.equals("/tabela_nutricional/inserir")) {
            service.save(req);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        TabelaNutricionalService service = new TabelaNutricionalService();
        if (path.equals("/tabela_nutricional")) {
            resp.getWriter().write(service.findAll().toString());
        }
    }
}
