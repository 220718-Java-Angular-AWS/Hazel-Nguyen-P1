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

public class AuthServlet extends HttpServlet {
    ObjectMapper mapper;
    UserService service;

    @Override
    public void init() throws ServletException {
        System.out.println("Auth Servlet initializing...");
        this.mapper = new ObjectMapper();
        this.service = new UserService();
        System.out.println("Auth Servlet Initialized!");
    }

    // TODO: ask where destroy method is not here

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Authenticate
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while(buffer.ready()) {
            builder.append(buffer.readLine());
        }
        String json = builder.toString();

        User user = mapper.readValue(json, User.class);
        User authUser = service.authenticate(user.getUserName(), user.getPassword());

        // check user in postamn -> headers in console to see JWT corresponding userid
        if(authUser != null) {
            resp.setStatus(200);
            resp.getWriter().write(mapper.writeValueAsString(authUser)); // send back a token as a resp
            resp.addHeader("JWT", String.valueOf(authUser.getUserId())); // add a header
        } else {
            resp.setStatus(403);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //test authentication
        String jwt = req.getHeader("JWT");
        System.out.println("JWT Header: " + jwt);

    }
}