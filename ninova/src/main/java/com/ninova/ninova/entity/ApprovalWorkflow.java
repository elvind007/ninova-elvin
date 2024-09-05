package com.ninova.ninova.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "approval_workflow")
@Data
public class ApprovalWorkflow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "po_id")
    private PurchaseOrder purchaseOrder;

    private Integer level; // The level of approval (1, 2, 3, etc.)

    @ManyToOne
    @JoinColumn(name = "approver_id")
    private User approver; // The user who approves this level

    private String status; // e.g., PENDING, APPROVED, REJECTED
}
