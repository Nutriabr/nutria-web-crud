package io.github.nutria.nutria.model;

import java.util.HashMap;
import java.util.Map;

public class FiltroUsuario {
    private static String coluna;
    private static String operador;
    private static String valor;

    public FiltroUsuario(String coluna, String operador, String valor) {
        this.coluna = coluna;
        this.operador = operador;
        this.valor = valor;
    }

    public FiltroUsuario(String coluna, String operador) {
        this.coluna = coluna;
        this.operador = operador;
    }

    // getters
    public String getColuna() {
        return coluna;
    }

    public String getOperador() {
        return operador;
    }

    public String getValor() {
        return valor;
    }

    // setters


    public static void setColuna(String coluna) {
        FiltroUsuario.coluna = coluna;
    }

    public static void setOperador(String operador) {
        FiltroUsuario.operador = operador;
    }

    public static void setValor(String valor) {
        FiltroUsuario.valor = valor;
    }


    public static Map<String, FiltroUsuario> filtrosUsuarios() {
        Map<String, FiltroUsuario> filtroUsuario = new HashMap<>();

        filtroUsuario.put("nome_usuario", new FiltroUsuario("nome", "LIKE", valor));
        filtroUsuario.put("email_usuario", new FiltroUsuario("email", "=", valor));
        filtroUsuario.put("dominio_email_usuario", new FiltroUsuario("email", "LIKE", valor));
        filtroUsuario.put("empresa", new FiltroUsuario("empresa", "LIKE", valor));
        filtroUsuario.put("com_foto", new FiltroUsuario("foto", "IS NOT NULL"));
        filtroUsuario.put("sem_foto", new FiltroUsuario("foto", "IS NULL"));

        return filtroUsuario;
    }
}
