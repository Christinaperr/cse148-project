package model;

import java.util.Arrays;

public class Student extends Person {
	private Major major;
	private double gpa;
	private Grade[] coursesTook;
	private Grade[] coursesTaking;
	private Grade[] coursesToTake;

	public Student(Name name, String phone, Address address) {
		super(name, phone, address);
		
	}
	public Student(String firstName, String lastName) {
		super(firstName, lastName);
		
	}
	public Student(String firstName, char middleInitial, String lastName) {
		super(firstName, middleInitial, lastName);
		
	}
	public Student(Student student) {
		this(student.getName(), student.getPhone(), student.getAddress());
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public Grade[] getCoursesTook() {
		return coursesTook;
	}

	public void setCoursesTook(Grade[] coursesTook) {
		this.coursesTook = coursesTook;
	}

	public Grade[] getCoursesTaking() {
		return coursesTaking;
	}

	public void setCoursesTaking(Grade[] coursesTaking) {
		this.coursesTaking = coursesTaking;
	}

	public Grade[] getCoursesToTake() {
		return coursesToTake;
	}

	public void setCoursesToTake(Grade[] coursesToTake) {
		this.coursesToTake = coursesToTake;
	}
	
	public int getCredits(Grade[] array, CourseBag courseBag) {
		int creditAmount = 0;
		for(int i = 0; i < array.length; i++){
			Course course = courseBag.findBycourseNumber(array[i].getCourseNumber());
			if(course == null){
				System.out.println("Course Not Found");
				break;
			}
			else{
				creditAmount += course.getNumberOfCredits();
			}
		}
		return creditAmount;
			}
	private double getGradePoints(CourseBag courseBag){
		double gradePoints =0.0;
		Course[] coursesArray = courseBag.getCourseArray();
		for(int i = 0; i < coursesTook.length; i++){
			for(int j = 0; j < courseBag.getCourseArray().length; j++){
				if(coursesTook[i].getCourseNumber().equals(coursesArray[j].getCourseNumber())){
					gradePoints += convertLetterGrade(coursesTook[i].getCourseLetterGrade()) * coursesArray[j].getNumberOfCredits();
				}
			}
		}
		return gradePoints;
	}
	private double convertLetterGrade(String letterGrade){
		if(letterGrade.equals("A")){
			return 4.0;
		} else if(letterGrade.equals("B+")){
			return 3.5;
		} else if(letterGrade.equals("B")){
			return 3.0;
		} else if(letterGrade.equals("C+")){
			return 2.5;
		} else if(letterGrade.equals("C")){
			return 2.0;
		} else if(letterGrade.equals("D+")){
			return 1.5;
		} else if(letterGrade.equals("D")){
			return 1.0;
		} else{
			return 0.0;
		}

	}
	
	private double calculateGpa(Grade[] array, CourseBag courseBag){
		int creditsTook = getCredits(array, courseBag);
		double gradePoint = getGradePoints(courseBag);
		return gradePoint/creditsTook;
	}
	
	private void setGpa(Grade[] array, CourseBag courseBag){
		this.gpa = calculateGpa(array, courseBag);

	}
	/*private int getCreditsTook(CourseBag courseBag){
		int creditsTook = 0;
		Course[] coursesArray = courseBag.getCourseArray();
		for(int i = 0; i < coursesTook.length; i++){
			for(int j = 0; j < courseBag.getCourseArray().length;j++){
				if(coursesTook[i].getCourseNumber().equals(coursesArray[j])){
					creditsTook += coursesArray[j].getNumberOfCredits();
					break;
				}
			}
		}
		return creditsTook;
	}*/

	/*public double getGPA(Grade[] coursesTook){
		double average = 0.0;
		double numberGrade = 0.0;
		int totalCredits = 0;
		int creditAmount = 0;
		for(int i = 0; i < coursesTook.length; i++){
			String letterGrade = coursesTook[i].getCourseLetterGrade();
			switch(letterGrade){
			case "A": numberGrade = 4.0; break;
			case "A-": numberGrade = 3.75; break;
			case "B+": numberGrade = 3.25; break;
			case "B": numberGrade = 3.0; break;
			case "B-": numberGrade = 2.75; break;
			case "C+": numberGrade = 2.25; break;
			case "C": numberGrade = 2.0; break;
			case "C-": numberGrade = 1.75; break;
			case "D+": numberGrade = 1.25; break;
			case "D": numberGrade = 1.0; break;
			case "D-": numberGrade = .75; break;
			case "F": numberGrade = 0; break;
			}
			creditAmount = getCredits(coursesTook, courseBag);
			totalCredits +=creditAmount;
			average += numberGrade*creditAmount;
		}
		average /= totalCredits;
		return Math.round(average*10.0)/10.0;
	}*/

	@Override
	public String toString() {
		return super.toString() + "\n" + major + "\n"
				+ Arrays.toString(coursesTook) + "\n" + Arrays.toString(coursesTaking)
				+ "\n" + Arrays.toString(coursesToTake);
	}
	public String displayMajor(){
		return major.toString();
	}

}
