package com.pojos;

import java.util.Objects;

public class Reimbursement {
    private Integer reimbursementID;
    private String reimbursementType;
    private String dateOfRequest;
    private double amount;
    private String comment;
    private String statusOfRequest;
    private Integer userID;

    // TODO: ask kyle for special functions for timestamp or date in SQL
    public Reimbursement() {
    }

    public Reimbursement(Integer reimbursementID, String reimbursementType, String dateOfRequest, double amount, String comment, String statusOfRequest, Integer userID) {
        this.reimbursementID = reimbursementID;
        this.reimbursementType = reimbursementType;
        this.dateOfRequest = dateOfRequest;
        this.amount = amount;
        this.comment = comment;
        this.statusOfRequest = statusOfRequest;
        this.userID = userID;
    }

    // Write another constructor to take in parameters without reimbursementid
    public Reimbursement(String reimbursementType, String dateOfRequest, double amount, String comment, String statusOfRequest, Integer userID) {
        this.reimbursementType = reimbursementType;
        this.dateOfRequest = dateOfRequest;
        this.amount = amount;
        this.comment = comment;
        this.statusOfRequest = statusOfRequest;
        this.userID = userID;
    }

    public Integer getReimbursementID() {
        return reimbursementID;
    }

    public void setReimbursementID(Integer reimbursementID) {
        this.reimbursementID = reimbursementID;
    }

    public String getReimbursementType() {
        return reimbursementType;
    }

    public void setReimbursementType(String reimbursementType) {
        this.reimbursementType = reimbursementType;
    }

    public String getDateOfRequest() {
        return dateOfRequest;
    }

    public void setDateOfRequest(String dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatusOfRequest() {
        return statusOfRequest;
    }

    public void setStatusOfRequest(String statusOfRequest) {
        this.statusOfRequest = statusOfRequest;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return Double.compare(that.amount, amount) == 0 &&
                Objects.equals(reimbursementID, that.reimbursementID) &&
                Objects.equals(reimbursementType, that.reimbursementType) &&
                Objects.equals(dateOfRequest, that.dateOfRequest) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(statusOfRequest, that.statusOfRequest) &&
                Objects.equals(userID, that.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbursementID, reimbursementType, dateOfRequest, amount, comment, statusOfRequest, userID);
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbursementID=" + reimbursementID +
                ", reimbursementType='" + reimbursementType + '\'' +
                ", dateOfRequest='" + dateOfRequest + '\'' +
                ", amount=" + amount +
                ", comment='" + comment + '\'' +
                ", statusOfRequest='" + statusOfRequest + '\'' +
                ", userID=" + userID +
                '}';
    }
}
