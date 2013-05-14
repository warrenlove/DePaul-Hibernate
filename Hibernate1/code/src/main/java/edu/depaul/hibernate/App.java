package edu.depaul.hibernate;

import edu.depaul.hibernate.domain.Message;

import org.hibernate.tutorial.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
		App app = new App();
		app.insertMessages();
		app.displayMessages();
		app.deleteMessages();
		app.insertMessageChain();
		app.displayMessageChain();
		app.deleteMessages();
		app.displayMessages();
    }

	public void insertMessageChain() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Message chain = createMessage("This is the first message");
		chain.setNextMessage(createMessage("This is the second message"));
		chain.getNextMessage().setNextMessage(createMessage("This is the thrid message"));

		session.save(chain);

		session.getTransaction().commit();
	}

	public void insertMessages() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		session.save(createMessage("Hello, World!"));
		session.save(createMessage("Hello, Michael!"));
		session.save(createMessage("Visit http://www.michaelminella.com"));

		session.getTransaction().commit();
	}

	private Message createMessage(String value) {
		Message message = new Message();
		message.setMessage(value);
		return message;
	}

	@SuppressWarnings("unchecked")
	public void displayMessageChain() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

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

		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public void displayMessages() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

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

		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public void deleteMessages() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		List<Message> messages = session.createQuery("from Message").list();

		for(Message message : messages) {
			session.delete(message);
		}

		session.getTransaction().commit();
	}
}
