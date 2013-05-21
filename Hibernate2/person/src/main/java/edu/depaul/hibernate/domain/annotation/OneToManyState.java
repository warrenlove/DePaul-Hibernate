package edu.depaul.hibernate.domain.annotation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class OneToManyState {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private long population;

	@OneToMany(mappedBy="state")
	private Set<OneToManyCity> cities = new HashSet<OneToManyCity>();

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPopulation() {
		return population;
	}
	public void setPopulation(long population) {
		this.population = population;
	}
	public Set<OneToManyCity> getCities() {
		return cities;
	}
	public void setCities(Set<OneToManyCity> cities) {
		this.cities = cities;
	}
	@Override
	public String toString() {
		Iterator<OneToManyCity> curCities = cities.iterator();
		String message = String.format("%s has a population of %s with the following cities:\n", name, population);

		while(curCities.hasNext()) {
			message = message + curCities.next().getName() + "\n";
		}

		return message;
	}
}
