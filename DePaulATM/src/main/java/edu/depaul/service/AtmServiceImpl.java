/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.depaul.service;

import edu.depaul.hibernate.domain.annotation.Card;
import edu.depaul.hibernate.domain.annotation.Customer;
import edu.depaul.hibernate.domain.annotation.Transaction;

import java.util.List;

/**
 *
 * @author Love
 */
public class AtmServiceImpl implements AtmService {

    public List<Double> getBalancesByCard(Card card) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Transaction> getLastTransactions(Card card, int qty) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Customer getCustomer(Card card) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
