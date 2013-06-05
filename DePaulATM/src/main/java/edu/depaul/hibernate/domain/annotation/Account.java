package edu.depaul.hibernate.domain.annotation;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    
    private String number;
    private Date currentBalance;
    
    @ManyToMany(cascade=CascadeType.ALL, mappedBy="accounts")
    private Set<Customer> customers = new HashSet<Customer>(0);
    
    @OneToMany(mappedBy = "account")
    private Set<Transaction> transactions = new HashSet<Transaction>(0);

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Date currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addCustomers(Customer customer) {
        customers.add(customer);
    }

    public void addTransactions(Transaction transaction) {
        transactions.add(transaction);
    }
}
