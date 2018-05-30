package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class CourseBag implements Serializable{
	private Course[] courseArray;
	private int nElems;
	Scanner in = new Scanner(System.in);

	public CourseBag(int size) {
		this.courseArray = new Course[size];
		this.nElems = 0;
	}
	public void add(Course course) {
		courseArray[nElems++] = course;
	}

	public Course findBycourseNumber(String courseNumber) {
		for (int i = 0; i < nElems; i++) {
			if (courseNumber.equals(courseArray[i].getCourseNumber())) {
				return courseArray[i];
			}
		}
		return null;
	}

	public int removeBycourseNumber(String courseNumber) {
		int courseIndex = -1;
		for (int i = 0; i < nElems; i++) {
			if (courseNumber.equals(courseArray[i].getCourseNumber())) {
				courseIndex = i;
			}
		}
		if (courseIndex == -1) {
			return courseIndex;
		} else {
			for (int j = courseIndex; j < nElems-1; j++) {
				courseArray[j] = courseArray[j + 1];
				courseIndex = j;
			}
		}
		nElems--;
		return courseIndex;
	}
	public Course[] getCourseArray(){
		return Arrays.copyOf(courseArray, nElems);
	}

	public void importData(){
		File file = new File("course.txt");
		try {
			in = new Scanner(file);
			while (in.hasNextLine()) {
				String courseName = in.nextLine();
				String courseNumber = in.nextLine();
				String creditsText = in.nextLine();
				int credits = Integer.parseInt(creditsText);
				String facultyId = in.nextLine();
				String textbookISBN = in.nextLine();
				
				Course course = new Course(courseName, courseNumber, credits);
				if (!facultyId.equals("")) {
					course.setFacultyID(facultyId);
				}
				if (!textbookISBN.equals("")) {
					course.setTextbookISBN(textbookISBN);
				}
				add(course);
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} 
	}
	public void exportData(){
		try {
			PrintWriter pw = new PrintWriter("course.txt");
			for (int i = 0; i < nElems; i++) {
				Course c = courseArray[i];
				pw.println(c.getCourseTitle());
				pw.println(c.getCourseNumber());
				pw.println(c.getNumberOfCredits());
				pw.println(c.getFacultyID());
				pw.println(c.getTextbookISBN());
			}
			pw.close();
		} catch (IOException e) {
			System.out.println("Cant write file");
		}
	}


	public void save(){
		FileOutputStream fos;
		try{
			fos = new FileOutputStream("CourseBag.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(courseArray);
			oos.writeObject(nElems);
			oos.close();
		} catch (IOException e){
			System.out.println("Failed to Save courseBag File");
		}

	}

	public void load(){
		FileInputStream fis;
		try{
			fis = new FileInputStream("CourseBag.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			courseArray = (Course[])(ois.readObject());
			nElems = (int)(ois.readObject());
			ois.close();
		} catch(FileNotFoundException e){
		} catch(IOException e){
			System.out.println("Failed to Load courseBag File");
		} catch (ClassNotFoundException e){
		}
	}
	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(courseArray[i]);
		}
		System.out.println();
	}
}
