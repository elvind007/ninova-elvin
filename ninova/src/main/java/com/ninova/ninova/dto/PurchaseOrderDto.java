package com.ninova.ninova.dto;


import java.math.BigDecimal;
import java.util.List;

import com.ninova.ninova.entity.User;

public class PurchaseOrderDto {
    private BigDecimal totalAmount;
    private User user;
    private List<ApprovalWorkflowDto> workflows;

    // Getters and Setters
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ApprovalWorkflowDto> getWorkflows() {
        return workflows;
    }

    public void setWorkflows(List<ApprovalWorkflowDto> workflows) {
        this.workflows = workflows;
    }
}
