/* 
 * $Id: AtmService.java mminella $
 * $Revision:  $
 * $Author: mminella $
 * $Date:  $
 */
package edu.depaul.service;

import java.util.List;

import edu.depaul.hibernate.domain.annotation.Card;
import edu.depaul.hibernate.domain.annotation.Customer;
import edu.depaul.hibernate.domain.annotation.Transaction;
import java.io.Serializable;

/**
 * @author mminella
 *
 */
public interface AtmService extends Serializable {

	List<Double> getBalancesByCard(Card card);
	
	List<Transaction> getLastTransactions(Card card, int qty);
	
	Customer getCustomer(Card card);
}
