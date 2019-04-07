package GPA;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;

public class Main extends Application {

    private Course course;
    private CourseList courseList;
    private LogInPane loginPane;

    private Stage stage;

    private EditPane editPane;
    private CoursePane coursePane;

    private MenuBar menuBar;
    private Menu fileMenu;
    private Menu editMenu;
    private Menu helpMenu;
    private MenuItem exitItem;
    private MenuItem logOutItem;
    private MenuItem saveItem;
    private MenuItem searchItem;
    private MenuItem updateItem;
    private MenuItem delItem;
    private MenuItem aboutItem;
    private MenuItem readMeItem;

    public static void main(String[] args) {
        Main.launch((String[]) args);
    }

    @Override
    public void start(Stage primaryStage) {
        courseList = new CourseList();
        stage = primaryStage;
        Scene scene = logInScene();
        primaryStage.setTitle("GPA Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Scene mainScene() {
        setMenu();

// instance coursePane
        coursePane = new CoursePane();

//Instance EditorPane
        editPane = new EditPane();
        editPane.btnSearch.setOnAction(e -> eventsCode((ActionEvent) e));
        editPane.btnDelete.setOnAction(e -> eventsCode((ActionEvent) e));
        editPane.btnAdd.setOnAction(e -> eventsCode((ActionEvent) e));
        editPane.btnUpdate.setOnAction(e -> eventsCode((ActionEvent) e));
        editPane.btnClear.setOnAction(e -> eventsCode((ActionEvent) e));

        HBox pnlCenter = new HBox(editPane, coursePane);
        pnlCenter.getStylesheets().add("styles.css");

        BorderPane pnlMain = new BorderPane();
        pnlMain.setTop(menuBar);
        pnlMain.setCenter(pnlCenter);

        Scene scene = new Scene(pnlMain);
        //getSelectedRecord();
        return scene;
    }

    private Scene logInScene() {
        loginPane = new LogInPane();
        loginPane.btnWelcome.setOnAction(e -> eventsCode((ActionEvent) e));
        loginPane.btnRegister.setOnAction(e -> eventsCode((ActionEvent) e));
        StackPane pnlLogin = new StackPane(loginPane);
        pnlLogin.setId("login");
        Scene scene = new Scene(pnlLogin, 600, 500);
        scene.getStylesheets().add("styles.css");
        return scene;
    }

    private void setMenu() {
        menuBar = new MenuBar();
        fileMenu = new Menu("_File");
        editMenu = new Menu("_Edit");
        helpMenu = new Menu("_Help");
        saveItem = new MenuItem("Add _Record");
        logOutItem = new MenuItem("_Log Out");
        exitItem = new MenuItem("_Exit");
        searchItem = new MenuItem("_Search");
        updateItem = new MenuItem("_Update");
        delItem = new MenuItem("_Delete");
        aboutItem = new MenuItem("_About");
        readMeItem = new MenuItem("READ_ME. txt");
        saveItem.setAccelerator((KeyCombination) new KeyCodeCombination(KeyCode.R, new KeyCombination.Modifier[]{KeyCombination.SHORTCUT_DOWN}));
        logOutItem.setAccelerator((KeyCombination) new KeyCodeCombination(KeyCode.L, new KeyCombination.Modifier[]{KeyCombination.SHORTCUT_DOWN}));
        exitItem.setAccelerator((KeyCombination) new KeyCodeCombination(KeyCode.E, new KeyCombination.Modifier[]{KeyCombination.SHORTCUT_DOWN}));
        searchItem.setAccelerator((KeyCombination) new KeyCodeCombination(KeyCode.S, new KeyCombination.Modifier[]{KeyCombination.SHORTCUT_DOWN}));
        updateItem.setAccelerator((KeyCombination) new KeyCodeCombination(KeyCode.U, new KeyCombination.Modifier[]{KeyCombination.SHORTCUT_DOWN}));
        delItem.setAccelerator((KeyCombination) new KeyCodeCombination(KeyCode.D, new KeyCombination.Modifier[]{KeyCombination.SHORTCUT_DOWN}));
        aboutItem.setAccelerator((KeyCombination) new KeyCodeCombination(KeyCode.A, new KeyCombination.Modifier[]{KeyCombination.SHORTCUT_DOWN}));
        readMeItem.setAccelerator((KeyCombination) new KeyCodeCombination(KeyCode.M, new KeyCombination.Modifier[]{KeyCombination.SHORTCUT_DOWN}));
        exitItem.setOnAction(e -> eventsCode((ActionEvent) e));
        logOutItem.setOnAction(e -> eventsCode((ActionEvent) e));
        saveItem.setOnAction(e -> eventsCode((ActionEvent) e));
        searchItem.setOnAction(e -> eventsCode((ActionEvent) e));
        updateItem.setOnAction(e -> eventsCode((ActionEvent) e));
        delItem.setOnAction(e -> eventsCode((ActionEvent) e));
        aboutItem.setOnAction(e -> eventsCode((ActionEvent) e));
        readMeItem.setOnAction(e -> eventsCode((ActionEvent) e));
        SeparatorMenuItem sepItem = new SeparatorMenuItem();
        editMenu.getItems().addAll(new MenuItem[]{searchItem, updateItem, delItem});
        helpMenu.getItems().addAll(new MenuItem[]{aboutItem, readMeItem});
        fileMenu.getItems().addAll(new MenuItem[]{saveItem, sepItem, logOutItem, exitItem});
        menuBar.getMenus().add(fileMenu);
        menuBar.getMenus().add(editMenu);
        menuBar.getMenus().add(helpMenu);
        fileMenu.setMnemonicParsing(true);
        editMenu.setMnemonicParsing(true);
        searchItem.setMnemonicParsing(true);
        logOutItem.setMnemonicParsing(true);
        exitItem.setMnemonicParsing(true);
    }

    private void eventsCode(ActionEvent e) {
        Alert alert;
        Optional result;
//Welcome Button
        if (e.getSource() == loginPane.btnWelcome) {
            stage.setScene(mainScene());
            return;
        }
//exit item
        if (e.getSource() == exitItem) {
            alert = getConfirmation("Exit application?");
            result = alert.showAndWait();
            if (result.get() == ButtonType.OK);
            System.exit(0);
        }

        if (e.getSource() == editPane.btnAdd) {
            addRecord();
        }

       
        if (e.getSource() == editPane.btnClear) {
            editPane.Clear();

        }

        if (e.getSource() == editPane.btnDelete) {
            int i = coursePane.tableView.getSelectionModel().getFocusedIndex();
            course = courseList.get(i);
            System.out.println("wine = wineCollection.get(i);" + i); //////////////////////////////////testpoint
            Alert alert3 = getConfirmation("Delete record no: " + course.getCourseID() + "?");
            Optional result3 = alert3.showAndWait();
            if (result3.get() == ButtonType.OK) {
                deleteRecord(course, i);
                stage.setScene(mainScene());
            }
            return;
        }
        if (e.getSource() == aboutItem) {
            showAboutAlert();
        }
        if (e.getSource() == readMeItem) {
            Runtime rt = Runtime.getRuntime();
            File file = new File("README.txt");
            try {
                Process result3 = rt.exec("notepad " + file);
            } catch (IOException ex) {
                System.out.println("File not found" + ex.getMessage());
            }
        }
    }

    // end Event Code
    // fill  
    private void tableEvent(MouseEvent me) {
        getSelectedRecord();
    }

    private void getSelectedRecord() {
        int i = coursePane.tableView.getSelectionModel().getFocusedIndex();
        course = courseList.get(i);
        showRecord(course);
    }

    private void showRecord(Course localCourse) {
        editPane.txtCourseId.setText(localCourse.getCourseID() + "");
        editPane.txtCourseName.setText(localCourse.getCourseName());
        editPane.txtProffesorName.setText(localCourse.getProffesor());
        editPane.txtCourseCredits.setText(localCourse.getCredits() + "");
    }

//end of 
    public void noRecordFound() {
        Alert alert = getInformation("No records found");
        alert.showAndWait();
    }

    private String getInput(String input) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText(null);
        dialog.setContentText(input);
        String answer = "no answer";
        Optional result = dialog.showAndWait();
        if (result.isPresent()) {
            answer = (String) result.get();
        }
        return answer;
    }

    private Alert getInformation(String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText(info);
        return alert;
    }

    private Alert getConfirmation(String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText(text);
        return alert;
    }

    private void showAboutAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("GPA Calculator 1.0");
        alert.setContentText("Copyright \u00a9Javier Nievas");
        alert.show();
    }

    private void addRecord() {
        /*boolean valid;
        Validator val = new Validator();
        boolean bl = valid = val.isValidInteger(editPane.txtColumns[3].getText()) && val.isValidDouble(editPane.txtColumns[4].getText()) && val.isValidInteger(editPane.txtColumns[5].getText());
        
        if (!valid) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Invalid entry");
            alert.show();
        } else {*/

        course = new Course();
        course.setCourseID(courseList.size());
        System.out.println(editPane.txtCourseCredits.getText());
        course.setCourseName(editPane.txtCourseName.getText());
        course.setProffesor(editPane.txtProffesorName.getText());
        course.setCredits(Integer.getInteger(editPane.txtCourseCredits.getText()));

        courseList.add(course);
        courseList.writeRecord(course);
    }

    private void deleteRecord(Course w, int index) {
        courseList.remove(w);
        courseList.writePartialCollection(index);
    }

}
