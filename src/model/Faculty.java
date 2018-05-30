package model;

public class Faculty extends Person {
	private String officeAddress;
	private double salary;
	private String title;

	public Faculty(Name name, String phone, Address address) {
		super(name, phone, address);
	}
	public Faculty(String firstName, String lastName){
		super(firstName, lastName);
		
	}
	
	public Faculty(String firstName, char middleInitial, String lastName){
		super(firstName, middleInitial, lastName);
		
	}
	
	public Faculty(Faculty faculty) {
		this(faculty.getName(), faculty.getPhone(), faculty.getAddress());
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return super.toString()+ "\n" + officeAddress + "\n" + salary + "\n"
				+ title;
	}

}
