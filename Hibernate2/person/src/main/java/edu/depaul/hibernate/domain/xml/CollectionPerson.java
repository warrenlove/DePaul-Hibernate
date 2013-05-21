package edu.depaul.hibernate.domain.xml;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CollectionPerson {

	private long id;

	private String firstName;
	private String lastName;
	private String middleInitial;
	private Date birthdate;
	private String ssn;
	private Set<String> nicknames = new HashSet<String>();

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

	public Set<String> getNicknames() {
		return nicknames;
	}

	public void setNicknames(Set<String> nicknames) {
		this.nicknames = nicknames;
	}

	public void addNickname(String name) {
		nicknames.add(name);
	}

	@Override
	public String toString() {
		Iterator<String> nicknameIterator = nicknames.iterator();

		return String.format("%s %s. %s was born on %s and has the following nicknames: %s and %s.", firstName, middleInitial, lastName, new SimpleDateFormat("MM/dd/yyyy").format(birthdate), nicknameIterator.next(), nicknameIterator.next());
	}
}