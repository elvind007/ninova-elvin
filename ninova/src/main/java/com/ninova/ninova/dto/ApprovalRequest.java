package com.ninova.ninova.dto;


public class ApprovalRequest {
    private Long approverId;
    private String decision; // Either "APPROVED" or "REJECTED"

    // Getters and Setters
    public Long getApproverId() {
        return approverId;
    }

    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}
