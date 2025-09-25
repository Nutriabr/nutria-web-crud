package io.github.nutria.nutria.service;

// Importações necessárias
import io.github.nutria.nutria.dao.ReceitaDAO;
import io.github.nutria.nutria.model.Receita;

import java.util.List;

public class ReceitaService {
    private final ReceitaDAO dao = new ReceitaDAO();

    public ReceitaService(){

    }
    public String insert(Receita receita){
        // Declaração dos atributos dos campos
        String nome = receita.getNome();
        String porcao = receita.getPorcao();
        long idProduto = receita.getIdProduto();

        // Validação dos campos obrigatórios
        if(porcao == null ||  porcao.isBlank() || idProduto <=0 ){
            return "Os campos de porção e do id do produto são obrigatórios.";
        }

        dao.insert(receita);
        return null;
    }

    public List<Receita> findAll(){
        int page = 1;
        return dao.findAll(page);
    }

    public boolean deleteById(long id){
        return dao.deleteById(id);
    }
}

