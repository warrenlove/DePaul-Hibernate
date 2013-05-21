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

import edu.depaul.hibernate.domain.xml.CollectionPerson;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class CollectionPersonTest {

	@Autowired
	private SessionFactory sessionFactory;

	private String[] firstNames = {"Tom", "Richard", "Harry"};
	private String[] lastNames = {"Smith", "Johnson", "Williams"};
	private String[] nickNames = {"Boss", "Chief", "Butch"};
	private String initials = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private DecimalFormat formatter = new DecimalFormat("###-##-####");

	@DirtiesContext
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testSimplePerson() {
		Session session = sessionFactory.getCurrentSession();

		session.save(buildPerson());
		session.save(buildPerson());
		session.save(buildPerson());

		List<CollectionPerson> people = session.createQuery("from CollectionPerson").list();

		System.err.println("********** P E O P L E ************");
		for (CollectionPerson person : people) {
			System.err.println(person);
		}
	}

	private CollectionPerson buildPerson() {
		CollectionPerson result = new CollectionPerson();

		Random randomGenerator = new Random();

		result.setBirthdate(new Date(randomGenerator.nextInt()));
		result.setFirstName(firstNames[randomGenerator.nextInt(2)]);
		result.setLastName(lastNames[randomGenerator.nextInt(2)]);
		result.setMiddleInitial(String.valueOf(initials.charAt(randomGenerator.nextInt(initials.length()))));
		result.setSsn(formatter.format(randomGenerator.nextInt(999999999)));

		result.addNickname(nickNames[randomGenerator.nextInt(2)]);

		String name2 = nickNames[randomGenerator.nextInt(2)];

		// Hack around the chance of inserting the same nickname twice
		if(result.getNicknames().contains(name2)) {
			result.addNickname("Big " + name2);
		} else {
			result.addNickname(name2);
		}

		return result;
	}
}
