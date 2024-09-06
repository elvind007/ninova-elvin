package com.ninova.ninova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninova.ninova.entity.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

}