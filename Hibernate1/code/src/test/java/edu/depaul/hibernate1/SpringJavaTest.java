package edu.depaul.hibernate1;

import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import edu.depaul.hibernate.domain.Message;
import edu.depaul.hibernate1.SpringJavaTest.JavaConfig;

@ContextConfiguration(classes=JavaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringJavaTest {

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

	@Configuration
	@EnableTransactionManagement
	public static class JavaConfig {
		@Bean
		public PlatformTransactionManager transactionManager() {
			return new HibernateTransactionManager(sessionFactory());
		}

		@Bean
		public DataSource dataSource() {
			EmbeddedDatabaseFactoryBean bean = new EmbeddedDatabaseFactoryBean();
			bean.afterPropertiesSet(); // necessary because EmbeddedDatabaseFactoryBean is a FactoryBean
			return bean.getObject();
		}

		@Bean
		@SuppressWarnings("deprecation")
		public SessionFactory sessionFactory() {
			Properties properties = new Properties();
			properties.put("hibernate.hbm2ddl.auto", "update");
			properties.put("hibernate.show_sql", true);

			return new LocalSessionFactoryBuilder(dataSource())
			.addAnnotatedClass(Message.class)
			.addProperties(properties)
			// This is deprecated but necessary in Spring
			.buildSessionFactory();
		}
	}
}
