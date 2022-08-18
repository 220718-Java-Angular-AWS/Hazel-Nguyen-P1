package com.services;

import com.daos.UserDAO;
import com.pojos.User;

import java.util.List;

// the only way to get to the DAOs classes and methods is through this service layer where the API layers can access them later
// POST, PUT, PATCH, DELETE, and GET
public class UserService {
    private UserDAO dao;

    public UserService() {
        this.dao = new UserDAO();
    }

    public void saveUser(User user) {
        //validation logic here
        dao.create(user);
    }

    public User getUser(int id) {
        return dao.read(id);
    }

    public List<User> getAllUsers() {
        return dao.readAll();
    }

    public void updateUser(User user) { dao.update(user); }

    public void deleteUser(int id) {
        dao.delete(id);
    }

    // add in authentification for web-tokens
    public User authenticate(String username, String password) {
        return dao.authenticate(username, password);
    }
}
