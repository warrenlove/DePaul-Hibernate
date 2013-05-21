package edu.depaul.hibernate.domain.annotation;

import javax.persistence.Embeddable;

@Embeddable
public class ComponentAddress {
	private String address1;
	private String city;
	private String state;
	private String zipCode;

	public ComponentAddress() {}

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

	@Override
	public String toString() {
		return String.format("\t%s, %s %s, %s", address1, city, state, zipCode);
	}
}