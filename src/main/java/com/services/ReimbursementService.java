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

//    public List<Task> getTasksForUser(Integer userId) {
//        List<Task> taskList = dao.readAll();
//
//        for(Task task : taskList) {
//            if(!task.getUserId().equals(userId)) {
//                taskList.remove(task);
//            }
//        }
//
//        return taskList;
//    }

    public void updateReimbursement(Reimbursement reimbursement) {
        dao.update(reimbursement);
    }

    public void deleteTask(int id) {
        dao.delete(id);
    }
}
