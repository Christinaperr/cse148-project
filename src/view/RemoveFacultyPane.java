package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import model.College;
import model.Faculty;
import model.Name;
import model.Person;

public class RemoveFacultyPane {
	private GridPane gridPane;
	private BorderPane facultyPane;


	public RemoveFacultyPane(College college) {
		facultyPane = new BorderPane();
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(30));
		gridPane.setHgap(20);
		gridPane.setVgap(30);

		Text text1 = new Text();
		text1.setText("Remove Faculty");
		text1.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		text1.setFill(Color.DIMGREY);

		Text text2 = new Text();
		text2.setText("Enter Faculty ID: ");
		text2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		text2.setFill(Color.DIMGRAY);

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
		// studentPane.setAlignment(exitBtn, Pos.CENTER_LEFT);

		// studentPane.getChildren().addAll(gridPane, displayArea);

		okBtn.setOnAction(event2 -> {
			String facultyID = removeIDField.getText();
			Person faculty1 = college.getPersonBag().findById(facultyID);
			int facultyToRemove = college.getPersonBag().removeById(facultyID);
			if(facultyToRemove != -1 && faculty1 instanceof Faculty){
				Alert confirmation = new Alert(AlertType.CONFIRMATION, "Faculty "+ faculty1.getName() + " Removed!", ButtonType.OK);
				confirmation.showAndWait();
			}
			else{
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
