package model;

public class Name {
	private String firstName;
	private String lastName;
	private char middleInitial = ' ';

	public Name(String firstName, char middleInitial, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleInitial = middleInitial;
	}

	public Name(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Name(Name name) {
		this(name.firstName, name.middleInitial, name.lastName);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public char getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(char middleInitial) {
		this.middleInitial = middleInitial;
	}

	@Override
	public String toString() {
		if(middleInitial == ' '){
		return firstName + " " + lastName;
		} else {
			return firstName + " " + middleInitial + " " + lastName;
		}
	}

}
