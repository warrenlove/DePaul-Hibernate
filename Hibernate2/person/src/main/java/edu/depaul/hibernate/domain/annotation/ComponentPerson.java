package edu.depaul.hibernate.domain.annotation;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ComponentPerson {

	@Id
	@GeneratedValue
	private long id;

	private String firstName;
	private String lastName;
	private String middleInitial;
	private Date birthdate;
	private String ssn;

	@Embedded
	@AttributeOverrides({
        @AttributeOverride(name="address1", column=@Column(name="WORK_ADDRESS1")),
        @AttributeOverride(name="city", column=@Column(name="WORK_CITY")),
        @AttributeOverride(name="state", column=@Column(name="WORK_STATE")),
        @AttributeOverride(name="zipCode", column=@Column(name="WORK_ZIPCODE")),
    })
    private ComponentAddress workAddress;
	@Embedded
	@AttributeOverrides({
        @AttributeOverride(name="address1", column=@Column(name="HOME_ADDRESS1")),
        @AttributeOverride(name="city", column=@Column(name="HOME_CITY")),
        @AttributeOverride(name="state", column=@Column(name="HOME_STATE")),
        @AttributeOverride(name="zipCode", column=@Column(name="HOME_ZIPCODE")),
    })
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