package com.toy.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

public class Toy implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	@NumberFormat(pattern="#,###.00")
	private Double price;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createDate;
	
	
	@Override
	public String toString() {
		return "Toy [id=" + id + ", name=" + name + ", price=" + price + ", createDate=" + createDate + "]";
	}
	public Toy() {
		super();
	}
	public Toy(Integer id, String name, Double price, Date createDate) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.createDate = createDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
