package edu.depaul.hibernate.xml;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.depaul.hibernate.domain.xml.Person;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class SimplePersonTest {

	@Autowired
	private SessionFactory sessionFactory;

	private String[] firstNames = {"Tom", "Richard", "Harry"};
	private String[] lastNames = {"Smith", "Johnson", "Williams"};
	private String initials = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	@DirtiesContext
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testSimplePerson() {
		Session session = sessionFactory.getCurrentSession();

		session.save(buildPerson());
		session.save(buildPerson());
		session.save(buildPerson());

		List<Person> people = session.createQuery("from Person").list();

		System.err.println("********** P E O P L E ************");
		for (Person person : people) {
			System.err.println(person);
		}
	}

	private Person buildPerson() {
		Person result = new Person();

		Random randomGenerator = new Random();

		result.setBirthdate(new Date(randomGenerator.nextInt()));
		result.setFirstName(firstNames[randomGenerator.nextInt(2)]);
		result.setLastName(lastNames[randomGenerator.nextInt(2)]);
		result.setMiddleInitial(String.valueOf(initials.charAt(randomGenerator.nextInt(initials.length()))));
		result.setSsn(new StringBuilder().append(randomGenerator.nextInt(999999999)).insert(3, "-").insert(6, "-").toString());
		result.setAddress1("123 4th Street");
		result.setCity("Chicago");
		result.setState("IL");
		result.setZip("60606");

		return result;
	}
}
