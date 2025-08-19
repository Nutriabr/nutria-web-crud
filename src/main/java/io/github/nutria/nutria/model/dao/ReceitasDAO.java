package io.github.nutria.nutria.model.dao;
import io.github.nutria.nutria.model.entity.Receita;
import io.github.nutria.nutria.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ReceitasDAO {
    ConnectionFactory factory = new ConnectionFactory();
    public void insertReceita(Receita receita){
        try (Connection connect = factory.connect()){
            // Query de inserção no banco de dados
            String sql = "INSERT INTO receitas (nome, porcao, id_Produto)" +
                    "VALUES (?,?,?)";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, receita.getNome());
            ps.setString(2, receita.getPorcao());
            ps.setLong(3, receita.getIdProduto());

            // Executando o query de inserção
            int result = ps.executeUpdate();
            if(result == 1){
                System.out.println("Receita cadastrado com sucesso.");
            }
        } catch (Exception e ){
            throw new RuntimeException("Erro ao cadastrar receita: " + e.getMessage());
        }
    }
}