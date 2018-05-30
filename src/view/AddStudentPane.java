package view;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Address;
import model.College;
import model.Course;
import model.Grade;
import model.Major;
import model.Name;
import model.PhoneNumberException;
import model.Student;

public class AddStudentPane {
	private GridPane gridPane;
	private BorderPane studentPane;
	private Name studentName;
	private Student student1;
	private String item = "";
	private Grade[] coursesToTake;
	private Grade[] coursesTaking;
	private Grade[] coursesTaken;
	private Grade[] tempArr1 = new Grade[50];
	private Grade[] tempArr2 = new Grade[50];
	private Grade[] tempArr3 = new Grade[50];
	private int counter = 0;
	private int counter2 = 0;
	private int counter3 = 0;

	public AddStudentPane(College college) {
		studentPane = new BorderPane();
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(30));
		gridPane.setHgap(20);
		gridPane.setVgap(30);

		Text text1 = new Text();
		text1.setText("New Student");
		text1.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		text1.setFill(Color.BLUE);
		

		TextField firstNameField = new TextField();
		firstNameField.setPromptText("First Name");

		TextField middleInitialField = new TextField();
		middleInitialField.setPromptText("Middle Initial");

		TextField lastNameField = new TextField();
		lastNameField.setPromptText("Last Name");

		TextField phoneField = new TextField();
		phoneField.setPromptText("Phone Number");

		TextField streetNumField = new TextField();
		streetNumField.setPromptText("Street Number");

		TextField streetNameField = new TextField();
		streetNameField.setPromptText("Street Name");

		TextField cityField = new TextField();
		cityField.setPromptText("City");

		TextField stateField = new TextField();
		stateField.setPromptText("State");

		TextField zipField = new TextField();
		zipField.setPromptText("Zip Code");

		TextField letterGradeField = new TextField();
		letterGradeField.setPromptText("Letter Grade");

		Label majorLabel = new Label("Major:");
		majorLabel.setScaleX(2);
		majorLabel.setScaleY(2);
		
		Label coursesTookLabel = new Label("Add Courses Took:");
		Label coursesTakingLabel = new Label("Add Courses Taking:");
		Label coursesToToTakeLabel = new Label("Add Courses Need to Take:");

		ObservableList<String> list = FXCollections.observableArrayList("Undeclared", "Biology", "Computer Science",
				"Education", "History", "Mathematics", "Psychology");
		ComboBox<String> box = new ComboBox<>();
		box.setItems(list);
		box.setValue("Undeclared");
		box.setOnAction(e -> {
			item = box.getValue();
		});
		String[] coursesNumbers = new String[college.getCourseBag().getCourseArray().length];
		Course[] courseArr = college.getCourseBag().getCourseArray();
		for (int i = 0; i < courseArr.length; i++) {
			String courseNum1 = courseArr[i].getCourseNumber();
			coursesNumbers[i] = courseNum1;
		}

		ObservableList<String> courses = FXCollections.observableList(Arrays.asList(coursesNumbers));
		ListView courseList = new ListView(courses);
		ListView courseTakingList = new ListView(courses);
		ListView courseToTakeList = new ListView(courses);

		Button add = new Button("Add");
		Button add2 = new Button("Add");
		Button add3 = new Button("Add");

		add.setOnAction(e -> {
			Grade courseTook1 = new Grade((String) (courseList.getSelectionModel().getSelectedItem()),
					letterGradeField.getText());
			tempArr1[counter++] = courseTook1;
			letterGradeField.clear();
		});
		
		add2.setOnAction(e -> {
			Grade courseTaking1 = new Grade((String) (courseTakingList.getSelectionModel().getSelectedItem()));
			tempArr2[counter2++] = courseTaking1;
		});
		
		add3.setOnAction(e -> {
			Grade courseToTake1 = new Grade((String) (courseToTakeList.getSelectionModel().getSelectedItem()));
			tempArr3[counter3++] = courseToTake1;
		});
		
		
		
		Button okBtn = new Button("OK");

		TextArea displayArea = new TextArea();

