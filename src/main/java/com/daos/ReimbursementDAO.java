package com.daos;

import com.pojos.Reimbursement;
import com.services.DatasourceService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ReimbursementDAO implements DatasourceCRUD<Reimbursement> {
    Connection connection;

    public ReimbursementDAO() {
        connection = DatasourceService.getConnection();
    }

    // add this to test in main function
    public ReimbursementDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Reimbursement reimbursement) {
        try {
            // create the statement
            // paratmeterize the statement
            // execute the statement
            // marshall the result set (READ ONLY)

            // PreparedStatment is an interface
            String sql = "INSERT INTO reimbursements (reimbursement_type, date_of_request, amount, comment, status_of_request, user_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, reimbursement.getReimbursementType());
            pstmt.setString(2, reimbursement.getDateOfRequest());
            pstmt.setDouble(3, reimbursement.getAmount());
            pstmt.setString(4, reimbursement.getComment());
            pstmt.setString(5, reimbursement.getStatusOfRequest());
            pstmt.setInt(6, reimbursement.getUserID());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Reimbursement read(int id) {
        Reimbursement reimbursement = new Reimbursement();

        try {
            String sql = "SELECT * FROM reimbursements WHERE reimbursement_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet results = pstmt.executeQuery();

            // marshall the result set into an object Reimbursement
            // in the parameter, use the name convention in sql
            if(results.next()) {
                reimbursement.setReimbursementID(results.getInt("reimbursement_id"));
                reimbursement.setReimbursementType(results.getString("reimbursement_type"));
                reimbursement.setDateOfRequest(results.getString("date_of_request"));
                reimbursement.setAmount(results.getDouble("amount"));
                reimbursement.setComment(results.getString("comment"));
                reimbursement.setStatusOfRequest(results.getString("status_of_request"));
                reimbursement.setUserID(results.getInt("user_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reimbursement;
    }

    @Override
    public List<Reimbursement> readAll() {
        List<Reimbursement> list = new LinkedList<>();

        try {
            String sql = "SELECT * FROM reimbursements";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            ResultSet results = pstmt.executeQuery();

            while(results.next()) {
                Reimbursement reimbursement = new Reimbursement();
                reimbursement.setReimbursementID(results.getInt("reimbursement_id"));
                reimbursement.setReimbursementType(results.getString("reimbursement_type"));
                reimbursement.setDateOfRequest(results.getString("date_of_request"));
                reimbursement.setAmount(results.getDouble("amount"));
                reimbursement.setComment(results.getString("comment"));
                reimbursement.setStatusOfRequest(results.getString("status_of_request"));
                reimbursement.setUserID(results.getInt("user_id"));
                list.add(reimbursement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void update(Reimbursement reimbursement) {

        // TODO: ask kyle if there's a way to break sql statement to make it more readable
        try {
            String sql = "UPDATE reimbursements SET reimbursement_type = ?, date_of_request = ?, amount = ?, comment = ?, status_of_request = ?, user_id = ? WHERE reimbursement_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, reimbursement.getReimbursementType());
            pstmt.setString(2, reimbursement.getDateOfRequest());
            pstmt.setDouble(3, reimbursement.getAmount());
            pstmt.setString(4, reimbursement.getComment());
            pstmt.setString(5, reimbursement.getStatusOfRequest());
            pstmt.setInt(6, reimbursement.getUserID());
            pstmt.setInt(7, reimbursement.getReimbursementID());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // delete by reimbursement_id
    public void delete(int id) {
        try {
            String sql = "DELETE FROM reimbursements WHERE reimbursement_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

}
