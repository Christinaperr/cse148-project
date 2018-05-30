package model;

public class Course {
	private String courseTitle;
	private String courseNumber;
	private String textbookISBN;
	private int numberOfCredits;
	private String facultyID;

	public Course(String courseTitle, String courseNumber, int numberOfCredits) {
		super();
		this.courseTitle = courseTitle;
		this.courseNumber = courseNumber;
		this.numberOfCredits = numberOfCredits;
	}

	public Course(Course course) {
		this(course.courseTitle, course.courseNumber, course.numberOfCredits);
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getTextbookISBN() {
		return textbookISBN;
	}

	public void setTextbookISBN(String textbookISBN) {
		this.textbookISBN = textbookISBN;
	}

	public int getNumberOfCredits() {
		return numberOfCredits;
	}

	public void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}

	public String getFacultyID() {
		return facultyID;
	}

	public void setFacultyID(String facultyID) {
		this.facultyID = facultyID;
	}

	@Override
	public String toString() {
		return "Course " + courseTitle + " " + courseNumber + "\nTextbook ISBN: "
				+ textbookISBN + "\nCredits: " + numberOfCredits + "\nInstructor: " + facultyID + "\n";
	}
	public String exportToString(){
		return courseNumber;
	}

}
