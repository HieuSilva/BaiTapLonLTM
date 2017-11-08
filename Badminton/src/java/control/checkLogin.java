/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;

/**
 *
 * @author HIEU
 */
public class checkLogin {
    
    private DAO dao;
    
    public User [] checkLogin(User user) {
        dao = new DAO();
        return dao.check(user);
    }
}
