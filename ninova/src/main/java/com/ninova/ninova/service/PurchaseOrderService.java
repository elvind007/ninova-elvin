package com.ninova.ninova.service;

import com.ninova.ninova.entity.PurchaseOrder;
import com.ninova.ninova.entity.ApprovalWorkflow;
import com.ninova.ninova.repository.PurchaseOrderRepository;
import com.ninova.ninova.repository.ApprovalWorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ApprovalWorkflowRepository approvalWorkflowRepository;

    @Transactional
    public PurchaseOrder createPO(PurchaseOrder po, List<ApprovalWorkflow> workflows) {
        // Save PO to the database
        PurchaseOrder savedPO = purchaseOrderRepository.save(po);

        // Save each approval workflow step for the PO
        for (ApprovalWorkflow workflow : workflows) {
            workflow.setPurchaseOrder(savedPO);
            approvalWorkflowRepository.save(workflow);
        }

        return savedPO;
    }

    public List<ApprovalWorkflow> getApprovalSteps(Long poId) {
        return approvalWorkflowRepository.findByPurchaseOrderId(poId);
    }

    public void approvePO(Long workflowId, Long approverId, String decision) {
        ApprovalWorkflow workflow = approvalWorkflowRepository.findById(workflowId).orElseThrow();
        if (workflow.getApprover().getId().equals(approverId)) {
            workflow.setStatus(decision);
            approvalWorkflowRepository.save(workflow);

            // Optionally, you can check if all steps are approved and update the PO status
            if ("APPROVED".equals(decision)) {
                updatePurchaseOrderStatus(workflow.getPurchaseOrder());
            }
        }
    }

    private void updatePurchaseOrderStatus(PurchaseOrder po) {
        List<ApprovalWorkflow> workflows = approvalWorkflowRepository.findByPurchaseOrderId(po.getId());

        boolean allApproved = workflows.stream().allMatch(wf -> "APPROVED".equals(wf.getStatus()));
        if (allApproved) {
            po.setStatus("APPROVED");
            purchaseOrderRepository.save(po);
        }
    }
}

