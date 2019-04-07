package GPA;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private Wine wine;
    private WineCollection wineCollection;
    private ArrayList<Wine> arratListofWine;
    private LogInPane loginPane;
    private ViewPane viewPane;
    private Stage stage;

    private EditPane editPane;

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
        wineCollection = new WineCollection();
        stage = primaryStage;
        Scene scene = logInScene();
        primaryStage.setTitle("GPA Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Scene mainScene() {
        setMenu();

        viewPane = new ViewPane();
        viewPane.tableView.setOnMouseClicked(me -> tableEvent((MouseEvent) me));
        viewPane.btnExit.setOnAction(e -> eventsCode((ActionEvent) e));
        viewPane.btnSearch.setOnAction(e -> eventsCode((ActionEvent) e));
        viewPane.btnDelete.setOnAction(e -> eventsCode((ActionEvent) e));

        editPane = new EditPane();
        editPane.btnSave.setOnAction(e -> eventsCode((ActionEvent) e));
        editPane.btnUpdate.setOnAction(e -> eventsCode((ActionEvent) e));
        editPane.btnReset.setOnAction(e -> eventsCode((ActionEvent) e));

        HBox pnlCenter = new HBox(editPane, viewPane);
        pnlCenter.getStylesheets().add("styles.css");

        BorderPane pnlMain = new BorderPane();
        pnlMain.setTop(menuBar);
        pnlMain.setCenter(pnlCenter);

        Scene scene = new Scene(pnlMain);
        getSelectedRecord();
        return scene;
    }

    private Scene logInScene() {
        loginPane = new LogInPane();
        loginPane.btnLogin.setOnAction(e -> eventsCode((ActionEvent) e));
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

        if (e.getSource() == loginPane.btnLogin) {
            stage.setScene(mainScene());
            return;
        }
        if (e.getSource() == logOutItem) {
            stage.setScene(logInScene());
            return;
        }
        if ((e.getSource() == exitItem || e.getSource() == viewPane.btnExit) && (result = (alert = getConfirmation("Exit application?")).showAndWait()).get() == ButtonType.OK) {
            System.exit(0);
        }
        if (e.getSource() == editPane.btnSave || e.getSource() == saveItem) {
            alert = getConfirmation("Save record new record?");
            result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Validator valid = new Validator();
                if (valid.isValidInteger(editPane.txtColumns[3].getText()) && valid.isValidDouble(editPane.txtColumns[4].getText()) && valid.isValidInteger(editPane.txtColumns[5].getText())) {
                    addRecord();
                } else {
                    Alert invalidInt = getInformation("Invalid entry!");
                    invalidInt.showAndWait();
                }
            }
            stage.setScene(mainScene());
            return;
        }
        if (e.getSource() == editPane.btnUpdate || e.getSource() == updateItem) {
            Validator valid = new Validator();
            int i = viewPane.tableView.getSelectionModel().getFocusedIndex();
            Alert alert2 = getConfirmation("Update record no: " + wine.getCourseID() + "?");
            Optional result2 = alert2.showAndWait();
            if (result2.get() == ButtonType.OK) {
                wine = (Wine) wineCollection.get(i);
                wine.setCourseID(Integer.parseInt(editPane.txtColumns[0].getText()));
                wine.setCourseName(wineCollection.prepField(editPane.txtColumns[1].getText(), wineCollection.FIELD_SIZE));
                wine.setGrape(wineCollection.prepField(editPane.txtColumns[2].getText(), wineCollection.FIELD_SIZE));
                if (valid.isValidInteger(editPane.txtColumns[3].getText()) && valid.isValidDouble(editPane.txtColumns[4].getText()) && valid.isValidInteger(editPane.txtColumns[5].getText())) {
                    wine.setYear(Integer.parseInt(editPane.txtColumns[3].getText()));
                    wine.setPrice(Double.parseDouble(editPane.txtColumns[4].getText()));
                    wine.setQuantity(Integer.parseInt(editPane.txtColumns[5].getText()));
                } else {
                    Alert invalidInt = getInformation("Invalid entry!");
                    invalidInt.showAndWait();
                }
                try {
                    wineCollection.raf.seek((long) i * wineCollection.RECORD_SIZE);
                    wineCollection.raf.writeInt(wine.getCourseID());
                    wineCollection.raf.writeChars(wine.getCourseName());
                    wineCollection.raf.writeChars(wine.getGrape());
                    wineCollection.raf.writeInt(wine.getYear());
                    wineCollection.raf.writeDouble(wine.getPrice());
                    wineCollection.raf.writeInt(wine.getQuantity());
                } catch (Exception ex) {
                    System.out.println("Error update: " + ex.getMessage());
                }
                stage.setScene(mainScene());
            }
            return;
        }
        if (e.getSource() == editPane.btnReset) {
            editPane.resetFields();
            return;
        }
        if (e.getSource() == viewPane.btnSearch || e.getSource() == searchItem) {
            getSearchChoice();
        }
        if (e.getSource() == viewPane.btnDelete || e.getSource() == delItem) {
            int i = viewPane.tableView.getSelectionModel().getFocusedIndex();
            wine = wineCollection.get(i);
            System.out.println("wine = wineCollection.get(i);" + i); //////////////////////////////////testpoint
            Alert alert3 = getConfirmation("Delete record no: " + wine.getCourseID() + "?");
            Optional result3 = alert3.showAndWait();
            if (result3.get() == ButtonType.OK) {
                deleteRecord(wine, i);
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
    private void getSelectedRecord() {
        int i = viewPane.tableView.getSelectionModel().getFocusedIndex();
        wine = wineCollection.get(i);
        showRecord(wine);
    }

    private void showRecord(Wine w) {
        editPane.txtColumns[0].setText(w.getCourseID() + "");
        editPane.txtColumns[1].setText(w.getCourseName());
        editPane.txtColumns[2].setText(w.getGrape());
        editPane.txtColumns[3].setText(w.getYear() + "");
        editPane.txtColumns[4].setText(w.getPrice() + "");
        editPane.txtColumns[5].setText(w.getQuantity() + "");
    }

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
        alert.setHeaderText("MyWineCollection ver. 1.0");
        alert.setContentText("Copyright \u00a9Haxhi Dvorani");
        alert.show();
    }

    private void tableEvent(MouseEvent me) {
        getSelectedRecord();
    }

    private void addRecord() {
        boolean valid;
        Validator val = new Validator();
        boolean bl = valid = val.isValidInteger(editPane.txtColumns[3].getText()) && val.isValidDouble(editPane.txtColumns[4].getText()) && val.isValidInteger(editPane.txtColumns[5].getText());
        if (!valid) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Invalid entry");
            alert.show();
        } else {
            wine = new Wine();
            wine.setCourseID(wineCollection.size());
            wine.setCourseName(editPane.txtColumns[1].getText());
            wine.setGrape(editPane.txtColumns[2].getText());
            wine.setYear(Integer.parseInt(editPane.txtColumns[3].getText()));
            wine.setPrice(Double.parseDouble(editPane.txtColumns[4].getText()));
            wine.setQuantity(Integer.parseInt(editPane.txtColumns[5].getText()));
            wineCollection.add(wine);
            wineCollection.writeRecord(wine);
        }
    }

    private void deleteRecord(Wine w, int index) {
        wineCollection.remove(w);
        wineCollection.writePartialCollection(index);
    }

    //Trash 
    private void getSearchChoice() {
        ArrayList<String> choices = new ArrayList<>();
        choices.add("Wine ID");
        choices.add("Grape");
        choices.add("Year");
        choices.add("Price");
        ChoiceDialog dialog = new ChoiceDialog("Select...", choices);
        dialog.setTitle("Selection Criteria");
        dialog.setHeaderText("You can search for wines beased on different criteria");
        dialog.setContentText("Select the searching creteria: Wine ID, Estate, Year or Price");
        Optional result = dialog.showAndWait();
        if (result.isPresent()) {
            if (((String) result.get()).equals(choices.get(0))) {
                String sId = getInput("Enter Wine ID");
                Validator valid = new Validator(sId);
                if (valid.isValidInteger()) {
                    int id = Integer.parseInt(sId);
                    boolean found = false;
                    for (Wine w : wineCollection) {
                        if (w.getCourseID() != id) {
                            continue;
                        }
                        showRecord(w);
                        found = true;
                    }
                    if (!found) {
                        noRecordFound();
                    }
                } else {
                    Alert alert = getInformation("Wine ID mut be an integer");
                    alert.showAndWait();
                }
            }
            if (((String) result.get()).equals(choices.get(1))) {
                String estate = getInput("Enter Grape: ");
                arratListofWine = new ArrayList();
                int num = 0;
                for (Wine w : wineCollection) {
                    if (!w.getGrape().trim().equalsIgnoreCase(estate)) {
                        continue;
                    }
                    num += w.getQuantity();
                    arratListofWine.add(w);
                }
                search(num);
            }
            if (((String) result.get()).equals(choices.get(2))) {
                int year = 0;
                try {
                    year = Integer.parseInt(getInput("Enter the Year : "));
                } catch (NumberFormatException e) {
                    Alert invalidInt = getInformation("Error: Invalid value for year");
                    invalidInt.showAndWait();
                    return;
                }
                arratListofWine = new ArrayList();
                int num = 0;
                for (Wine w : wineCollection) {
                    if (w.getYear() != year) {
                        continue;
                    }
                    num += w.getQuantity();
                    arratListofWine.add(w);
                }
                search(num);
            }
            if (((String) result.get()).equals(choices.get(3))) {
                double price = 0.0;
                try {
                    price = Double.parseDouble(getInput("Enter the price : "));
                } catch (NumberFormatException e) {
                    Alert invalidInt = getInformation("Error: Invalid value for price");
                    invalidInt.showAndWait();
                    return;
                }
                arratListofWine = new ArrayList();
                int num = 0;
                for (Wine w : wineCollection) {
                    if (w.getPrice() != price) {
                        continue;
                    }
                    num += w.getQuantity();
                    arratListofWine.add(w);
                }
                search(num);
            }
        }
    }

    public void search(int num) {
        Stage searchStage = new Stage();
        searchStage.setTitle("Search Result");
        if (num == 0) {
            noRecordFound();
        } else {
            SearchPane pane = new SearchPane(arratListofWine);
            searchStage.setScene(new Scene((Parent) pane));
            searchStage.show();
            pane.btnClose.setOnAction(event -> searchStage.close());
        }
    }

}
