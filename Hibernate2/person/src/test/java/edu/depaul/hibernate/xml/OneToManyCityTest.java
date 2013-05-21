package edu.depaul.hibernate.xml;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.depaul.hibernate.domain.xml.OneToManyCity;
import edu.depaul.hibernate.domain.xml.OneToManyState;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class OneToManyCityTest {

	@Autowired
	private SessionFactory sessionFactory;

	private String [] stateNames = {"Illinois", "Wisconsin", "Iowa", "Indiana", "Michigan"};
	private String [] cityNames = {"Chicago", "Milwaukee", "Iowa City", "South Bend", "Detroit"};
	private String [] mayors = {"Richard M. Daley", "Rahm Emanuel", "Eugene Sawyer", "Harold Washington", "Jane Margaret Byrne"};
	private Set<OneToManyState> states = new HashSet<OneToManyState>();
	private Random random = new Random();

	@DirtiesContext
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testSimplePerson() {
		Session session = sessionFactory.getCurrentSession();

		buildStates(session);

		for (OneToManyState curState : states) {
			session.save(buildCity(curState));
			session.save(buildCity(curState));
		}

		for (OneToManyState curState : states) {
			session.saveOrUpdate(curState);
		}

		List<OneToManyCity> cities = session.createQuery("from OneToManyCity").list();

		System.err.println("********** C I T I E S ************");
		for (OneToManyCity city : cities) {
			System.err.println(city);
		}

		List<OneToManyState> savedStates = session.createQuery("from OneToManyState").list();

		System.err.println("********** STATES ************");
		for (OneToManyState state : savedStates) {
			System.err.println(state);
		}
	}

	private void buildStates(Session session) {

		for (String stateName : stateNames) {
			OneToManyState state = new OneToManyState();

			state.setName(stateName);
			state.setPopulation(Math.abs(random.nextInt()));

			session.save(state);
			states.add(state);
		}
	}

	private OneToManyCity buildCity(OneToManyState state) {
		OneToManyCity city = new OneToManyCity();

		city.setName(cityNames[random.nextInt(cityNames.length - 1)]);
		city.setState(state);
		state.getCities().add(city);
		city.setMayor(mayors[random.nextInt(mayors.length - 1)]);

		return city;
	}
}
