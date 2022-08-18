package com.daos;

import com.exceptions.AccessDeniedException;
import com.pojos.User;
import com.services.DatasourceService;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDAO implements DatasourceCRUD<User>{

    Connection connection;

    public UserDAO() {
        this.connection = DatasourceService.getConnection();
    }

    // add this to test in main function
    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) {
        try {
            String sql = "INSERT INTO users (first_name, last_name, user_name, email, password, admin) VALUES (?, ?, ?, ?, ?, false)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getUserName());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPassword());

            // execute one row of sql
            pstmt.executeUpdate();

            // Iterate through generatedkeys to asign the new user id to userID
            // next does the boolean check to see if theres a next value and moves cursor to retrieve that next value
            ResultSet keys = pstmt.getGeneratedKeys();
            if(keys.next()) {
                Integer key = keys.getInt("user_id"); // could set it equal to an integer 1
                user.setUserId(key);

                if(key == 1) {
                    String admin = "UPDATE users SET admin = true WHERE user_id = 1";
                    PreparedStatement pstmtAdmin = connection.prepareStatement(admin);
                    pstmtAdmin.executeUpdate();
                }
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User read(int id) {
        User user = new User();
        try {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet results = pstmt.executeQuery();


            if(results.next()) {
                user.setUserId(results.getInt("user_id"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setUserName(results.getString("user_name"));
                user.setEmail(results.getString("email"));
                user.setPassword(results.getString("password"));
                user.setAdmin(results.getBoolean("admin"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> readAll() {
        List<User> userList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();

            while(results.next()) {
                // must always create a new user to update the list
                User user = new User();
                user.setUserId(results.getInt("user_id"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setUserName(results.getString("user_name"));
                user.setEmail(results.getString("email"));
                user.setPassword(results.getString("password"));
                user.setAdmin(results.getBoolean("admin"));
                // add user to the userList
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void update(User user) {
        try{
            String sql = "UPDATE users SET first_name = ?, last_name = ?, user_name = ?, email = ?, password = ? WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getUserName());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPassword());
            pstmt.setInt(6, user.getUserId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Authenticate Servlet, added method not needed for override in DatasourceCRUD
    public User authenticate(String username, String password) {
        User user = new User();

        try {
            String sql = "SELECT * FROM users WHERE user_name = ? AND password = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet results = pstmt.executeQuery();

            if(results.next()) {
                user.setUserId(results.getInt("user_id"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setUserName(results.getString("user_name"));
                user.setEmail(results.getString("email"));
                user.setPassword(results.getString("password"));
                user.setAdmin(results.getBoolean("admin"));

            } else {
                throw new AccessDeniedException("Access denied!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return user;
    }

}
