package com.example.demo.Pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Inventory_Pojo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String pname;
	private String pdesc;
	private int pquantity;
	private int pactualprice;
	private int pprice;
	private int pdiscount;
	private String pimage;
	private String uemail;
	@Override
	public String toString() {
		return "Inventory_Pojo [id=" + id + ", pname=" + pname + ", pdesc=" + pdesc + ", pquantity=" + pquantity
				+ ", pactualprice=" + pactualprice + ", pprice=" + pprice + ", pdiscount=" + pdiscount + ", pimage="
				+ pimage + ", uemail=" + uemail + "]";
	}
	public Inventory_Pojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public int getPquantity() {
		return pquantity;
	}
	public void setPquantity(int pquantity) {
		this.pquantity = pquantity;
	}
	public int getPactualprice() {
		return pactualprice;
	}
	public void setPactualprice(int pactualprice) {
		this.pactualprice = pactualprice;
	}
	public int getPprice() {
		return pprice;
	}
	public void setPprice(int pprice) {
		this.pprice = pprice;
	}
	public int getPdiscount() {
		return pdiscount;
	}
	public void setPdiscount(int pdiscount) {
		this.pdiscount = pdiscount;
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
	public Inventory_Pojo(String pname, String pdesc, int pquantity, int pactualprice, int pprice, int pdiscount,
			String pimage, String uemail) {
		super();
		this.pname = pname;
		this.pdesc = pdesc;
		this.pquantity = pquantity;
		this.pactualprice = pactualprice;
		this.pprice = pprice;
		this.pdiscount = pdiscount;
		this.pimage = pimage;
		this.uemail = uemail;
	}
}
