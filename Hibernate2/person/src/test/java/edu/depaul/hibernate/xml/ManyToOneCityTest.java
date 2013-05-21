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

import edu.depaul.hibernate.domain.xml.City;
import edu.depaul.hibernate.domain.xml.State;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ManyToOneCityTest {

	@Autowired
	private SessionFactory sessionFactory;

	private String [] stateNames = {"Illinois", "Wisconsin", "Iowa", "Indiana", "Michigan"};
	private String [] cityNames = {"Chicago", "Milwaukee", "Iowa City", "South Bend", "Detroit"};
	private String [] mayors = {"Richard M. Daley", "Rahm Emanuel", "Eugene Sawyer", "Harold Washington", "Jane Margaret Byrne"};
	private Set<State> states = new HashSet<State>();
	private Random random = new Random();

	@DirtiesContext
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void testSimplePerson() {
		Session session = sessionFactory.getCurrentSession();

		buildStates(session);

		for (State curState : states) {
			session.save(buildCity(curState));
		}

		List<City> cities = session.createQuery("from City").list();

		System.err.println("********** C I T I E S ************");
		for (City city : cities) {
			System.err.println(city);
		}

		List<State> savedStates = session.createQuery("from State").list();

		System.err.println("********** STATES ************");
		for (State state : savedStates) {
			System.err.println(state);
		}
	}

	private void buildStates(Session session) {

		for (String stateName : stateNames) {
			State state = new State();

			state.setName(stateName);
			state.setPopulation(Math.abs(random.nextInt()));

			session.save(state);
			states.add(state);
		}
	}

	private City buildCity(State state) {
		City city = new City();

		city.setName(cityNames[random.nextInt(cityNames.length - 1)]);
		city.setState(state);
		city.setMayor(mayors[random.nextInt(mayors.length - 1)]);

		return city;
	}
}
