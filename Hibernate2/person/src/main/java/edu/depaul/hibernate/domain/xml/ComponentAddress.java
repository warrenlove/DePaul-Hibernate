package edu.depaul.hibernate.domain.xml;

public class ComponentAddress {
	private String address1;
	private String city;
	private String state;
	private String zipCode;
	private ComponentPerson parent;

	public String getAddress1() {
		return address1;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setAddress1(String address) {
		address1 = address;
	}
	public void setCity(String newCity) {
		city = newCity;
	}
	public void setState(String newState) {
		state = newState;
	}
	public void setZipCode(String zip) {
		zipCode = zip;
	}

	public ComponentPerson getParent() {
		return parent;
	}
	public void setParent(ComponentPerson parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return String.format("\t%s: %s, %s %s, %s", parent.getFirstName(), address1, city, state, zipCode);
	}
}