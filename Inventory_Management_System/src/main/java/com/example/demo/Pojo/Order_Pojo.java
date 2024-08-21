package com.example.demo.Pojo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Order_Pojo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int oid;
	private String pname;
	private int pquantity;
	private int totalPrice;
	private String odate;
	private String status;
	private String pimage;
	private String uemail;
	public Order_Pojo(String pname, int pquantity, int totalPrice, String odate, String status, String pimage,
			String uemail) {
		super();
		this.pname = pname;
		this.pquantity = pquantity;
		this.totalPrice = totalPrice;
		this.odate = odate;
		this.status = status;
		this.pimage = pimage;
		this.uemail = uemail;
	}
	public Order_Pojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPquantity() {
		return pquantity;
	}
	public void setPquantity(int pquantity) {
		this.pquantity = pquantity;
	}
	public String getOdate() {
		return odate;
	}
	public void setOdate(String odate) {
		this.odate = odate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "Order_Pojo [oid=" + oid + ", pname=" + pname + ", pquantity=" + pquantity + ", totalPrice=" + totalPrice
				+ ", odate=" + odate + ", status=" + status + ", pimage=" + pimage + ", uemail=" + uemail + "]";
	}

	
	

}
