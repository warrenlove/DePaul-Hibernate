package edu.depaul.hibernate.domain.annotation;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String middleIntial;
    private String ssn;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipcode;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customer_account",
            joinColumns = {
        @JoinColumn(name = "customer_id")},
            inverseJoinColumns = {
        @JoinColumn(name = "accoutn_id")})
    private Set<Account> accounts = new HashSet<Account>(0);
   
    @OneToOne(fetch=FetchType.LAZY, mappedBy="customer")
    private Card card;

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleIntial() {
        return middleIntial;
    }

    public void setMiddleIntial(String middleIntial) {
        this.middleIntial = middleIntial;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void addAccounts(Account account) {
        accounts.add(account);
    }
}
