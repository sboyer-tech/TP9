/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import simplejdbc.DAO;
import simplejdbc.DAOException;
/**
 *
 * @author pedago
 */
public class States extends DAO {

    public States(DataSource dataSource) {
        super(dataSource);
    }
    
    public ArrayList StatesList() throws DAOException {
        
        ArrayList result = new ArrayList<>();
        
        String sql = "SELECT DISTINCT STATE AS ETAT FROM CUSTOMER";
        try (   Connection connection = myDataSource.getConnection(); // Ouvrir une connexion
		java.sql.Statement stmt = connection.createStatement(); // On crée un statement pour exécuter une requête
		ResultSet rs = stmt.executeQuery(sql) // Un ResultSet pour parcourir les enregistrements du résultat
		) {
                    while (rs.next()) {
			result.add(rs.getString("ETAT"));
                    }
		} catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}

		return result;
    }
}
