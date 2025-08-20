package io.github.nutria.nutria.model.dao;
import io.github.nutria.nutria.model.entity.Receita;
import io.github.nutria.nutria.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDAO implements IReceitaDAO{
    ConnectionFactory factory = new ConnectionFactory();

    // Método para inserir (create) ou atualizar (update)
    public void save(Receita receita){
        try (Connection connect = factory.connect()){
            if(receita.getId() == 0){
                // Query de inserção no banco de dados
                String sql = "INSERT INTO receitas (nome, porcao, id_Produto)" +
                        "VALUES (?,?,?)";

                PreparedStatement ps = connect.prepareStatement(sql);
                ps.setString(1, receita.getNome());
                ps.setString(2, receita.getPorcao());
                ps.setLong(3, receita.getIdProduto());

                // Executando o query de inserção
                int result = ps.executeUpdate();
                if(result > 0){
                    System.out.println("Receita cadastrado com sucesso.");
                }
            } else {

                String sql = "UPDATE receitas SET nome = ?, porcao = ?, idProduto = ?" +
                        "WHERE id = ?";

                PreparedStatement ps = connect.prepareStatement(sql);

                ps.setString(1,receita.getNome());
                ps.setString(2,receita.getPorcao());
                ps.setLong(3,receita.getIdProduto());

                // Condição do WHERE
                ps.setLong(4,receita.getId());

                int result = ps.executeUpdate();

                if(result > 0){
                    System.out.println("Receita atualizado com sucesso.");
                } else {
                    System.out.println("Nenhuma receita encontrada para atualizar.");
                }

            }
        } catch (Exception e ){
            throw new RuntimeException("Erro ao cadastrar receita: " + e.getMessage());
        }
    }

    // Método para exibir (read)
    public List<Receita> findAll() {
        List<Receita> receitas = new ArrayList<>();

        try (Connection connect = factory.connect()) {
            String sql = "SELECT * FROM receitas";
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Receita receita = new Receita();
                receita.setId(rs.getLong("id"));
                receita.setNome(rs.getString("nome"));
                receita.setPorcao(rs.getString("porcao"));
                receita.setIdProduto(rs.getLong("telefone"));
//                Adicionamos o usuário à lista
                receitas.add(receita);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return receitas;
    }

    // Método para deletar (delete)
    public int deleteById(long id) {
        try (Connection connect = factory.connect()) {
            String sql = "DELETE FROM receitas WHERE id = ?";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setLong(1, id);

            return ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
