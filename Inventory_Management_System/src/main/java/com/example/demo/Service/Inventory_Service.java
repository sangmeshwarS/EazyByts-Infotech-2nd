package com.example.demo.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Pojo.Inventory_Pojo;

public interface Inventory_Service extends JpaRepository<Inventory_Pojo, Integer> {

}
