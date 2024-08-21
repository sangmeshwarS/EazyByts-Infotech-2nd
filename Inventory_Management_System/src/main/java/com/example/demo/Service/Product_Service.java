package com.example.demo.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Pojo.Product_Pojo;

public interface Product_Service extends JpaRepository<Product_Pojo, Integer>{

}
