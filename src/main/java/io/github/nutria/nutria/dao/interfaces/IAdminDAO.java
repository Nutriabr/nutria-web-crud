package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.model.Admin;
import io.github.nutria.nutria.model.Usuario;

import java.sql.SQLException;
/**
 * Interface para operações de CRUD na entidade Admin.
 * @author Luis Henrique
 * @version 1.0
 * @see Admin
 */
public interface IAdminDAO {
    /** Método para listar por email todos os admins cadastrados no banco de dados
     * @return List<Admin> Retorna uma lista de objetos Admin
     * @author Luis Henrique
     * @throws SQLException Lança uma exceção SQL em caso de erro na consulta
     * */
    Admin findByEmail(String email);
}
