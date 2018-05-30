package view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.Address;
import model.College;
import model.Name;
import model.Student;

public class StudentPane {
private GridPane gridPane;

	public StudentPane(College college){
		//BorderPane studentPane = new BorderPane();
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(30));
		gridPane.setHgap(20);
		gridPane.setVgap(30);

		Label nameLbl = new Label("Name");
		TextField nameField = new TextField();

		Label phoneLbl = new Label("Phone");
		TextField phoneField = new TextField();

		Label streetNumLbl = new Label("Street Number");
		TextField streetNumField = new TextField();

		Label streetNameLbl = new Label("Street Name");
		TextField streetNameField = new TextField();

		Label cityLbl = new Label("City");
		TextField cityField = new TextField();

		Label stateLbl = new Label("State");
		TextField stateField = new TextField();

		Label ZipLbl = new Label("Zip code");
		TextField zipField = new TextField();

		Button okBtn = new Button("OK");
		Button exitBtn = new Button("Exit");

		TextArea displayArea = new TextArea();
		gridPane.add(nameField, 0, 0, 2, 1);
		gridPane.add(phoneField, 2, 0, 2, 1);
		//studentPane.getChildren().addAll(studentPane, displayArea);

		okBtn.setOnAction(event2 -> {
			String name = nameField.getText();
			String phone = phoneField.getText();
			String streetNum = streetNumField.getText();
			String streetName = streetNameField.getText();
			String city = cityField.getText();
			String state = stateField.getText();
			String zip = zipField.getText();
			Address address = new Address(streetNum, streetName, city, state, zip);
			nameField.clear();
			phoneField.clear();
			streetNumField.clear();
			streetNameField.clear();
			cityField.clear();
			stateField.clear();
			zipField.clear();
			String[] tokens = name.split(" ");
			Name studentName = new Name(tokens[0], tokens[1]);
			Student student1 = new Student(studentName, phone, address);
			college.getPersonBag().add(student1);
			displayArea.appendText(student1.toString());

			exitBtn.setOnAction(event3 -> {
				//bag1.save();
				//bag2.save();
				//bag3.save();
				//idCounter.save();
				//nElems.save();
				Platform.exit();
			});

		});
	}
	public GridPane getGridPane(){
		return gridPane;
	}
}
