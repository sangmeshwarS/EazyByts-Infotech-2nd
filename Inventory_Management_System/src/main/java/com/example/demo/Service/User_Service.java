package com.example.demo.Service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Pojo.User;

public interface User_Service extends JpaRepository<User, Integer>{
	
	@Query("select u from User u where u.email = ?1 and u.password = ?2")
	public List<User> getByEmailAndPassword(@Param("email")String email,@Param("password") String password);
	
	@Query("select u from User u where u.email = :email")
	public User findByEmail(@Param("email")String email);

}
