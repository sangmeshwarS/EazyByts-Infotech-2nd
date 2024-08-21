package com.example.demo.Controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Pojo.Inventory_Pojo;
import com.example.demo.Pojo.Order_Pojo;
import com.example.demo.Pojo.Product_Pojo;
import com.example.demo.Pojo.User;
import com.example.demo.Service.Inventory_Service;
import com.example.demo.Service.Order_Service;
import com.example.demo.Service.Product_Service;
import com.example.demo.Service.User_Service;

import jakarta.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
public class Controller {
	@Autowired
	private User_Service uservice;
	@Autowired
	private Product_Service pservice;
	@Autowired
	private Order_Service oservice;
	@Autowired
	private Inventory_Service iservice;
	
	@RequestMapping("/index")
	public String index(Model m, HttpSession s) {
		s.removeAttribute("notlogin");
		List<Product_Pojo> li =	this.pservice.findAll();
		m.addAttribute("plist", li);
		List<Order_Pojo> oli = this.oservice.findAll();
		m.addAttribute("olist", oli);
		return "index";
	}
	
	@RequestMapping("/Login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/processLogin")
	public String processLogin(@RequestParam("email")String email, @RequestParam("password")String password, Model m, HttpSession s) {
	
		List<User> li =this.uservice.getByEmailAndPassword(email, password);
		
		m.addAttribute("userList", li);
		s.setAttribute("name", li.get(0).getName());
	
	 if(li.isEmpty()) {
		 return "redirect:/Login";
	 }else {
		 s.setAttribute("login", "Login Successful");
		 s.setAttribute("email", email);
		 return "redirect:/index";
	 }
	}
	
	@RequestMapping("/Register")
	public String register() {
		return "register";
	}
	
	@RequestMapping("/processUser")
	public String processUser(@ModelAttribute("user")User user, Model m) {
		m.addAttribute("Users", user);
		this.uservice.save(user);
		return "login";
	}
	
	@RequestMapping("/GetUsers")
	public String getUsers(Model m) {
	List<User> li =	this.uservice.findAll();
	
	m.addAttribute("userList", li);
		return "getUsers";
	}
	
	@RequestMapping("/UpdateUser")
	public String updateUser(Model m, HttpSession s) {
		String e = (String) s.getAttribute("email");
		if(e==null) {
			s.setAttribute("notlogin", "You are not login yet");
			return "redirect:/index";
		}
		
		User u = this.uservice.findByEmail(e);
		m.addAttribute("ulist", u);
		return "updateUser";
	}
	
	@RequestMapping("/updateUser")
	public String updateUser(@RequestParam("uname")String name, @RequestParam("uemail")String email, @RequestParam("upassword")String password, Model m, HttpSession s) {
		
		String e = (String) s.getAttribute("email");
		
		User u = this.uservice.findByEmail(e);
		m.addAttribute("user", u);
		if(u==null) {
			m.addAttribute("error", "No user found");
			return "redirect:/updateUser";
		}
		u.setName(name);
		u.setEmail(email);
		u.setPassword(password);
		this.uservice.save(u);
		return "index";
	}
	
	@RequestMapping("/Product")
	public String product() {
		return "product";
	}
	
	@RequestMapping("/processProduct")
	public String processProduct(@RequestParam("pname")String name,@RequestParam("pdesc")String desc,@RequestParam("pquantity")int quantity,@RequestParam("pactualprice")int actualprice,@RequestParam("pprice")int price,@RequestParam("pdiscount")int discount , @RequestParam("pimage")MultipartFile pimage, HttpSession s) throws IOException {
		String email =	(String) s.getAttribute("email");
		Product_Pojo p = new Product_Pojo(name, desc, quantity, actualprice, price, discount, pimage.getOriginalFilename(), email);
		Product_Pojo p1 =	this.pservice.save(p);
		if(p1!=null) {
			
		}
		File f = new ClassPathResource("static/Images").getFile();
		Path path = Paths.get(f.getAbsolutePath()+File.separator+pimage.getOriginalFilename());
		System.out.println(path);
		Files.copy(pimage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		return "redirect:/index";
	}
	
	@RequestMapping("/getProducts")
	public String getProducts(Model m) {
		List<Product_Pojo> li =	this.pservice.findAll();
		m.addAttribute("plist", li);
		return "getProducts";
	}
	
	@RequestMapping("/updateProduct")
	public String updateProduct() {
		return "updateProductById";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession s) {
		s.removeAttribute("login");
		s.removeAttribute("email");
		return "redirect:/Login";
	}
	
	@RequestMapping("/placeOrder")
	public String placeOrder(@RequestParam("id")int id, Model m) {
		Optional<Product_Pojo> o =	this.pservice.findById(id);
		Product_Pojo p =o.get();
		m.addAttribute("olist", p);
		Order_Pojo o1 = new Order_Pojo(p.getPname(), 1, p.getPprice(), new Date().toLocaleString(), "Pending", p.getPimage(), p.getUemail());
		Order_Pojo o2 = this.oservice.save(o1);
		if(o2!=null) {
			int i =	p.getPquantity() - 1;
			p.setPquantity(i);
			this.pservice.save(p);
		}
		return "placeOrder";
	}
	
	@RequestMapping("/getOrders")
	public String getOrder(Model m) {
		List<Order_Pojo> o = this.oservice.findAll();
		m.addAttribute("olist", o);
		return "getOrders";
	}
	
	@RequestMapping("/deleteOrder")
	public String deleteOrder(@RequestParam("id")int id) {
		this.pservice.deleteById(id);
		return "redirect:/getProducts";
	}
	
	@RequestMapping("/addInventory")
	public String addInventory() {
		return "addInventory";
	}
	
	@RequestMapping("/processInventory")
	public String processInventory(@RequestParam("pname")String name,@RequestParam("pdesc")String desc,@RequestParam("pquantity")int quantity,@RequestParam("pactualprice")int actualprice,@RequestParam("pprice")int price,@RequestParam("pdiscount")int discount , @RequestParam("pimage")MultipartFile pimage, HttpSession s) throws IOException {
		String email =	(String) s.getAttribute("email");
		Inventory_Pojo p = new Inventory_Pojo(name, desc, quantity, actualprice, price, discount, pimage.getOriginalFilename(), email);
		this.iservice.save(p);
		File f = new ClassPathResource("static/Images").getFile();
		Path path = Paths.get(f.getAbsolutePath()+File.separator+pimage.getOriginalFilename());
		System.out.println(path);
		Files.copy(pimage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		return "redirect:/index";
	}
	
	@RequestMapping("/getInventories")
	public String getInventories(Model m) {
		List<Inventory_Pojo> li =	this.iservice.findAll();
		m.addAttribute("ilist", li);
		return "getInventories";
	}

}
