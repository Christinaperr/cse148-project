package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Address;
import model.College;
import model.Major;
import model.Name;
import model.Person;
import model.PhoneNumberException;
import model.Student;

public class EditStudentPane {
	private GridPane gridPane;
	private BorderPane studentPane;
	private Name studentName;
	private Student student1;
	private String item = "";

	public EditStudentPane(College college) {
		studentPane = new BorderPane();
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(30));
		gridPane.setHgap(20);
		gridPane.setVgap(30);

		Text text1 = new Text();
		text1.setText("Edit a Student");
		text1.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		text1.setFill(Color.LIGHTSLATEGREY);

		Text text2 = new Text();
		text2.setText("Enter Student ID: ");
		text2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		text2.setFill(Color.LIGHTSLATEGREY);

		TextField iDField = new TextField();
		iDField.setPromptText("Student ID");
		iDField.setPrefWidth(400);

		Button okBtn = new Button("OK");

		TextArea displayArea = new TextArea();

		gridPane.add(text2, 2, 2, 2, 1);
		gridPane.add(iDField, 2, 4, 2, 1);

		studentPane.setTop(text1);
		studentPane.setAlignment(text1, Pos.CENTER);
		studentPane.setCenter(gridPane);
		studentPane.setBottom(okBtn);
		studentPane.setAlignment(okBtn, Pos.CENTER_RIGHT);

		okBtn.setOnAction(event2 -> {
			String studentID = iDField.getText();
			Person studentToSearch = college.getPersonBag().findById(studentID);
			if (studentToSearch != null && studentToSearch instanceof Student) {
				student1 = (Student) studentToSearch;
				Alert confirmation = new Alert(AlertType.CONFIRMATION,
						"Student " + studentToSearch.getName() + " Found!", ButtonType.OK);
				confirmation.showAndWait();

				TextField firstNameField = new TextField(studentToSearch.getName().getFirstName());

				TextField middleInitialField = new TextField(
						String.valueOf(studentToSearch.getName().getMiddleInitial()));
				middleInitialField.setPromptText("Middle Initial");

				TextField lastNameField = new TextField(studentToSearch.getName().getLastName());

				TextField phoneField = new TextField(studentToSearch.getPhone());

				TextField streetNumField = new TextField(studentToSearch.getAddress().getStreetNumber());

				TextField streetNameField = new TextField(studentToSearch.getAddress().getStreetName());

				TextField cityField = new TextField(studentToSearch.getAddress().getCity());

				TextField stateField = new TextField(studentToSearch.getAddress().getState());
				stateField.setPromptText("State");

				TextField zipField = new TextField(studentToSearch.getAddress().getZip());
				zipField.setPromptText("Zip Code");

				Label majorLabel = new Label("Major:");
				majorLabel.setScaleX(2);
				majorLabel.setScaleY(2);

				ObservableList<String> list = FXCollections.observableArrayList("Undeclared", "Biology",
						"Computer Science", "Education", "History", "Mathematics", "Psychology");
				ComboBox<String> box = new ComboBox<>();
				box.setItems(list);
				box.setValue(String.valueOf(student1.getMajor()));
				box.setOnAction(e -> {
					item = box.getValue();
				});

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
				gridPane.add(box, 0, 6, 2, 1);

				studentPane.setTop(text1);
				studentPane.setAlignment(text1, Pos.CENTER);
				studentPane.setCenter(gridPane);
				studentPane.setBottom(okBtn);
				studentPane.setAlignment(okBtn, Pos.CENTER_RIGHT);

				okBtn.setOnAction(event3 -> {
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
							Alert alert = new Alert(AlertType.ERROR,
									"Phone Number is wrong format! Please enter again!", ButtonType.OK);
							alert.showAndWait();
							phoneField.requestFocus();
						}
					} else {
						phone = phone.replaceAll("[(]?(\\d{3})[-.)]?(\\d{3})[-.]?(\\d{4})", "($1)$2-$3");
						Alert confirmation2 = new Alert(AlertType.CONFIRMATION, "Student Edited!", ButtonType.OK);
						confirmation2.showAndWait();
						if (confirmation2.getResult() == ButtonType.OK) {

							student1.setName(studentName);
							student1.setPhone(phone);
							student1.setAddress(address);

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
							} else if (item.equals("Undeclared")) {
								student1.setMajor(Major.UNDECLARED);
							} else {
							}

							displayArea.appendText(student1.toString());
						}

					}

				});

			}

			else {
				Alert alert = new Alert(AlertType.CONFIRMATION, "Student Does Not Exist!", ButtonType.OK);
				alert.showAndWait();
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
