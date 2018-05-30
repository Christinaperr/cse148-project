package view;

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
import model.College;
import model.Course;
import model.Name;
import model.Person;

public class RemoveCoursePane {
	private GridPane gridPane;
	private BorderPane studentPane;

	public RemoveCoursePane(College college) {
		studentPane = new BorderPane();
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(30));
		gridPane.setHgap(20);
		gridPane.setVgap(30);

		Text text1 = new Text();
		text1.setText("Remove a Course");
		text1.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		text1.setFill(Color.PALEVIOLETRED);

		Text text2 = new Text();
		text2.setText("Enter Course Number: ");
		text2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		text2.setFill(Color.PALEVIOLETRED);

		TextField removeIDField = new TextField();
		removeIDField.setPromptText("Course Number");
		removeIDField.setPrefWidth(400);

		Button okBtn = new Button("OK");

		Button exitBtn = new Button("Exit");

		TextArea displayArea = new TextArea();

		gridPane.add(text2, 2, 2, 2, 1);
		gridPane.add(removeIDField, 2, 4, 2, 1);


		studentPane.setTop(text1);
		studentPane.setAlignment(text1, Pos.CENTER);
		studentPane.setCenter(gridPane);
		studentPane.setBottom(okBtn);
		studentPane.setAlignment(okBtn, Pos.CENTER_RIGHT);
		// studentPane.setAlignment(exitBtn, Pos.CENTER_LEFT);

		// studentPane.getChildren().addAll(gridPane, displayArea);

		okBtn.setOnAction(event2 -> {
			String courseNum = removeIDField.getText();
			Course courseToRemoveName = college.getCourseBag().findBycourseNumber(courseNum);
			int courseToRemove = college.getCourseBag().removeBycourseNumber(courseNum);
			if(courseToRemove != -1){
				Alert confirmation = new Alert(AlertType.CONFIRMATION, "Course "+ courseToRemoveName.getCourseNumber() + " Removed!", ButtonType.OK);
				confirmation.showAndWait();
			}
			else{
				Alert alert = new Alert(AlertType.CONFIRMATION, "Course Does Not Exist!", ButtonType.OK);
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
