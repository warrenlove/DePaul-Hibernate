package edu.depaul.hibernate.domain.xml;

import java.util.Date;

public class OneToOneMayor {

	private long id;

	private String firstName;
	private String lastName;
	private String middleInitial;
	private Date birthdate;
	private String ssn;
	private OneToOneCity city;

	public void setId(long newId) {
		id = newId;
	}

	public long getId() {
		return id;
	}

	public void setFirstName(String newName) {
		firstName = newName;
	}

	public void setLastName(String newName) {
		lastName = newName;
	}

	public void setMiddleInitial(String newName) {
		middleInitial = newName;
	}

	public void setBirthdate(Date newName) {
		birthdate = newName;
	}

	public void setSsn(String newName) {
		ssn = newName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public String getSsn() {
		return ssn;
	}

	public OneToOneCity getCity() {
		return city;
	}

	public void setCity(OneToOneCity city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return String.format("%s %s. %s is mayor of %s.", firstName, middleInitial, lastName, city.getName());
	}
}