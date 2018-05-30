package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

import javafx.util.Pair;

public class PersonBag implements Serializable {
	private Person[] personArray;
	private int nElems;
	Scanner in = new Scanner(System.in);

	public PersonBag(int maxSize) {
		this.personArray = new Person[maxSize];
		this.nElems = 0;
	}

	public void add(Person person) {
		this.personArray[nElems++] = person;
	}

	public Person findById(String id) {
		for (int i = 0; i < nElems; i++) {
			if (id.equals(personArray[i].getId())) {
				return personArray[i];
			}
		}
		return null;
	}

	public int removeById(String id) {
		int idIndex = -1;
		for (int i = 0; i < nElems; i++) {
			if (id.equals(personArray[i].getId())) {
				idIndex = i;
			}
		}
		if (idIndex == -1) {
			return idIndex;
		} else {
			for (int j = idIndex; j < nElems - 1; j++) {
				personArray[j] = personArray[j + 1];
				idIndex = j;
			}
		}
		nElems--;
		return idIndex;
	}

	public void importDataStudent() {
		File file = new File("student.txt");
		Student student = null;
		Major major;
		try {
			in = new Scanner(file);
			while (in.hasNextLine()) {
				String[] nameTokens = in.nextLine().split(" ");
				if (nameTokens.length == 2) {
					student = new Student(nameTokens[0], nameTokens[1]);
				} else {
					student = new Student(nameTokens[0], nameTokens[1].charAt(0), nameTokens[2]);
				}

				String phone = in.nextLine();
				if (!phone.equals("")) {
					student.setPhone(phone);
				}

				String[] addressTokens = in.nextLine().split(", ");
				if (!addressTokens.equals("")) {
					Address studentAddress = new Address(addressTokens[0], addressTokens[1], addressTokens[2],
							addressTokens[3], addressTokens[4]);
					student.setAddress(studentAddress);
				}

				String majorInput = in.nextLine();
				if (majorInput.equals("")) {
					major = Major.UNDECLARED;
				} else {
					major = Major.valueOf(majorInput);
				}
				student.setMajor(major);
				
				String idInput = in.nextLine();
				student.setID2(idInput);
				
				String[] coursesTook = (in.nextLine().trim()).split(" ");
				if (coursesTook != null) {
					Grade[] gradesTook = new Grade[coursesTook.length / 2];
					for (int i = 0; i < coursesTook.length - 1; i += 2) {
						Grade grade1 = new Grade(coursesTook[i], coursesTook[i + 1]);
						gradesTook[i / 2] = grade1;
					}
					student.setCoursesTook(gradesTook);
				}
				String[] coursesTaking = (in.nextLine().trim()).split(" ");
				if (coursesTaking != null) {
					Grade[] gradesTaking = new Grade[coursesTaking.length];
					for (int i = 0; i < coursesTaking.length; i++) {
						Grade grade2 = new Grade(coursesTaking[i]);
						gradesTaking[i] = grade2;
					}
					student.setCoursesTaking(gradesTaking);
				}
				String[] coursesToTake = (in.nextLine().trim()).split(" ");
				if (coursesToTake != null) {
					Grade[] gradesToTake = new Grade[coursesToTake.length];
					for (int i = 0; i < coursesToTake.length; i++) {
						Grade grade3 = new Grade(coursesToTake[i]);
						gradesToTake[i] = grade3;
					}
					student.setCoursesToTake(gradesToTake);
				}
				add(student);
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void exportDataStudent() {
		try {
			PrintWriter pw = new PrintWriter("student.txt");
			for (int i = 0; i < nElems; i++) {
				if (personArray[i] instanceof Student) {
					Student s = (Student) personArray[i];
					pw.println(s.getName());
					pw.println(s.getPhone());
					pw.println(s.getAddress());
					pw.println(s.getMajor());
					
					pw.println(s.getId());
					
					Grade[] coursesTookGrades = s.getCoursesTook();
					if (coursesTookGrades != null) {
						for (int j = 0; j < s.getCoursesTook().length; j++) {
							pw.print(coursesTookGrades[j]);
						}
						//System.out.println(coursesTookGrades.length);
						pw.println();
					}
					Grade[] coursesTakingGrades = s.getCoursesTaking();
					if (coursesTakingGrades != null) {
						for (int j = 0; j < s.getCoursesTaking().length; j++) {
							pw.print(coursesTakingGrades[j].toString2());
						}
					}
					pw.println();
					Grade[] coursesToTakeGrades = s.getCoursesToTake();
					if (coursesToTakeGrades != null) {
						for (int j = 0; j < s.getCoursesToTake().length; j++) {
							pw.print(coursesToTakeGrades[j].toString2());
						}
					}
					pw.println();
				}
			}
			pw.close();
		} catch (IOException e) {
			System.out.println("Cant write file");
		}
	}

	public void importDataFaculty() {
		File file = new File("faculty.txt");
		Faculty faculty = null;
		double salary = 0.0;
		try {
			in = new Scanner(file);
			while (in.hasNextLine()) {
				String[] nameTokens = in.nextLine().split(" ");
				if (nameTokens.length == 2) {
					faculty = new Faculty(nameTokens[0], nameTokens[1]);
				} else if(nameTokens.length == 3){
					faculty = new Faculty(nameTokens[0], nameTokens[1].charAt(0), nameTokens[2]);
				} else {
				}
				String phone = in.nextLine();
				faculty.setPhone(phone);

				String[] addressTokens = in.nextLine().split(", ");
				Address facultyAddress = new Address(addressTokens[0], addressTokens[1], addressTokens[2],
						addressTokens[3], addressTokens[4]);
				faculty.setAddress(facultyAddress);

				String officeAddress = in.nextLine();
				faculty.setOfficeAddress(officeAddress);
				
				String salaryToken = in.nextLine();
				salary = Double.parseDouble(salaryToken);
				faculty.setSalary(salary);

				String title = in.nextLine();
				faculty.setTitle(title);
				
				String idInput = in.nextLine();
				faculty.setID2(idInput);

				add(faculty);
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void exportDataFaculty() {
		try {
			PrintWriter pw = new PrintWriter("faculty.txt");
			for (int i = 0; i < nElems; i++) {
				if (personArray[i] instanceof Faculty) {
					Faculty f = (Faculty) personArray[i];
					pw.println(f.getName());
					pw.println(f.getPhone());
					pw.println(f.getAddress());
					pw.println(f.getOfficeAddress());
					pw.println(f.getSalary());
					pw.println(f.getTitle());
					pw.println(f.getId());
				}
			}
			pw.close();
		} catch (IOException e) {
			System.out.println("Cant write file");
		}
	}

	public void save() {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("PersonBag.dat", false);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(personArray);
			oos.writeInt(nElems);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failure saving PersonBag.dat file");
		}
	}

	public void load() {
		FileInputStream fis;
		try {
			fis = new FileInputStream("PersonBag.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			personArray = (Person[]) (ois.readObject());
			nElems = ois.readInt();
			ois.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			System.out.println("Failure loading PersonBag.dat file");
		} catch (ClassNotFoundException e) {
		}
	}

	public Person[] getPersonArray() {
		return Arrays.copyOf(personArray, nElems);
	}
	
	@Override
	public String toString() {
		return "PersonBag [personArray=" + Arrays.toString(personArray) + ", nElems=" + nElems + "]";
	}
	
	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(personArray[i]);
		}
		System.out.println();
	}
}
