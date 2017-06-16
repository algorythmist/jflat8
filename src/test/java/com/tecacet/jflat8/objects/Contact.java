package com.tecacet.jflat8.objects;

public class Contact {

	public enum State {
		CA, IL, OR, WA
	}

	private final String name;
	private final String state; // TODO enum
	private final String telephone; // TODO object

	public Contact(String name, String state, String telephone) {
		super();
		this.name = name;
		this.state = state;
		this.telephone = telephone;
	}

	public String getName() {
		return name;
	}

	public String getState() {
		return state;
	}

	public String getTelephone() {
		return telephone;
	}

}
