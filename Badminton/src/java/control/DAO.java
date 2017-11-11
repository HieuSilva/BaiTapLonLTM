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
import model.QuocGia;
import model.User;

/**
 *
 * @author HIEU
 */
public class DAO {
    
    private Connection con;
    private String dbURL = "jdbc:sqlserver://localhost\\EVALUATION:1433; databasename=badminton; username=sa; password=12345678";
    private String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    public DAO() {
        try {
            Class.forName(driverClass);
            con = DriverManager.getConnection(dbURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public QuocGia getQuocGia(int id) {
        String sql = "SELECT * FROM tbl_quoc_gia WHERE id = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                rs.beforeFirst();
                while(rs.next()) {
                    QuocGia q = new QuocGia(rs.getInt("id"), rs.getString("ten"), rs.getString("anh"));
                    return q;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public User [] check(User user) {
        User [] listUser = null;
        String sql = "SELECT * FROM tbl_user "
                + "WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                listUser = new User[rs.getRow()];
                rs.beforeFirst();
                int i = 0;
                while(rs.next()) {
                    listUser[i++] = new User(rs.getInt("id"),
                                             rs.getString("username"),
                                             rs.getString("password"));
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return listUser;
    }
}
