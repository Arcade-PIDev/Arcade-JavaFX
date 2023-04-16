/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Amira
 */
public interface IService <C>{
    void ajouter(C c) throws SQLException;
    List<C> afficher() throws SQLException;
    void delete(int id) throws SQLException;
    void update(C c) throws SQLException;
}
