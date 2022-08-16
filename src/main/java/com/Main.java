package com;// Will be replacing our com.Main java class with TOMCAT API Layer

import com.daos.ReimbursementDAO;
import com.daos.UserDAO;
import com.pojos.Reimbursement;
import com.pojos.User;
import com.services.DatasourceService;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        // Testing database connection
        System.out.println("Connecting...");
        Connection connection = DatasourceService.getConnection();
        UserDAO userDao = new UserDAO(connection);
        ReimbursementDAO rDao = new ReimbursementDAO(connection);

        // Testing DBeaver User CRUD
        // Adding new user
        User hazel = new User("hazel", "nguyen", "hazelnguyen", "hazel@gmail.com", "Password01!");
        User kyle = new User("kyle", "plummer", "kplummer", "kyle@gmail.com", "Password01!");
        User jaesha = new User("jaesha", "last", "jaeshalast", "jaesha@gmail.com", "Password01!");

        //create new user (run once)
        //userDao.create(hazel);
        //userDao.create(kyle);
        //userDao.create(jaesha);

        // Get User with ID
//        System.out.println("Get user: " + userDao.read(1));
//
//        // Get All Users
//        System.out.println("Get All Users: " + userDao.readAll());
//
//        // Delete User with ID
//        userDao.delete(3);
//        System.out.println("user 3 deleted!");
//        System.out.println("Updated List: " + userDao.readAll());

        // Testing DBeaver Reimbursement CRUD
        Reimbursement r1 = new Reimbursement("TRAVEL", "June", 1000, "went to paris", "Pending", 1 );

        // Create new reimbursement request
        //rDao.create(r1);

        // Read Request with Reimbursement_ID
        //System.out.println("Get Request with ID: " + rDao.read(1));

        // Read All Requests
        //System.out.println("Get All Requests: " + rDao.readAll());

        // Delete Reimbursement Request by reimbursement_id
//        rDao.delete(3);
//        System.out.println("reimbursment_id 3 deleted");
//        System.out.println("updated list: " + rDao.readAll());

        System.out.println("Done!");

    }
}
