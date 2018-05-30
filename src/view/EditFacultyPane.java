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
import model.Faculty;
import model.Major;
import model.Name;
import model.Person;
import model.PhoneNumberException;
import model.Student;

public class EditFacultyPane {
	private GridPane gridPane;
	private BorderPane facultyPane;
	private Name facultyName;
	private Faculty faculty1;
	private String item = "";
	
	public EditFacultyPane(College college) {
		facultyPane = new BorderPane();
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(30));
		gridPane.setHgap(20);
		gridPane.setVgap(30);

		Text text1 = new Text();
		text1.setText("Edit Faculty");
		text1.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		text1.setFill(Color.LIGHTSLATEGREY);

		Text text2 = new Text();
		text2.setText("Enter Faculty ID: ");
		text2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		text2.setFill(Color.LIGHTSLATEGREY);

		TextField iDField = new TextField();
		iDField.setPromptText("Student ID");
		iDField.setPrefWidth(400);

		Button okBtn = new Button("OK");


		TextArea displayArea = new TextArea();

		gridPane.add(text2, 2, 2, 2, 1);
		gridPane.add(iDField, 2, 4, 2, 1);

		facultyPane.setTop(text1);
		facultyPane.setAlignment(text1, Pos.CENTER);
		facultyPane.setCenter(gridPane);
		facultyPane.setBottom(okBtn);
		facultyPane.setAlignment(okBtn, Pos.CENTER_RIGHT);

		okBtn.setOnAction(event2 -> {
			String facultyID = iDField.getText();
			Person facultyToSearch = college.getPersonBag().findById(facultyID);
			if (facultyToSearch != null && facultyToSearch instanceof Faculty) {
				faculty1 = (Faculty)facultyToSearch;
				Alert confirmation = new Alert(AlertType.CONFIRMATION,
						"Faculty " + facultyToSearch.getName() + " Found!",
						ButtonType.OK);
				confirmation.showAndWait();

				TextField firstNameField = new TextField(facultyToSearch.getName().getFirstName());

				TextField middleInitialField = new TextField(String.valueOf(facultyToSearch.getName().getMiddleInitial()));
				middleInitialField.setPromptText("Middle Initial");

				TextField lastNameField = new TextField(facultyToSearch.getName().getLastName());

				TextField phoneField = new TextField(facultyToSearch.getPhone());

				TextField streetNumField = new TextField(facultyToSearch.getAddress().getStreetNumber());

				TextField streetNameField = new TextField(facultyToSearch.getAddress().getStreetName());

				TextField cityField = new TextField(facultyToSearch.getAddress().getCity());

				TextField stateField = new TextField(facultyToSearch.getAddress().getState());

				TextField zipField = new TextField(facultyToSearch.getAddress().getZip());
				
				TextField officeAddressField = new TextField(faculty1.getOfficeAddress());
				
				TextField salaryField = new TextField(String.valueOf(faculty1.getSalary()));
				
				TextField titleField = new TextField(faculty1.getTitle());

				
				gridPane.add(firstNameField, 0, 0, 2, 1);
				gridPane.add(middleInitialField, 2, 0, 2, 1);
				gridPane.add(lastNameField, 4, 0, 2, 1);
				gridPane.add(phoneField, 0, 2, 2, 1);
				gridPane.add(streetNumField, 2, 2, 2, 1);
				gridPane.add(streetNameField, 4, 2, 2, 1);
				gridPane.add(cityField, 0, 4, 2, 1);
				gridPane.add(stateField, 2, 4, 2, 1);
				gridPane.add(zipField, 4, 4, 2, 1);
				gridPane.add(officeAddressField, 4, 6, 2, 1);
				gridPane.add(salaryField, 2, 8, 2, 1);
				gridPane.add(titleField, 0, 2, 2, 1);

				facultyPane.setTop(text1);
				facultyPane.setAlignment(text1, Pos.CENTER);
				facultyPane.setCenter(gridPane);
				facultyPane.setBottom(okBtn);
				facultyPane.setAlignment(okBtn, Pos.CENTER_RIGHT);
				
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
					String officeAddress = officeAddressField.getText();
					String salaryText = salaryField.getText();
					String title = titleField.getText();
					
					double salary = Double.parseDouble(salaryText);

					Address address = new Address(streetNum, streetName, city, state, zip);

					if (middleInitial.equals("")) {
						facultyName = new Name(firstName, lastName);
					} else {
						facultyName = new Name(firstName, middleInitial.charAt(0), lastName);
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
						Alert confirmation2 = new Alert(AlertType.CONFIRMATION, "Faculty Edited!", ButtonType.OK);
						confirmation2.showAndWait();
						if (confirmation2.getResult() == ButtonType.OK) {
							
							faculty1.setName(facultyName);
							faculty1.setPhone(phone);
							faculty1.setAddress(address);
							faculty1.setOfficeAddress(officeAddress);
							faculty1.setSalary(salary);
							
							displayArea.appendText(faculty1.toString());
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
		return facultyPane;
	}
}
