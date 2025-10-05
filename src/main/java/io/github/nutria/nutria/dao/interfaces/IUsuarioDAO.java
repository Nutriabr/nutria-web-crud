package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.model.Usuario;

/**
 * Interface para operações de CRUD na entidade Usuario.
 * @author Luis Henrique
 * @version 1.1
 * @see Usuario
 */
public interface IUsuarioDAO {
    /**
     * Método para validar se o email já está cadastrado no Banco de Dados
     * @param email Recebe como o parametro o email que será usado na clausura where na query
     * @return boolean Retorna <i>true</i> caso a quantidade de linhas seja maior que 0 (já existe um email com
     * o do parametro passado ou <i>false</i> caso a quantidade linha seja menor que 0 (Não existe um email como o do
     * parametro passado).;
     * */
    boolean findByEmailUsed(String email);
}
