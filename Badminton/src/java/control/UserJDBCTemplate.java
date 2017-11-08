/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import model.User;

/**
 *
 * @author HIEU
 */
public class UserJDBCTemplate {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public boolean checkLogin(User user) {
        String SQL = "select * from tbl_user where username = ? and password =  ?";
        List<User> result = jdbcTemplateObject.query(SQL, new Object[]{user.getUsername(), user.getPassword()}, new UserMapper());
        if (result.size() > 0) {
            return true;
        }
        return false;
    }

//    public boolean create(User user) {
//        String SQL = "select * from tblUser where username = ?";
//        List<User> result = jdbcTemplateObject.query(SQL, new Object[]{user.getUsername()}, new UserMapper());
//        if (result.size() > 0) {
//            return false;
//        }
//        SQL = "insert into tblUser (username, password, fullName, idCardNumber, idCardType, address, description) values( ?,  ?,  ?,  ?,  ?,  ?,  ?)";
//        jdbcTemplateObject.update(SQL, user.getUsername(), user.getPassword(),
//                user.getFullName(), user.getIdCardNumber(), user.getIdCardType(),
//                user.getAddress(), user.getDescription());
//        return true;
//    }
//
//    public List<User> search(String username) {
//        String SQL = "select * from tblUser where username = ?";
//        List<User> result = jdbcTemplateObject.query(SQL, new Object[]{username}, new UserMapper());
//        return result;
//    }
}
