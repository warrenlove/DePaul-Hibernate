package edu.depaul.hibernate.xml;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import edu.depaul.hibernate.domain.xml.Item;
import edu.depaul.hibernate.domain.xml.Order;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ManyToManyOrderTest {

	@Autowired
	private SessionFactory sessionFactory;
	private List<Order> orders = new ArrayList<Order>();
	private List<Item> items = new ArrayList<Item>();
	private String [] itemNames = {"Macbook Pro", "DePaul T-Shirt", "iPhone", "iPad", "iPod", "backpack"};
	private String [] custName = {"Richard M. Daley", "Rahm Emanuel", "Eugene Sawyer", "Harold Washington", "Jane Margaret Byrne"};
	private Random random = new Random();

	@DirtiesContext
	@Test
	@Transactional
	@SuppressWarnings("unchecked")
	public void test() {
		Session session = sessionFactory.getCurrentSession();

		createItems();
		createOrders();
		associate();

		for (Order order : orders) {
			session.save(order);
		}

		List<Order> savedOrders = session.createQuery("from Order").list();

		System.out.println("*********** ORDERS **************");
		for (Order order : savedOrders) {
			System.out.println(String.format("Order for %s has the following items in it:", order.getCustomerName()));
			for (Item curItem : order.getItems()) {
				System.out.println(String.format("\t%d of %s at %,f", curItem.getNumber(), curItem.getName(), curItem.getPrice().doubleValue()));
			}
		}

		List<Item> savedItems = session.createQuery("from Item").list();

		System.out.println("*********** ITEMS **************");
		for (Item curItem : savedItems) {
			System.out.println(String.format("%s is going to the following:", curItem.getName()));
			for (Order curOrder : curItem.getOrders()) {
				System.out.println(String.format("\t%s", curOrder.getCustomerName()));
			}
		}
	}

	private void associate() {
		for (Order order : orders) {
			int qty = random.nextInt(items.size() - 1);

			if(qty <= 0) {
				qty = 1;
			}

			for (int i = 0; i < qty; i++) {
				Item selectedItem = items.get(random.nextInt(items.size() - 1));
				order.getItems().add(selectedItem);
				selectedItem.getOrders().add(order);
			}
		}
	}

	private void createOrders() {
		for (int i = 0; i < 3; i++) {
			Order order = new Order();

			order.setCustomerName(custName[random.nextInt(custName.length - 1)]);

			orders.add(order);
		}
	}

	private void createItems() {
		for (String curName : itemNames) {
			Item item = new Item();

			item.setName(curName);
			item.setNumber(Math.abs(random.nextInt()));
			item.setPrice(new BigDecimal(100 * random.nextDouble()));

			items.add(item);
		}
	}
}