		gridPane.add(firstNameField, 0, 0, 2, 1);
		gridPane.add(middleInitialField, 2, 0, 2, 1);
		gridPane.add(lastNameField, 4, 0, 2, 1);
		gridPane.add(phoneField, 0, 2, 2, 1);
		gridPane.add(streetNumField, 2, 2, 2, 1);
		gridPane.add(streetNameField, 4, 2, 2, 1);
		gridPane.add(cityField, 0, 4, 2, 1);
		gridPane.add(stateField, 2, 4, 2, 1);
		gridPane.add(zipField, 4, 4, 2, 1);
		gridPane.add(majorLabel, 1, 5, 2, 1);
		gridPane.add(coursesTookLabel, 3, 5, 2, 1);
		gridPane.add(box, 0, 6, 2, 1);
		gridPane.add(courseList, 2, 6, 2, 1);
		gridPane.add(add, 4, 6, 2, 1);
		gridPane.add(letterGradeField, 2, 7, 2, 1);
		gridPane.add(courseTakingList, 0, 9, 2, 1);
		gridPane.add(coursesTakingLabel, 1, 8, 2, 1);
		gridPane.add(add2, 2, 9, 2, 1);
		gridPane.add(courseToTakeList, 4, 9, 2, 1);
		gridPane.add(add3, 6, 9, 2, 1);
		gridPane.add(coursesToToTakeLabel, 4, 8, 2, 1);

		studentPane.setTop(text1);
		studentPane.setAlignment(text1, Pos.CENTER);
		studentPane.setCenter(gridPane);
		studentPane.setBottom(okBtn);
		studentPane.setAlignment(okBtn, Pos.CENTER_RIGHT);

		okBtn.setOnAction(event2 -> {
			String firstName = firstNameField.getText();
			String middleInitial = middleInitialField.getText();
			String lastName = lastNameField.getText();
			String phone = phoneField.getText();
			String streetNum = streetNumField.getText();
			String streetName = streetNameField.getText();
			String city = cityField.getText();
			String state = stateField.getText();
			String zip = zipField.getText();

			Address address = new Address(streetNum, streetName, city, state, zip);

			if (middleInitial.equals("")) {
				studentName = new Name(firstName, lastName);
			} else {
				studentName = new Name(firstName, middleInitial.charAt(0), lastName);
			}
			if (!phone.matches("[(]?(\\d{3})[-.)]?(\\d{3})[-.]?(\\d{4})")) {
				try {
					throw new PhoneNumberException("Wrong phone number format. Enter again:");
				} catch (PhoneNumberException e) {
					Alert alert = new Alert(AlertType.ERROR, "Phone Number is wrong format! Please enter again!",
							ButtonType.OK);
					alert.showAndWait();
					phoneField.requestFocus();
				}
			} else {
				phone = phone.replaceAll("[(]?(\\d{3})[-.)]?(\\d{3})[-.]?(\\d{4})", "($1)$2-$3");
				Alert confirmation = new Alert(AlertType.CONFIRMATION, "Student Added!", ButtonType.OK);
				confirmation.showAndWait();
				if (confirmation.getResult() == ButtonType.OK) {
					student1 = new Student(studentName, phone, address);
					
					coursesTaken = new Grade[counter];
					coursesTaken = Arrays.copyOf(tempArr1, counter);
					student1.setCoursesTook(coursesTaken);
					
					coursesTaking = new Grade[counter2];
					coursesTaking = Arrays.copyOf(tempArr2, counter2);
					student1.setCoursesTaking(coursesTaking);
					
					coursesToTake = new Grade[counter3];
					coursesToTake = Arrays.copyOf(tempArr3, counter3);
					student1.setCoursesToTake(coursesToTake);

					firstNameField.clear();
					middleInitialField.clear();
					lastNameField.clear();
					phoneField.clear();
					streetNumField.clear();
					streetNameField.clear();
					cityField.clear();
					stateField.clear();
					zipField.clear();

					if (item.equals("Biology")) {
						student1.setMajor(Major.BIOLOGY);
					} else if (item.equals("Computer Science")) {
						student1.setMajor(Major.COMPUTER_SCIENCE);
					} else if (item.equals("Education")) {
						student1.setMajor(Major.EDUCATION);
					} else if (item.equals("History")) {
						student1.setMajor(Major.HISTORY);
					} else if (item.equals("Mathematics")) {
						student1.setMajor(Major.MATHEMATICS);
					} else if (item.equals("Psychology")) {
						student1.setMajor(Major.PSYCHOLOGY);
					} else {
						student1.setMajor(Major.UNDECLARED);
					}

					college.getPersonBag().add(student1);
					displayArea.appendText(student1.toString());
				}

			}

		});
	}

	public GridPane getGridPane() {
		return gridPane;
	}

	public BorderPane getBorderPane() {
		return studentPane;
	}
}
