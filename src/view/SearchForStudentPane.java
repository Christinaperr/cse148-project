package view;

import java.util.Arrays;
import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.College;
import model.Person;
import model.Student;

public class SearchForStudentPane {
	private GridPane gridPane;
	private BorderPane studentPane;

	public SearchForStudentPane(College college) {
		studentPane = new BorderPane();
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(30));
		gridPane.setHgap(20);
		gridPane.setVgap(30);

		Text text1 = new Text();
		text1.setText("Search for a Student");
		text1.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		text1.setFill(Color.SLATEBLUE);

		Text text2 = new Text();
		text2.setText("Enter Student ID: ");
		text2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		text2.setFill(Color.SLATEBLUE);

		TextField iDField = new TextField();
		iDField.setPromptText("Student ID");
		iDField.setPrefWidth(400);

		Button okBtn = new Button("OK");

		Button exitBtn = new Button("Exit");

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
				Alert confirmation = new Alert(AlertType.CONFIRMATION,
						"Student " + studentToSearch.getName() + " Found! \nWant to look at Students information?",
						ButtonType.NO, ButtonType.YES);
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
					HBox hBox7 = new HBox(30);
					hBox7.setPadding(inset);
					HBox hBox8 = new HBox(30);
					hBox8.setPadding(inset);

					GridPane gPane = new GridPane();
					gridPane.setPadding(new Insets(30));

					Student student1 = (Student) studentToSearch;
					String studentName = student1.getName().getFirstName() + " " + student1.getName().getMiddleInitial()
							+ " " + student1.getName().getLastName();
					String studentAddress = student1.getAddress().display();
					Text nameText = new Text(studentName);
					Text idText = new Text(student1.getId());
					Text phoneText = new Text(student1.getPhone());
					Text addressText = new Text(studentAddress);
					Text majorText = new Text(student1.displayMajor());
					Text coursesTookText = new Text("Courses Took: \n" + Arrays.toString(student1.getCoursesTook()));
					Text coursesTakingText = new Text(
							"Courses Taking: \n" + Arrays.toString(student1.getCoursesTaking()));
					Text coursesToTakeText = new Text(
							"Courses To Take: \n" + Arrays.toString(student1.getCoursesToTake()));

					hBox1.getChildren().addAll(nameText);
					hBox2.getChildren().add(idText);
					hBox3.getChildren().add(phoneText);
					hBox4.getChildren().add(addressText);
					hBox5.getChildren().add(majorText);
					hBox6.getChildren().add(coursesTookText);
					hBox7.getChildren().add(coursesTakingText);
					hBox8.getChildren().add(coursesToTakeText);

					gPane.add(hBox1, 0, 1, 2, 1);
					gPane.add(hBox2, 0, 3, 2, 1);
					gPane.add(hBox3, 0, 5, 2, 1);
					gPane.add(hBox4, 0, 7, 2, 1);
					gPane.add(hBox5, 0, 9, 2, 1);
					gPane.add(hBox6, 0, 12, 2, 1);
					gPane.add(hBox7, 0, 15, 2, 1);
					gPane.add(hBox8, 0, 18, 2, 1);

					Stage studentInfo = new Stage();
					studentInfo.setTitle("Student Information");
					studentInfo.setScene(new Scene(gPane, 450, 450));
					studentInfo.show();
				}
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
