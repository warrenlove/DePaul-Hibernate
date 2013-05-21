package edu.depaul.hibernate.domain.annotation;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="order_table")
public class Order {

	@Id
	@GeneratedValue
	private long id;
	private String customerName;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "order_item",
	joinColumns = { @JoinColumn(name = "order_id") },
	inverseJoinColumns = { @JoinColumn(name = "item_id") })
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
