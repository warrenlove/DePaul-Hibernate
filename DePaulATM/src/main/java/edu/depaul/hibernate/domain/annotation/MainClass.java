package edu.depaul.hibernate.domain.annotation;

import edu.depaul.dao.*;
import java.util.Date;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

    public static void main(String[] args) {
        AbstractApplicationContext factory;
        factory = new ClassPathXmlApplicationContext("config/springHibernate-db-Config.xml");
        AtmDao atmDao = (AtmDao) factory.getBean("atmDaoImpl");
/*
        Transaction trans = new Transaction();
        trans.setAmount("100");
        trans.setType("Deposit");

        Account account = new Account();
        account.setCurrentBalance(new Date());
        account.setNumber("123456789");

        trans.setAccount(account);

        atmDao.saveTransaction(trans);

*/


    }
}
