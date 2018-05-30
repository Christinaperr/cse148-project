package model;

import java.util.Scanner;

public class Address {
	private String streetNumber;
	private String streetName;
	private String city;
	private String state;
	private String zip;
	private Scanner kb = new Scanner(System.in);

	public Address(String streetNumber, String streetName, String city, String state, String zip) {
		super();
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		checkZipCode(zip);
	}

	public Address(Address address) {
		this(address.streetNumber, address.streetName, address.city, address.state, address.zip);
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		checkZipCode(zip);
	}

	private void checkZipCode(String zip) {
		if (!zip.matches("(\\d{5})")) {
			try {
				throw new ZipCodeException("Wrong zip code format. Re-enter: ");
			} catch (ZipCodeException e) {
				System.out.println(e.getMessage());
				setZip(kb.nextLine());
			}
		} else {
			this.zip = zip;
		}
	}

	@Override
	public String toString() {
		return streetNumber + ", " + streetName + ", " + city + ", " + state + ", " + zip;
	}
	public String display(){
		return streetNumber + " " + streetName + "\n" + city + "\n" + state + ", " + zip;
	}

}
