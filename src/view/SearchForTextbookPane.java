package view;

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
import model.Person;
import model.Textbook;

public class SearchForTextbookPane {
	private GridPane gridPane;
	private BorderPane textbookPane;

	public SearchForTextbookPane(College college) {
		textbookPane = new BorderPane();
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(30));
		gridPane.setHgap(20);
		gridPane.setVgap(30);

		Text text1 = new Text();
		text1.setText("Search for a Textbook");
		text1.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		text1.setFill(Color.DARKOLIVEGREEN);

		Text text2 = new Text();
		text2.setText("Enter Textbook ISBN: ");
		text2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		text2.setFill(Color.DARKOLIVEGREEN);

		TextField removeIDField = new TextField();
		removeIDField.setPromptText("Course Number");
		removeIDField.setPrefWidth(400);

		Button okBtn = new Button("OK");

		Button exitBtn = new Button("Exit");

		TextArea displayArea = new TextArea();

		gridPane.add(text2, 2, 2, 2, 1);
		gridPane.add(removeIDField, 2, 4, 2, 1);


		textbookPane.setTop(text1);
		textbookPane.setAlignment(text1, Pos.CENTER);
		textbookPane.setCenter(gridPane);
		textbookPane.setBottom(okBtn);
		textbookPane.setAlignment(okBtn, Pos.CENTER_RIGHT);
		// studentPane.setAlignment(exitBtn, Pos.CENTER_LEFT);

		// studentPane.getChildren().addAll(gridPane, displayArea);

		okBtn.setOnAction(event2 -> {
			String textbookISBN = removeIDField.getText();
			 Textbook textbookToSearch = college.getTextbookBag().findByIbsn(textbookISBN);
			if(textbookToSearch != null){
				Alert confirmation = new Alert(AlertType.CONFIRMATION, "Textbook "+textbookToSearch.getTitle() + " Found! Want to see textbook information?", ButtonType.YES, ButtonType.NO);
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

					Text textbookTitleText = new Text(textbookToSearch.getTitle());
					Text creditsText = new Text(textbookToSearch.getAuthor().getFirstName() + " " + textbookToSearch.getAuthor().getMiddleInitial() + " " + textbookToSearch.getAuthor().getLastName());
					Text courseNumText = new Text(textbookToSearch.getPublisher());
					Text facultyIdText = new Text("ISBN: " + textbookToSearch.getIsbn());
					Text priceText = new Text("Price : $" + textbookToSearch.getPrice());

					hBox1.getChildren().addAll(textbookTitleText);
					hBox2.getChildren().add(creditsText);
					hBox3.getChildren().add(courseNumText);
					hBox4.getChildren().add(facultyIdText);
					hBox5.getChildren().add(priceText);

					gPane.add(hBox1, 0, 1, 2, 1);
					gPane.add(hBox2, 0, 3, 2, 1);
					gPane.add(hBox3, 0, 5, 2, 1);
					gPane.add(hBox4, 0, 7, 2, 1);
					gPane.add(hBox5, 0, 9, 2, 1);

					Stage studentInfo = new Stage();
					studentInfo.setTitle("Textbook Information");
					studentInfo.setScene(new Scene(gPane, 450, 450));
					studentInfo.show();
				}
				else if(confirmation.getResult() == ButtonType.NO){
					confirmation.close();
				}
			}
			else{
				Alert alert = new Alert(AlertType.CONFIRMATION, "Textbook Does Not Exist!", ButtonType.OK);
				alert.showAndWait();
			}
		});

	}

	public GridPane getGridPane() {
		return gridPane;
	}

	public BorderPane getBorderPane() {
		return textbookPane;
	}
}
