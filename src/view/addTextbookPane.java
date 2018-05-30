package view;

import javafx.application.Platform;
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
import model.Address;
import model.College;
import model.Faculty;
import model.Name;
import model.Textbook;

public class addTextbookPane {
	private GridPane gridPane;
	private BorderPane textbookPane;
	private Name authorName;

	public addTextbookPane(College college) {
		textbookPane = new BorderPane();
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(30));
		gridPane.setHgap(20);
		gridPane.setVgap(30);

		Text text1 = new Text();
		text1.setText("New Textbook");
		text1.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		text1.setFill(Color.ORANGE);

		TextField authorFirstNameField = new TextField();
		authorFirstNameField.setPromptText("Author's First Name");

		TextField middleInitialField = new TextField();
		middleInitialField.setPromptText("Author's Middle Initial");

		TextField lastNameField = new TextField();
		lastNameField.setPromptText("Author's Last Name");
		
		TextField titleField = new TextField();
		titleField.setPromptText("Title");
		
		TextField publisherField = new TextField();
		publisherField.setPromptText("Publisher");

		
		TextField isbnField = new TextField();
		isbnField.setPromptText("ISBN");
		
		TextField priceField = new TextField();
		priceField.setPromptText("Price");

		Button okBtn = new Button("OK");

		TextArea displayArea = new TextArea();

		gridPane.add(titleField, 0, 2, 5, 1);
		gridPane.add(authorFirstNameField, 0, 4, 2, 1);
		gridPane.add(middleInitialField, 2, 4, 2, 1);
		gridPane.add(lastNameField, 4, 4, 2, 1);
		gridPane.add(publisherField, 0, 6, 2, 1);
		gridPane.add(isbnField, 2, 6, 2, 1);
		gridPane.add(priceField, 4, 6, 2, 1);
		
		textbookPane.setTop(text1);
		textbookPane.setAlignment(text1, Pos.CENTER);
		textbookPane.setCenter(gridPane);
		textbookPane.setBottom(okBtn);
		textbookPane.setAlignment(okBtn, Pos.CENTER_RIGHT);
		

		okBtn.setOnAction(event2 -> {
			String firstName = authorFirstNameField.getText();
			String middleInitial = middleInitialField.getText();
			String lastName = lastNameField.getText();
			String title = titleField.getText();
			String publisher = publisherField.getText();
			String isbn = isbnField.getText();
			String priceText = priceField.getText();
			
			double price = Double.parseDouble(priceText);
			
			authorFirstNameField.clear();
			middleInitialField.clear();
			lastNameField.clear();
			titleField.clear();
			publisherField.clear();
			isbnField.clear();
			priceField.clear();
			
			if(middleInitial.equals("")){
				authorName = new Name(firstName, lastName);
			}
			else{
				authorName = new Name(firstName, middleInitial.charAt(0), lastName);
			}
			Alert confirmation = new Alert(AlertType.CONFIRMATION, "Textbook Added!", ButtonType.OK);
			confirmation.showAndWait();
			
			Textbook textbook = new Textbook(title, authorName, publisher, isbn);
			textbook.setPrice(price);
			
			
			college.getTextbookBag().add(textbook);
			displayArea.appendText(textbook.toString());

			
		});
	
	}

	public GridPane getGridPane() {
		return gridPane;
	}

	public BorderPane getBorderPane() {
		return textbookPane;
	}
}
