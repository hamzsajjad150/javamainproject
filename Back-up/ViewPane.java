package GPA;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ViewPane
extends BorderPane {
    protected TableView tableView;
    protected Button btnSearch;
    protected Button btnExit;
    protected Button btnDelete;
    private final String[] colNames = {"CourseID", "Course Name", "Professor", "Credits", "GPA", "Alpha"};
    private final String[] colVars = {"CourseID", "CourseName", "Grape", "Year", "Price", "Quantity"};
    
    private final TableColumn[] tableColumn;

    public ViewPane() {
        WineCollection myCollection = new WineCollection();
 
        tableView = new TableView();///1 Creates table
        tableView.getFixedCellSize();
        tableView.setMaxWidth(650);
        
        tableColumn = new TableColumn[6];
        for (int i = 0; i < 6; ++i) {
            tableColumn[i] = new TableColumn(colNames[i]);//2 point Names column
            tableColumn[i].setPrefWidth(90);
            tableColumn[i].setCellValueFactory(new PropertyValueFactory(colVars[i]));//3 Search?????
            tableView.getColumns().add(tableColumn[i]);// 4 add columns
        }
        
        ObservableList lstWine = FXCollections.observableArrayList(myCollection);
        tableView.setItems(lstWine);
        
        this.btnSearch = new Button("_Search");
        this.btnExit = new Button("_Exit");
        this.btnDelete = new Button("_Delete");
        
        HBox pnlButtons = new HBox(10.0);
        pnlButtons.setPadding(new Insets(20));
        pnlButtons.getChildren().addAll(btnSearch, btnDelete, btnExit);
        pnlButtons.setAlignment(Pos.CENTER);
        
        setSizes();
        setCenter(tableView);
        setBottom(pnlButtons);
        getStylesheets().add("styles.css");
        
        setPadding(new Insets(30.0, 10.0, 20.0, 20.0));
    }

    private void setSizes() {
        btnSearch.setPrefSize(70.0, 20.0);
        btnExit.setPrefSize(70.0, 20.0);
        btnDelete.setPrefSize(70.0, 20.0);
    }
}

