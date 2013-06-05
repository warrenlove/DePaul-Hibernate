/* 
 * $Id: AtmDao.java mminella $
 * $Revision:  $
 * $Author: mminella $
 * $Date:  $
 *
 */
package edu.depaul.dao;

import edu.depaul.hibernate.domain.annotation.Card;
import edu.depaul.hibernate.domain.annotation.Customer;
import edu.depaul.hibernate.domain.annotation.Transaction;
import java.io.Serializable;

/**
 * @author mminella
 *
 */
public interface AtmDao extends Serializable {
	
	Customer getCustomerByCard(Card card);
	
	void saveTransaction(Transaction trans);
	
	double getCurrentBalance(String accountNumber);

}
