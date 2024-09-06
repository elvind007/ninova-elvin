package com.ninova.ninova.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninova.ninova.entity.Material;
import com.ninova.ninova.repository.MaterialRepository;


@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    @Autowired
    private MaterialRepository materialRepository;

    @PostMapping("/add")
    public ResponseEntity<Material> addMaterial(@RequestBody Material material) {
        Material savedMaterial = materialRepository.save(material);
        return ResponseEntity.ok(savedMaterial);
    }

    @GetMapping
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }
}
