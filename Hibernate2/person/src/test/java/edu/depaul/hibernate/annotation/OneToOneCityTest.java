package edu.depaul.hibernate.annotation;

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

import edu.depaul.hibernate.domain.annotation.OneToOneCity;
import edu.depaul.hibernate.domain.annotation.OneToOneMayor;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class OneToOneCityTest {

	@Autowired
	private SessionFactory sessionFactory;

	private String [] cityNames = {"Chicago", "Milwaukee", "Iowa City", "South Bend", "Detroit"};
	private String[] firstNames = {"Tom", "Richard", "Harry"};
	private String[] lastNames = {"Smith", "Johnson", "Williams"};
	private String initials = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private Random random = new Random();

	@DirtiesContext
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testSimplePerson() {
		Session session = sessionFactory.getCurrentSession();

		buildCities(session);

		List<OneToOneMayor> mayors = session.createQuery("from OneToOneMayor").list();

		System.err.println("********** M A Y O R S ************");
		for (OneToOneMayor mayor : mayors) {
			System.out.println(mayor);
		}

		List<OneToOneCity> cities = session.createQuery("from OneToOneCity").list();

		System.err.println("********** C I T I E S ************");
		for (OneToOneCity city : cities) {
			System.out.println(city);
		}
	}

	private void buildCities(Session session) {
		for (String cityName : cityNames) {
			OneToOneCity city = new OneToOneCity();

			city.setMayor(buildMayor(city, session));
			city.setName(cityName);
			session.save(city);
		}
	}

	private OneToOneMayor buildMayor(OneToOneCity city, Session session) {
		OneToOneMayor mayor = new OneToOneMayor();

		mayor.setBirthdate(new Date(random.nextInt()));
		mayor.setFirstName(firstNames[random.nextInt(2)]);
		mayor.setLastName(lastNames[random.nextInt(2)]);
		mayor.setMiddleInitial(String.valueOf(initials.charAt(random.nextInt(initials.length()))));
		mayor.setSsn(new StringBuilder().append(random.nextInt(999999999)).insert(3, "-").insert(6, "-").toString());
		mayor.setCity(city);

		return mayor;
	}
}
