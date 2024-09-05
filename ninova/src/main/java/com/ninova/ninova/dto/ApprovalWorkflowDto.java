package com.ninova.ninova.dto;


import com.ninova.ninova.entity.User;

public class ApprovalWorkflowDto {
    private Integer level;
    private User approver;

    // Getters and Setters
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }
}
