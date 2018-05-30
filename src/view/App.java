package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.College;
import model.CourseBag;
import model.PersonBag;
import model.TextbookBag;

public class App extends Application {
	CourseBag cBag = new CourseBag(100);
	TextbookBag tbBag = new TextbookBag(100);
	PersonBag pBag = new PersonBag(100);

	public static void main(String[] args) {
		
		launch(args);

	}

	public void init() {
		cBag.load();
		tbBag.load();
		pBag.load();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		College college = new College();
		college.setPersonBag(pBag);
		college.setTextbookBag(tbBag);
		college.setCourseBag(cBag);

		BorderPane bPane = new BorderPane();

		Text text1 = new Text();
		text1.setText("Welcome to the \nSchool Directory!");
		text1.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		text1.setFill(Color.MEDIUMSLATEBLUE);

		bPane.setCenter(text1);

		MenuBar menuBar = new MenuBar();

		Menu menuFile = new Menu("File");
		Menu menuAdd = new Menu("Add");
		Menu menuRemove = new Menu("Remove");
		Menu menuSearch = new Menu("Search");
		Menu menuEdit = new Menu("Edit");

		MenuItem menuItemImport = new MenuItem("Import");
		MenuItem menuItemExport = new MenuItem("Export");
		MenuItem menuItemSave = new MenuItem("Save");
		MenuItem menuItemLoad = new MenuItem("Load");

		MenuItem menuItemStudentAdd= new MenuItem("Student");
		MenuItem menuItemFacultyAdd = new MenuItem("Faculty");
		MenuItem menuItemTextbookAdd = new MenuItem("Textbook");
		MenuItem menuItemCourseAdd = new MenuItem("Course");

		MenuItem menuItemStudentRemove = new MenuItem("Student");
		MenuItem menuItemFacultyRemove = new MenuItem("Faculty");
		MenuItem menuItemTextbookRemove = new MenuItem("Textbook");
		MenuItem menuItemCourseRemove = new MenuItem("Course");

		MenuItem menuItemStudentEdit = new MenuItem("Student");
		MenuItem menuItemFacultyEdit = new MenuItem("Faculty");
		MenuItem menuItemTextbookEdit = new MenuItem("Textbook");
		MenuItem menuItemCourseEdit = new MenuItem("Course");

		MenuItem menuItemStudentSearch = new MenuItem("Student");
		MenuItem menuItemFacultySearch = new MenuItem("Faculty");
		MenuItem menuItemTextbookSearch = new MenuItem("Textbook");
		MenuItem menuItemCourseSearch = new MenuItem("Course");

		menuFile.getItems().addAll(menuItemImport, menuItemExport, menuItemSave, menuItemLoad);
		menuAdd.getItems().addAll(menuItemStudentAdd, menuItemFacultyAdd, menuItemTextbookAdd,
				menuItemCourseAdd);
		menuRemove.getItems().addAll(menuItemStudentRemove, menuItemFacultyRemove, menuItemTextbookRemove,
				menuItemCourseRemove);
		menuSearch.getItems().addAll(menuItemStudentSearch, menuItemFacultySearch, menuItemTextbookSearch,
				menuItemCourseSearch);
		menuEdit.getItems().addAll(menuItemStudentEdit, menuItemFacultyEdit, menuItemTextbookEdit, menuItemCourseEdit);

		menuBar.getMenus().addAll(menuFile, menuAdd, menuRemove, menuSearch, menuEdit);
		
		menuItemImport.setOnAction(e -> {
			cBag.importData();
			tbBag.importData();
			pBag.importDataStudent();
			pBag.importDataFaculty();
		});
		menuItemExport.setOnAction(e -> {
			pBag.exportDataStudent();
			cBag.exportData();
			tbBag.exportData();
			pBag.exportDataFaculty();
		});
		menuItemSave.setOnAction(e -> {
			college.save();
		});

		menuItemLoad.setOnAction(e -> {
			college.load();
		});
		menuItemStudentAdd.setOnAction(e -> {
				AddStudentPane studentPane = new AddStudentPane(college);
				bPane.setCenter(studentPane.getBorderPane());
			});

		menuItemFacultyAdd.setOnAction(e -> {
			AddFacultyPane facultyPane = new AddFacultyPane(college);
			bPane.setCenter(facultyPane.getBorderPane());
		});

		menuItemTextbookAdd.setOnAction(e -> {
			addTextbookPane textbookPane = new addTextbookPane(college);
			bPane.setCenter(textbookPane.getBorderPane());
		});

		menuItemCourseAdd.setOnAction(e -> {
			AddCoursePane coursePane = new AddCoursePane(college);
			bPane.setCenter(coursePane.getBorderPane());
		});

		menuItemStudentRemove.setOnAction(e -> {
			RemoveStudentPane removeStudent = new RemoveStudentPane(college);
			bPane.setCenter(removeStudent.getBorderPane());
		});

		menuItemFacultyRemove.setOnAction(e -> {
			RemoveFacultyPane removeFaculty = new RemoveFacultyPane(college);
			bPane.setCenter(removeFaculty.getBorderPane());
		});

		menuItemCourseRemove.setOnAction(e -> {
			RemoveCoursePane removeCourse = new RemoveCoursePane(college);
			bPane.setCenter(removeCourse.getBorderPane());
		});

		menuItemTextbookRemove.setOnAction(e -> {
			RemoveTextbookPane removeTextbook = new RemoveTextbookPane(college);
			bPane.setCenter(removeTextbook.getBorderPane());
		});
		
		menuItemStudentSearch.setOnAction(e -> {
			SearchForStudentPane searchForStudent = new SearchForStudentPane(college);
			bPane.setCenter(searchForStudent.getBorderPane());
		});

		menuItemFacultySearch.setOnAction(e -> {
			SearchForFacultyPane searchForFaculty = new SearchForFacultyPane(college);
			bPane.setCenter(searchForFaculty.getBorderPane());
		});

		menuItemCourseSearch.setOnAction(e -> {
			SearchForCoursePane searchForCourse = new SearchForCoursePane(college);
			bPane.setCenter(searchForCourse.getBorderPane());
		});

		menuItemTextbookSearch.setOnAction(e -> {
			SearchForTextbookPane searchForTextbook = new SearchForTextbookPane(college);
			bPane.setCenter(searchForTextbook.getBorderPane());
		});
		
		menuItemStudentEdit.setOnAction(e -> {
			EditStudentPane editStudent = new EditStudentPane(college);
			bPane.setCenter(editStudent.getBorderPane());
		});
		
		menuItemFacultyEdit.setOnAction(e -> {
			EditFacultyPane editFaculty = new EditFacultyPane(college);
			bPane.setCenter(editFaculty.getBorderPane());
		});
		
		menuItemCourseEdit.setOnAction(e -> {
			EditCoursePane editCourse = new EditCoursePane(college);
			bPane.setCenter(editCourse.getBorderPane());
		});
		
		menuItemTextbookEdit.setOnAction(e -> {
			EditTextbookPane editTextbook = new EditTextbookPane(college);
			bPane.setCenter(editTextbook.getBorderPane());
		});
		
		bPane.setTop(menuBar);
		
		Scene scene = new Scene(bPane, 620, 750);
		primaryStage.setScene(scene);
		primaryStage.setTitle("School Directory");
		primaryStage.show();
	}

}
