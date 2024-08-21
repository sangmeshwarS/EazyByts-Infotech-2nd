package com.example.demo.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Pojo.Order_Pojo;

public interface Order_Service extends JpaRepository<Order_Pojo, Integer>{

}
