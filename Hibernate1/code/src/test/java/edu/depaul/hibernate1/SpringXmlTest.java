package edu.depaul.hibernate1;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.depaul.hibernate.domain.Message;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringXmlTest {

	private Session session;

	@Autowired
	public SessionFactory sessionFactory;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	@Transactional
	public void test() throws Exception {
		session = sessionFactory.getCurrentSession();

		insertMessages();
		displayMessages();
		deleteMessages();

		insertMessageChain();
		displayMessageChain();
		deleteMessages();

		displayMessages();
	}

	public void insertMessageChain() {
		Message chain = createMessage("This is the first message");
		chain.setNextMessage(createMessage("This is the second message"));
		chain.getNextMessage().setNextMessage(createMessage("This is the thrid message"));

		session.save(chain);
	}

	public void insertMessages() {
		session.save(createMessage("Hello, World!"));
		session.save(createMessage("Hello, Michael!"));
		session.save(createMessage("Visit http://www.michaelminella.com"));
	}

	private Message createMessage(String value) {
		Message message = new Message();
		message.setMessage(value);
		return message;
	}

	@SuppressWarnings("unchecked")
	public void displayMessageChain() {
		List<Message> messages = session.createQuery("from Message").list();

		System.out.println("*********************************************");
		System.out.println("          Here are the chains");
		System.out.println("*********************************************");

		if(messages.size() > 0) {
			for(Message message : messages) {
				System.out.println("A new chain:");
				do {
					System.out.println(message.getMessage());
					message = message.getNextMessage();
				} while(message != null);
			}
		} else {
			System.out.println("There are none!");
		}
	}

	@SuppressWarnings("unchecked")
	public void displayMessages() {
		List<Message> messages = session.createQuery("from Message").list();

		System.out.println("*********************************************");
		System.out.println("          Here are the messages");
		System.out.println("*********************************************");

		if(messages.size() > 0) {
			for(Message message : messages) {
				System.out.println(message.getMessage());
			}
		} else {
			System.out.println("There are none!");
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteMessages() {
		List<Message> messages = session.createQuery("from Message").list();

		for(Message message : messages) {
			session.delete(message);
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
