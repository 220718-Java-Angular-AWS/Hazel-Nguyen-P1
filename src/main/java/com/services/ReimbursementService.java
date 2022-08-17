package com.services;

import com.daos.ReimbursementDAO;
import com.pojos.Reimbursement;

import java.util.List;

public class ReimbursementService {

    private ReimbursementDAO dao;

    public ReimbursementService() {
        this.dao = new ReimbursementDAO();
    }

    public void saveReimbursment(Reimbursement reimbursement) {
        dao.create(reimbursement);
    }

    public Reimbursement getReimbursement(int id) {
        return dao.read(id);
    }

    public List<Reimbursement> getAllReimbursements() {
        return dao.readAll();
    }

    public List<Reimbursement> getReimbursementsForUser(Integer userId) {
        List<Reimbursement> taskList = dao.readAll();

        for(Reimbursement task : taskList) {
            if(!task.getUserID().equals(userId)) {
                taskList.remove(task);
            }
        }

        return taskList;
    }

    public void updateReimbursement(Reimbursement reimbursement) {
        dao.update(reimbursement);
    }

    public void deleteReimbursement(int id) {
        dao.delete(id);
    }
}
