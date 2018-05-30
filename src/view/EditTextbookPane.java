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
import model.Textbook;

public class EditTextbookPane {
	private GridPane gridPane;
	private BorderPane coursePane;
	private Name authorName;

	public EditTextbookPane(College college) {
		coursePane = new BorderPane();
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(30));
		gridPane.setHgap(20);
		gridPane.setVgap(30);

		Text text1 = new Text();
		text1.setText("Edit a Textbook");
		text1.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		text1.setFill(Color.LIGHTSLATEGREY);

		Text text2 = new Text();
		text2.setText("Enter a Textbook: ");
		text2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		text2.setFill(Color.LIGHTSLATEGREY);

		TextField iDField = new TextField();
		iDField.setPromptText("Textbook ISBN");
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
			String textbook1 = iDField.getText();
			Textbook textbookToSearch = college.getTextbookBag().findByIbsn(textbook1);
			if (textbookToSearch != null) {
				Alert confirmation = new Alert(AlertType.CONFIRMATION,
						"Textbook " + textbookToSearch.getTitle() + " Found!", ButtonType.OK);
				confirmation.showAndWait();

				TextField textbookTitleField = new TextField(textbookToSearch.getTitle());

				TextField authorFirstNameField = new TextField(textbookToSearch.getAuthor().getFirstName());

				TextField middleInitialField = new TextField(
						String.valueOf(textbookToSearch.getAuthor().getMiddleInitial()));
				middleInitialField.setPromptText("Middle Initial");

				TextField lastNameField = new TextField(textbookToSearch.getAuthor().getLastName());

				TextField textbookPublisherField = new TextField(textbookToSearch.getPublisher());

				TextField textbookIsbnField = new TextField(textbookToSearch.getIsbn());

				TextField textbookPriceField = new TextField(String.valueOf(textbookToSearch.getPrice()));

				gridPane.add(textbookTitleField, 0, 2, 5, 1);
				gridPane.add(authorFirstNameField, 0, 4, 2, 1);
				gridPane.add(middleInitialField, 2, 4, 2, 1);
				gridPane.add(lastNameField, 4, 4, 2, 1);
				gridPane.add(textbookPublisherField, 0, 6, 2, 1);
				gridPane.add(textbookIsbnField, 2, 6, 2, 1);
				gridPane.add(textbookPriceField, 4, 6, 2, 1);

				coursePane.setTop(text1);
				coursePane.setAlignment(text1, Pos.CENTER);
				coursePane.setCenter(gridPane);
				coursePane.setBottom(okBtn);
				coursePane.setAlignment(okBtn, Pos.CENTER_RIGHT);

				okBtn.setOnAction(event3 -> {

					String firstName = authorFirstNameField.getText();
					String middleInitial = middleInitialField.getText();
					String lastName = lastNameField.getText();
					String title = textbookTitleField.getText();
					String publisher = textbookPublisherField.getText();
					String isbn = textbookIsbnField.getText();
					String priceText = textbookPriceField.getText();

					double price = Double.parseDouble(priceText);

					if (middleInitial.equals("")) {
						authorName = new Name(firstName, lastName);
					} else {
						authorName = new Name(firstName, middleInitial.charAt(0), lastName);
					}

					textbookToSearch.setTitle(title);
					textbookToSearch.setAuthor(authorName);
					textbookToSearch.setPublisher(publisher);
					textbookToSearch.setIsbn(isbn);
					textbookToSearch.setPrice(price);


					Alert confirmation2 = new Alert(AlertType.CONFIRMATION, "Textbook Edited!", ButtonType.OK);
					confirmation2.showAndWait();

				});
			} else {
				Alert alert = new Alert(AlertType.CONFIRMATION, "Textbook Does Not Exist!", ButtonType.OK);
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
