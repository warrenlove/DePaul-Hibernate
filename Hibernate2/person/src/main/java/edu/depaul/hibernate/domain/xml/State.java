package edu.depaul.hibernate.domain.xml;

public class State {
	private long id;
	private String name;
	private long population;
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
	@Override
	public String toString() {
		return String.format("%s has a population of %s", name, population);
	}
}
