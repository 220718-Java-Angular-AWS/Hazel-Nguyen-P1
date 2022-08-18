package com.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojos.Reimbursement;
import com.pojos.User;
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
        //String userId = req.getParameter("user-id");
        //String reimbursementId = req.getParameter("reimbursement-id");
        Integer userId = Integer.parseInt(req.getParameter("user-id"));
        //Integer reimbursementId = Integer.parseInt(req.getParameter("reimbursement-id"));

        List<Reimbursement> taskList = service.getReimbursementsForUser(userId);
        String json = mapper.writeValueAsString(taskList);
        resp.getWriter().print(json);

//        if (userId != null){
//            List<Reimbursement> taskList = service.getReimbursementsForUser(userId);
//            String json = mapper.writeValueAsString(taskList);
//            resp.getWriter().print(json);
//        } else {
//            Reimbursement request = service.getReimbursement(reimbursementId);
//            String json = mapper.writeValueAsString(request);
//            resp.getWriter().print(json);
//        }


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

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: write put request
        System.out.println("Updating Reimbursement Request..");

        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while(buffer.ready()) {
            builder.append(buffer.readLine());
        }
        String json = builder.toString();

        Reimbursement updateRequest = mapper.readValue(json, Reimbursement.class);
        service.updateReimbursement(updateRequest);

        resp.setStatus(200);
        resp.getWriter().print("Sucessfully Updated Reimbursement!");
        System.out.println("Successfully Updated! Go Check DB");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Deleting Reimbursement...");
        Integer reimbursementId = Integer.parseInt(req.getParameter("reimbursement-id"));
        service.deleteReimbursement(reimbursementId);

        resp.setStatus(200);
        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.getWriter().print("Sucessfully Deleted Reimbursement!");
        System.out.println("Successfully Deleted! Go Check DB");
    }
}
