package io.github.nutria.nutria.servlet.tabelaNutricionalServlet;

import io.github.nutria.nutria.service.TabelaNutricionalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "TabelaNutricionalController", value = "/TabelaNutricionalInsert")
public class TabelaNutricionalInsertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        TabelaNutricionalService service = new TabelaNutricionalService();

        if (path.equals("/TabelaNutricionalInsert")) {
            service.save(request);
        }
    }
}
