package model;

import java.util.Scanner;

public abstract class Person {
	private Name name;
	private String id;
	private String phone;
	private Address address;
	private transient Scanner kb = new Scanner(System.in);
	private static int idCounter = 0;

	public Person(Name name, String phone, Address address) {
		super();
		setId();
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

	public Person(String firstName, char middleInitial, String lastName) {
		super();
		setId();
		this.name = new Name(firstName, middleInitial, lastName);
	}

	public Person(String firstName, String lastName) {
		super();
		setId();
		this.name = new Name(firstName, lastName);
	}

	public Person(Person person) {
		this(person.name, person.phone, person.address);
		id = person.getId();
	}

	public Name getName() {
		return new Name(name);
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	private void setId() {
		id = String.valueOf(++idCounter);
		for (int i = 8; i > String.valueOf(idCounter).length(); i--) {
			id = '0' + id;
		}
	}
	public void setID2(String id){
		//idCounter++;
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Address getAddress() {
		return new Address(address);
	}

	public void setAddress(Address address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return name + "\n" + phone + "\n" + address;
	}

}
