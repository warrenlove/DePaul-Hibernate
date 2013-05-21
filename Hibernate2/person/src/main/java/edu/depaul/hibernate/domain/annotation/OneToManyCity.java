package edu.depaul.hibernate.domain.annotation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OneToManyCity {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String mayor;

	@ManyToOne
	@JoinColumn(name="state_fk")
	private OneToManyState state;
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
	public String getMayor() {
		return mayor;
	}
	public void setMayor(String mayor) {
		this.mayor = mayor;
	}
	public OneToManyState getState() {
		return state;
	}
	public void setState(OneToManyState state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return String.format("%s with mayor %s is in state %s", name, mayor, state.getName());
	}
}
