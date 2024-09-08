package com.ninova.ninova.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninova.ninova.dto.ApprovalRequest;
import com.ninova.ninova.dto.PurchaseOrderDto;
import com.ninova.ninova.entity.ApprovalWorkflow;
import com.ninova.ninova.entity.Material;
import com.ninova.ninova.entity.PurchaseOrder;
import com.ninova.ninova.service.PurchaseOrderService;
import com.ninova.ninova.repository.*;

@RestController
@RequestMapping("/api/po")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;
    private final MaterialRepository materialRepository;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService, MaterialRepository materialRepository) {
        this.purchaseOrderService = purchaseOrderService;
        this.materialRepository = materialRepository;
    }


    @PostMapping("/create")
    public ResponseEntity<PurchaseOrder> createPO(@RequestBody PurchaseOrderDto poDto) {
        PurchaseOrder po = new PurchaseOrder();
        po.setTotalAmount(poDto.getTotalAmount());
        po.setUser(poDto.getUser());
        po.setStatus("PENDING");
   
        
        // Inside createPO method
        Material material = materialRepository.findById(poDto.getMaterialId())
            .orElseThrow(() -> new RuntimeException("Material not found"));
    
        List<ApprovalWorkflow> workflows = poDto.getWorkflows().stream()
            .map(wfDto -> {
                ApprovalWorkflow workflow = new ApprovalWorkflow();
                workflow.setLevel(wfDto.getLevel());
                workflow.setApprover(wfDto.getApprover());
                workflow.setStatus("PENDING");
                return workflow;
            }).collect(Collectors.toList());
    
        PurchaseOrder createdPO = purchaseOrderService.createPO(po, workflows);
        return ResponseEntity.ok(createdPO);
    }

    @PostMapping("/approve/{workflowId}")
    public ResponseEntity<String> approvePO(@PathVariable Long workflowId, @RequestBody ApprovalRequest request) {
        purchaseOrderService.approvePO(workflowId, request.getApproverId(), request.getDecision());
        return ResponseEntity.ok("PO Approval updated");
    }

    @GetMapping("/{poId}/workflows")
    public ResponseEntity<List<ApprovalWorkflow>> getApprovalSteps(@PathVariable Long poId) {
        List<ApprovalWorkflow> workflows = purchaseOrderService.getApprovalSteps(poId);
        return ResponseEntity.ok(workflows);
    }

   
}
