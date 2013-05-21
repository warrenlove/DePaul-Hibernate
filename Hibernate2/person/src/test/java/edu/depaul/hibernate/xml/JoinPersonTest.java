package edu.depaul.hibernate.xml;

import java.text.DecimalFormat;
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

import edu.depaul.hibernate.domain.xml.JoinPerson;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class JoinPersonTest {

	@Autowired
	private SessionFactory sessionFactory;

	private String[] firstNames = {"Tom", "Richard", "Harry"};
	private String[] lastNames = {"Smith", "Johnson", "Williams"};
	private String initials = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private DecimalFormat formatter = new DecimalFormat("###-##-####");

	@DirtiesContext
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void test() {
		Session session = sessionFactory.getCurrentSession();

		session.save(buildPerson());
		session.save(buildPerson());
		session.save(buildPerson());

		List<JoinPerson> people = session.createQuery("from JoinPerson").list();

		System.err.println("********** P E O P L E ************");
		for (JoinPerson person : people) {
			System.err.println(person);
		}
	}

	private JoinPerson buildPerson() {
		JoinPerson result = new JoinPerson();

		Random randomGenerator = new Random();

		result.setBirthdate(new Date(randomGenerator.nextInt()));
		result.setFirstName(firstNames[randomGenerator.nextInt(2)]);
		result.setLastName(lastNames[randomGenerator.nextInt(2)]);
		result.setMiddleInitial(String.valueOf(initials.charAt(randomGenerator.nextInt(initials.length()))));
		result.setSsn(formatter.format(randomGenerator.nextInt(999999999)));
		result.setAddress1("123 4th Street");
		result.setCity("Chicago");
		result.setState("IL");
		result.setZip("60606");

		return result;
	}
}
