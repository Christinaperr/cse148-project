package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
import model.Textbook;

public class AddCoursePane {
	private GridPane gridPane;
	private BorderPane coursePane;

	public AddCoursePane(College college) {
		coursePane = new BorderPane();
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(30));
		gridPane.setHgap(20);
		gridPane.setVgap(30);

		Text text1 = new Text();
		text1.setText("New Course");
		text1.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		text1.setFill(Color.FORESTGREEN);

		TextField courseTitleField = new TextField();
		courseTitleField.setPromptText("Course Title");

		TextField courseNumberField = new TextField();
		courseNumberField.setPromptText("Course Number");

		TextField numOfCreditField = new TextField();
		numOfCreditField.setPromptText("Credits");

		TextField textbookIsbnField = new TextField();
		textbookIsbnField.setPromptText("Textbook ISBN");

		TextField facultyIDField = new TextField();
		facultyIDField.setPromptText("Faculty ID");

		Button okBtn = new Button("OK");

		TextArea displayArea = new TextArea();

		gridPane.add(courseTitleField, 0, 2, 2, 1);
		gridPane.add(courseNumberField, 2, 2, 2, 1);
		gridPane.add(numOfCreditField, 4, 2, 2, 1);
		gridPane.add(textbookIsbnField, 0, 4, 2, 1);
		gridPane.add(facultyIDField, 2, 4, 2, 1);

		coursePane.setTop(text1);
		coursePane.setAlignment(text1, Pos.CENTER);
		coursePane.setCenter(gridPane);
		coursePane.setBottom(okBtn);
		coursePane.setAlignment(okBtn, Pos.CENTER_RIGHT);

		okBtn.setOnAction(event2 -> {
			String courseTitle = courseTitleField.getText();
			String courseNumber = courseNumberField.getText();
			String creditsText = numOfCreditField.getText();
			String textbookIsbn = textbookIsbnField.getText();
			String facultyID = facultyIDField.getText();

			int credits = Integer.parseInt(creditsText);

			courseTitleField.clear();
			courseNumberField.clear();
			numOfCreditField.clear();
			textbookIsbnField.clear();
			facultyIDField.clear();


			Course course = new Course(courseTitle, courseNumber, credits);
			course.setFacultyID(facultyID);
			course.setTextbookISBN(textbookIsbn);

			college.getCourseBag().add(course);
			displayArea.appendText(course.toString());
			
			Alert confirmation = new Alert(AlertType.CONFIRMATION, "Course Added!", ButtonType.OK);
			confirmation.showAndWait();

		});

	}

	public GridPane getGridPane() {
		return gridPane;
	}

	public BorderPane getBorderPane() {
		return coursePane;
	}
}
