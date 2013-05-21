package edu.depaul.hibernate.domain.xml;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ComponentPerson {

	private long id;

	private String firstName;
	private String lastName;
	private String middleInitial;
	private Date birthdate;
	private String ssn;
	private ComponentAddress workAddress;
	private ComponentAddress homeAddress;

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

	public ComponentAddress getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(ComponentAddress workAddress) {
		this.workAddress = workAddress;
	}

	public ComponentAddress getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(ComponentAddress homeAddress) {
		this.homeAddress = homeAddress;
	}

	@Override
	public String toString() {
		return String.format("%s %s. %s was born on %s.  My addresses are:\n%s\n%s", firstName, middleInitial, lastName, new SimpleDateFormat("MM/dd/yyyy").format(birthdate), workAddress.toString(), homeAddress.toString());
	}
}