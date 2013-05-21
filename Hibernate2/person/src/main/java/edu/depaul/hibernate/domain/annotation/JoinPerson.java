package edu.depaul.hibernate.domain.annotation;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;

@Entity
@SecondaryTables({
    @SecondaryTable(name="Address", pkJoinColumns={
        @PrimaryKeyJoinColumn(name="id", referencedColumnName="id")})})
public class JoinPerson {

	@Id
	@GeneratedValue
	private long id;

	private String firstName;
	private String lastName;
	private String middleInitial;
	private Date birthdate;
	private String ssn;
	@Column(table="Address")
	private String address1;
	@Column(table="Address")
	private String city;
	@Column(table="Address")
	private String state;
	@Column(table="Address")
	private String zip;

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

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return String.format("%s %s. %s was born on %s and lives at %s, %s %s, %s.", firstName, middleInitial, lastName, new SimpleDateFormat("MM/dd/yyyy").format(birthdate), address1, city, state, zip);
	}
}