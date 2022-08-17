package com.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojos.Reimbursement;
import com.services.ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ReimbursementServlet extends HttpServlet {
    private ReimbursementService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        System.out.println("Reimbursement servlet initializing...");
        this.service = new ReimbursementService();
        this.mapper = new ObjectMapper();
        System.out.println("Reimbursement servlet initialized!");
    }

    @Override
    public void destroy() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = Integer.parseInt(req.getParameter("user-id"));

        List<Reimbursement> taskList = service.getReimbursementsForUser(userId);

        String json = mapper.writeValueAsString(taskList);

        resp.getWriter().print(json);
        resp.setStatus(200);
        resp.setContentType("Application/Json; Charset=UTF-8");
        //throw new ArithmeticException();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();

        while(buffer.ready()) {
            builder.append(buffer.readLine());
        }

        String json = builder.toString();
        Reimbursement request = mapper.readValue(json, Reimbursement.class);

        service.saveReimbursment(request);

        resp.setStatus(200);
        resp.getWriter().print("Sucessfully Posted New Reimbursement Request!");
        System.out.println("Successfully Posted! Go Check DB");

    }
}
