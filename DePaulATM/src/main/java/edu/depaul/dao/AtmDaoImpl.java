/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.depaul.dao;

import edu.depaul.hibernate.domain.annotation.Card;
import edu.depaul.hibernate.domain.annotation.Customer;
import edu.depaul.hibernate.domain.annotation.Transaction;
import java.io.Serializable;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Love
 */
public class AtmDaoImpl extends  HibernateDaoSupport implements AtmDao {

    public Customer getCustomerByCard(Card card) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void saveTransaction(Transaction trans) {
        Session session = getSession(true);
        session.save(trans);
    }

    public double getCurrentBalance(String accountNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
