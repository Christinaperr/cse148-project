package model;

public class Grade {
	private String courseNumber;
	private String courseLetterGrade;

	public Grade(String courseNumber) {
		super();
		this.courseNumber = courseNumber;
	}
	
	public Grade(String courseNumber, String courseLetterGrade) {
		super();
		this.courseNumber = courseNumber;
		this.courseLetterGrade = courseLetterGrade;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getCourseLetterGrade() {
		return courseLetterGrade;
	}

	public void setCourseLetterGrade(String courseLetterGrade) {
		this.courseLetterGrade = courseLetterGrade;
	}

	@Override
	public String toString() {
		return courseNumber + " " +courseLetterGrade + " ";
	}
	public String toString2() {
		return courseNumber + " ";
	}
}
