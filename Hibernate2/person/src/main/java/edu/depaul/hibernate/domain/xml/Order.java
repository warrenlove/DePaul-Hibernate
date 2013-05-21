package edu.depaul.hibernate.domain.xml;

import java.util.HashSet;
import java.util.Set;

public class Order {

	private long id;
	private String customerName;
	private Set<Item> items = new HashSet<Item>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Set<Item> getItems() {
		return items;
	}
	public void setItems(Set<Item> items) {
		this.items = items;
	}
}
