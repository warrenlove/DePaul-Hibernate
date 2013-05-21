package edu.depaul.hibernate.domain.xml;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Item {
	private long id;
	private String name;
	private int number;
	private BigDecimal price;
	private Set<Order> orders = new HashSet<Order>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
}
