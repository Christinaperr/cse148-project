package view;

import java.util.Arrays;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.College;
import model.Faculty;
import model.Person;
import model.Student;

public class SearchForFacultyPane {
	private GridPane gridPane;
	private BorderPane facultyPane;

	public SearchForFacultyPane(College college) {
		facultyPane = new BorderPane();
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(30));
		gridPane.setHgap(20);
		gridPane.setVgap(30);

		Text text1 = new Text();
		text1.setText("Search for Faculty");
		text1.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		text1.setFill(Color.INDIGO);

		Text text2 = new Text();
		text2.setText("Enter Faculty ID: ");
		text2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		text2.setFill(Color.INDIGO);

		TextField removeIDField = new TextField();
		removeIDField.setPromptText("Student ID");
		removeIDField.setPrefWidth(400);

		Button okBtn = new Button("OK");

		Button exitBtn = new Button("Exit");

		TextArea displayArea = new TextArea();

		gridPane.add(text2, 2, 2, 2, 1);
		gridPane.add(removeIDField, 2, 4, 2, 1);

		facultyPane.setTop(text1);
		facultyPane.setAlignment(text1, Pos.CENTER);
		facultyPane.setCenter(gridPane);
		facultyPane.setBottom(okBtn);
		facultyPane.setAlignment(okBtn, Pos.CENTER_RIGHT);

		okBtn.setOnAction(event2 -> {
			String facultyID = removeIDField.getText();
			Person facultyToSearch = college.getPersonBag().findById(facultyID);
			if (facultyToSearch != null && facultyToSearch instanceof Faculty) {
				Alert confirmation = new Alert(AlertType.CONFIRMATION,
						"Faculty " + facultyToSearch.getName() + " Found!\nWant to see faculty's information?",
						ButtonType.YES, ButtonType.NO);
				confirmation.showAndWait();
				if (confirmation.getResult() == ButtonType.YES) {
					Insets inset = new Insets(10);
					HBox hBox1 = new HBox(30);
					hBox1.setPadding(inset);
					HBox hBox2 = new HBox(30);
					hBox2.setPadding(inset);
					HBox hBox3 = new HBox(30);
					hBox3.setPadding(inset);
					HBox hBox4 = new HBox(30);
					hBox4.setPadding(inset);
					HBox hBox5 = new HBox(30);
					hBox5.setPadding(inset);
					HBox hBox6 = new HBox(30);
					hBox6.setPadding(inset);

					GridPane gPane = new GridPane();
					gridPane.setPadding(new Insets(30));

					Faculty faculty1 = (Faculty) facultyToSearch;
					String facultyName = faculty1.getName().getFirstName() + " " + faculty1.getName().getMiddleInitial()
							+ " " + faculty1.getName().getLastName();
					String facultyAddress = faculty1.getAddress().display();
					Text nameText = new Text(facultyName);
					Text idText = new Text(faculty1.getId());
					Text phoneText = new Text(faculty1.getPhone());
					Text addressText = new Text(facultyAddress);
					Text officeAddressText = new Text(faculty1.getOfficeAddress());
					Text titleText = new Text(faculty1.getTitle());
					Text salaryText = new Text(String.valueOf(faculty1.getSalary()));

					hBox1.getChildren().addAll(titleText, nameText);
					hBox2.getChildren().add(idText);
					hBox3.getChildren().add(phoneText);
					hBox4.getChildren().add(addressText);
					hBox5.getChildren().add(officeAddressText);
					hBox6.getChildren().add(salaryText);

					gPane.add(hBox1, 0, 1, 2, 1);
					gPane.add(hBox2, 0, 3, 2, 1);
					gPane.add(hBox3, 0, 5, 2, 1);
					gPane.add(hBox4, 0, 7, 2, 1);
					gPane.add(hBox5, 0, 9, 2, 1);
					gPane.add(hBox6, 0, 12, 2, 1);

					Stage studentInfo = new Stage();
					studentInfo.setTitle("Faculty Information");
					studentInfo.setScene(new Scene(gPane, 450, 450));
					studentInfo.show();
				}
			} else {
				Alert alert = new Alert(AlertType.CONFIRMATION, "Faculty Does Not Exist!", ButtonType.OK);
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
