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
import model.Course;
import model.Person;
import model.Student;

public class SearchForCoursePane {
	private GridPane gridPane;
	private BorderPane coursePane;

	public SearchForCoursePane(College college) {
		coursePane = new BorderPane();
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(30));
		gridPane.setHgap(20);
		gridPane.setVgap(30);

		Text text1 = new Text();
		text1.setText("Search for a Course");
		text1.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		text1.setFill(Color.CRIMSON);

		Text text2 = new Text();
		text2.setText("Enter Course Number: ");
		text2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		text2.setFill(Color.CRIMSON);

		TextField removeIDField = new TextField();
		removeIDField.setPromptText("Course Number");
		removeIDField.setPrefWidth(400);

		Button okBtn = new Button("OK");

		Button exitBtn = new Button("Exit");

		TextArea displayArea = new TextArea();

		gridPane.add(text2, 2, 2, 2, 1);
		gridPane.add(removeIDField, 2, 4, 2, 1);

		coursePane.setTop(text1);
		coursePane.setAlignment(text1, Pos.CENTER);
		coursePane.setCenter(gridPane);
		coursePane.setBottom(okBtn);
		coursePane.setAlignment(okBtn, Pos.CENTER_RIGHT);
		// studentPane.setAlignment(exitBtn, Pos.CENTER_LEFT);

		// studentPane.getChildren().addAll(gridPane, displayArea);

		okBtn.setOnAction(event2 -> {
			String courseNum = removeIDField.getText();
			Course courseToSearch = college.getCourseBag().findBycourseNumber(courseNum);
			if (courseToSearch != null) {
				Alert confirmation = new Alert(AlertType.CONFIRMATION, "Course " + courseToSearch.getCourseNumber()
						+ " Found! \nWant to look at course information?", ButtonType.YES, ButtonType.NO);
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

					GridPane gPane = new GridPane();
					gridPane.setPadding(new Insets(30));

					Text courseNameText = new Text(courseToSearch.getCourseTitle());
					Text courseNumText = new Text(courseToSearch.getCourseNumber());
					Text creditsText = new Text("Credits: " + String.valueOf(courseToSearch.getNumberOfCredits()));
					Text facultyIdText = new Text("Faculty ID: " + courseToSearch.getFacultyID());
					Text textbookText = new Text("Textbook ISBN: " + courseToSearch.getTextbookISBN());

					hBox1.getChildren().addAll(courseNameText);
					hBox2.getChildren().add(courseNumText);
					hBox3.getChildren().add(creditsText);
					hBox4.getChildren().add(facultyIdText);
					hBox5.getChildren().add(textbookText);

					gPane.add(hBox1, 0, 1, 2, 1);
					gPane.add(hBox2, 0, 3, 2, 1);
					gPane.add(hBox3, 0, 5, 2, 1);
					gPane.add(hBox4, 0, 7, 2, 1);
					gPane.add(hBox5, 0, 9, 2, 1);

					Stage studentInfo = new Stage();
					studentInfo.setTitle("Course Information");
					studentInfo.setScene(new Scene(gPane, 450, 450));
					studentInfo.show();
				}
				else if(confirmation.getResult() == ButtonType.NO){
					confirmation.close();
				}
			} else {
				Alert alert = new Alert(AlertType.CONFIRMATION, "Course Does Not Exist!", ButtonType.OK);
				alert.showAndWait();
			}
		});

	}

	public GridPane getGridPane() {
		return gridPane;
	}

	public BorderPane getBorderPane() {
		return coursePane;
	}
}
