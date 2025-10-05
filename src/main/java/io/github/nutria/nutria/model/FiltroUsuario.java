package io.github.nutria.nutria.model;

import java.util.HashMap;
import java.util.Map;

public class FiltroUsuario {
    private String coluna;
    private String operador;
    private String valor;

    public FiltroUsuario(String coluna, String operador, String valor) {
        this.coluna = coluna;
        this.operador = operador;
        this.valor = valor;
    }

    public FiltroUsuario(String coluna, String operador) {
        this.coluna = coluna;
        this.operador = operador;
    }

    public Map<String, FiltroUsuario> filtrosUsuarios() {
        Map<String, FiltroUsuario> filtros = new HashMap<>();

        filtros.put("nome_usuario", new FiltroUsuario("nome", "LIKE", valor));
        filtros.put("email_usuario", new FiltroUsuario("email", "=", valor));
        filtros.put("dominio_email_usuario", new FiltroUsuario("email", "LIKE", valor));
        filtros.put("empresa", new FiltroUsuario("empresa", "LIKE", valor));
        filtros.put("com_foto", new FiltroUsuario("foto", "IS NOT NULL"));
        filtros.put("sem_foto", new FiltroUsuario("foto", "IS NULL"));

        return filtros;
    }
}
