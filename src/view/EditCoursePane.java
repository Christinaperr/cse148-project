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

public class EditCoursePane {
	private GridPane gridPane;
	private BorderPane coursePane;
	
	public EditCoursePane(College college) {
		coursePane = new BorderPane();
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(30));
		gridPane.setHgap(20);
		gridPane.setVgap(30);

		Text text1 = new Text();
		text1.setText("Edit a Course");
		text1.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		text1.setFill(Color.LIGHTSLATEGREY);

		Text text2 = new Text();
		text2.setText("Enter Course Number: ");
		text2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		text2.setFill(Color.LIGHTSLATEGREY);

		TextField iDField = new TextField();
		iDField.setPromptText("Course Number");
		iDField.setPrefWidth(400);

		Button okBtn = new Button("OK");


		TextArea displayArea = new TextArea();

		gridPane.add(text2, 2, 2, 2, 1);
		gridPane.add(iDField, 2, 4, 2, 1);

		coursePane.setTop(text1);
		coursePane.setAlignment(text1, Pos.CENTER);
		coursePane.setCenter(gridPane);
		coursePane.setBottom(okBtn);
		coursePane.setAlignment(okBtn, Pos.CENTER_RIGHT);

		okBtn.setOnAction(event2 -> {
			String courseNum = iDField.getText();
			Course courseToSearch = college.getCourseBag().findBycourseNumber(courseNum);
			if (courseToSearch != null) {
				Alert confirmation = new Alert(AlertType.CONFIRMATION,
						"Course " + courseToSearch.getCourseTitle() + " Found!",
						ButtonType.OK);
				confirmation.showAndWait();

				TextField courseTitleField = new TextField(courseToSearch.getCourseTitle());

				TextField courseNumberField = new TextField(courseToSearch.getCourseNumber());

				TextField numOfCreditField = new TextField(String.valueOf(courseToSearch.getNumberOfCredits()));

				TextField textbookIsbnField = new TextField(courseToSearch.getTextbookISBN());

				TextField facultyIDField = new TextField(courseToSearch.getFacultyID());
				
				gridPane.add(courseTitleField, 0, 0, 2, 1);
				gridPane.add(courseNumberField, 2, 0, 2, 1);
				gridPane.add(numOfCreditField, 4, 0, 2, 1);
				gridPane.add(textbookIsbnField, 0, 2, 2, 1);
				gridPane.add(facultyIDField, 2, 2, 2, 1);

				coursePane.setTop(text1);
				coursePane.setAlignment(text1, Pos.CENTER);
				coursePane.setCenter(gridPane);
				coursePane.setBottom(okBtn);
				coursePane.setAlignment(okBtn, Pos.CENTER_RIGHT);
				
				okBtn.setOnAction(event3 -> {
					String courseTitle = courseTitleField.getText();
					String courseNumber = courseNumberField.getText();
					String numOfCreditText = numOfCreditField.getText();
					String textbookIsbn = textbookIsbnField.getText();
					String facultyID = facultyIDField.getText();

					int credits = Integer.parseInt(numOfCreditText);
					
					courseToSearch.setCourseTitle(courseTitle);
					courseToSearch.setCourseNumber(courseNumber);
					courseToSearch.setNumberOfCredits(credits);
					courseToSearch.setTextbookISBN(textbookIsbn);
					courseToSearch.setFacultyID(facultyID);
					
					displayArea.appendText(courseToSearch.toString());
					
					Alert confirmation2 = new Alert(AlertType.CONFIRMATION, "Course Edited!", ButtonType.OK);
					confirmation2.showAndWait();
					
				});
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
