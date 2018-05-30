package view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import model.Name;
import model.PhoneNumberException;
import model.Student;

public class AddFacultyPane {
	private GridPane gridPane;
	private BorderPane facultyPane;
	private Faculty faculty1;
	private Name facultyName;

	public AddFacultyPane(College college) {
		facultyPane = new BorderPane();
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(30));
		gridPane.setHgap(20);
		gridPane.setVgap(30);

		Text text1 = new Text();
		text1.setText("New Faculty");
		text1.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		text1.setFill(Color.PURPLE);

		TextField firstNameField = new TextField();
		firstNameField.setPromptText("First Name");

		TextField middleInitialField = new TextField();
		middleInitialField.setPromptText("Middle Initial");

		TextField lastNameField = new TextField();
		lastNameField.setPromptText("Last Name");
		
		TextField titleField = new TextField();
		titleField.setPromptText("Title");
		
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
		
		TextField officeAddressField = new TextField();
		officeAddressField.setPromptText("Office Address");
		
		TextField salaryField = new TextField();
		salaryField.setPromptText("Salary");

		Button okBtn = new Button("OK");
		Button exitBtn = new Button("Exit");

		TextArea displayArea = new TextArea();

		gridPane.add(firstNameField, 0, 0, 2, 1);
		gridPane.add(middleInitialField, 2, 0, 2, 1);
		gridPane.add(lastNameField, 4, 0, 2, 1);
		gridPane.add(titleField, 0, 2, 2, 1);
		gridPane.add(phoneField, 3, 2, 2, 1);
		gridPane.add(streetNumField, 0, 4, 2, 1);
		gridPane.add(streetNameField, 2, 4, 2, 1);
		gridPane.add(cityField, 4, 4, 2, 1);
		gridPane.add(stateField, 0, 6, 2, 1);
		gridPane.add(zipField, 2, 6, 2, 1);
		gridPane.add(officeAddressField, 4, 6, 2, 1);
		gridPane.add(salaryField, 2, 8, 2, 1);
		
		facultyPane.setTop(text1);
		facultyPane.setAlignment(text1, Pos.CENTER);
		facultyPane.setCenter(gridPane);
		facultyPane.setBottom(okBtn);
		facultyPane.setAlignment(okBtn, Pos.CENTER_RIGHT);
		

		okBtn.setOnAction(event2 -> {
			String firstName = firstNameField.getText();
			String middleInitial = middleInitialField.getText();
			String lastName = lastNameField.getText();
			String title = titleField.getText();
			String phone = phoneField.getText();
			String streetNum = streetNumField.getText();
			String streetName = streetNameField.getText();
			String city = cityField.getText();
			String state = stateField.getText();
			String zip = zipField.getText();
			String officeAddress = officeAddressField.getText();
			String salaryText = salaryField.getText();
			Address address = new Address(streetNum, streetName, city, state, zip);
			
			double salary = Double.parseDouble(salaryText);
			
			
			
			if(middleInitial.equals("")){
				facultyName = new Name(firstName, lastName);
			}
			else{
				facultyName = new Name(firstName, middleInitial.charAt(0), lastName);
			}
			
			if (!phone.matches("[(]?(\\d{3})[-.)]?(\\d{3})[-.]?(\\d{4})")) {
				try {
					throw new PhoneNumberException("Wrong phone number format. Enter again:");
				} catch (PhoneNumberException e) {
					Alert alert = new Alert(AlertType.ERROR, "Phone Number is wrong format! Please enter again!", ButtonType.OK);
					alert.showAndWait();
					phoneField.requestFocus();
				}
			} else {
				phone = phone.replaceAll("[(]?(\\d{3})[-.)]?(\\d{3})[-.]?(\\d{4})", "($1)$2-$3");
				Alert confirmation = new Alert(AlertType.CONFIRMATION, "Faculty Added!", ButtonType.OK);
				confirmation.showAndWait();
				if(confirmation.getResult() == ButtonType.OK){
					firstNameField.clear();
					middleInitialField.clear();
					lastNameField.clear();
					titleField.clear();
					phoneField.clear();
					streetNumField.clear();
					streetNameField.clear();
					cityField.clear();
					stateField.clear();
					zipField.clear();
					officeAddressField.clear();
					salaryField.clear();
					
					faculty1 = new Faculty(facultyName, phone, address);
					faculty1.setTitle(title);
					faculty1.setOfficeAddress(officeAddress);
					faculty1.setSalary(salary);
					
					
					college.getPersonBag().add(faculty1);
					displayArea.appendText(faculty1.toString());
				}

			}
			
			
			

			
		});
		
		exitBtn.setOnAction(event3 -> {
			// bag1.save();
			// bag2.save();
			// bag3.save();
			// idCounter.save();
			// nElems.save();
			Platform.exit();
		});
	}

	public GridPane getGridPane() {
		return gridPane;
	}

	public BorderPane getBorderPane() {
		return facultyPane;
	}
}
