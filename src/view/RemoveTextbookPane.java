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
import model.Textbook;

public class RemoveTextbookPane {
	private GridPane gridPane;
	private BorderPane textbookPane;

	public RemoveTextbookPane(College college) {
		textbookPane = new BorderPane();
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(30));
		gridPane.setHgap(20);
		gridPane.setVgap(30);

		Text text1 = new Text();
		text1.setText("Remove Textbook");
		text1.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		text1.setFill(Color.INDIANRED);

		Text text2 = new Text();
		text2.setText("Enter Textbook ISBN: ");
		text2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		text2.setFill(Color.INDIANRED);

		TextField removeIDField = new TextField();
		removeIDField.setPromptText("Textbook ISBN");
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
			Textbook textbook1 = college.getTextbookBag().findByIbsn(textbookISBN);
			System.out.println(textbookISBN);
			int textbookToRemove = college.getTextbookBag().removeByIsbn(textbookISBN);
			System.out.println(textbookISBN);
			if(textbookToRemove != -1){
				Alert confirmation = new Alert(AlertType.CONFIRMATION, "Textbook "+ textbook1.getTitle() + " Removed!", ButtonType.OK);
				confirmation.showAndWait();
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
