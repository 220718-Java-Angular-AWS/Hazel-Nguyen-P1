package com.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojos.User;
import com.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    UserService service;
    ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        System.out.println("User servlet initializing...");
        this.service = new UserService();
        this.mapper = new ObjectMapper();
        System.out.println("User servlet initialized!");

        // super.init();
    }

    @Override
    public void destroy() {

        // super.destroy();
    }
    // Method will get an array of JSON objects and one json object in the body
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("user-id");
        if(param == null) {
            //Return all
            List<User> userList = service.getAllUsers();
            String json = mapper.writeValueAsString(userList);
            resp.getWriter().println(json);
        } else {
            //return the one the request wants
            Integer userId = Integer.parseInt(req.getParameter("user-id"));

            User user = service.getUser(userId);
            String json = mapper.writeValueAsString(user);
            resp.getWriter().println(json);
        }

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);

        //super.doGet(req, resp);
    }

    // Method to Post New User
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Posting..");
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while(buffer.ready()) {
            builder.append(buffer.readLine());
        }
        String json = builder.toString();


        User newUser = mapper.readValue(json, User.class);
        service.saveUser(newUser);

        resp.setStatus(200);
        resp.getWriter().print("Sucessfully Posted New User!");
        System.out.println("Successfully Posted! Go Check DB");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Updating User..");

        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while(buffer.ready()) {
            builder.append(buffer.readLine());
        }
        String json = builder.toString();

        User updateUser = mapper.readValue(json, User.class);
        service.updateUser(updateUser);

        resp.setStatus(200);
        resp.getWriter().print("Sucessfully Updated User!");
        System.out.println("Successfully Updated! Go Check DB");

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: finish with delete
        System.out.println("Deleting User...");
        Integer userId = Integer.parseInt(req.getParameter("user-id"));
        service.deleteUser(userId);

        resp.setStatus(200);
        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.getWriter().print("Sucessfully Deleted User!");
        System.out.println("Successfully Deleted! Go Check DB");

    }



}
