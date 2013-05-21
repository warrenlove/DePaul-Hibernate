package edu.depaul.hibernate.domain.annotation;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OneToOneCity {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	@OneToOne(mappedBy="city", cascade=CascadeType.ALL)
	private OneToOneMayor mayor;
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

	public OneToOneMayor getMayor() {
		return mayor;
	}
	public void setMayor(OneToOneMayor mayor) {
		this.mayor = mayor;
	}
	@Override
	public String toString() {
		return String.format("%s with mayor %s %s", name, mayor.getFirstName(), mayor.getLastName());
	}
}
