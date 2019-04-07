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
import javafx.scene.layout.VBox;

public class CoursePane extends VBox {
    
    protected TableView tableView;
    private final String[] colNames = {"ID", "Course Name", "Professor", "Credits", "GPA", "Alpha"};
    private final String[] colVars = {"CourseID", "CourseName", "Grape", "Year", "Price", "Quantity"};
    private final TableColumn[] tableColumn;

    public CoursePane() {
        
        CourseList courseLists = new CourseList();
 
        
        tableView = new TableView();///1 Creates table
        //tableView.getFixedCellSize();
        tableView.setMaxWidth(650);
        
        
        tableColumn = new TableColumn[6];
        for (int i = 0; i < 6; ++i) {
            tableColumn[i] = new TableColumn(colNames[i]);//2 point Names column
            tableColumn[i].setPrefWidth(90);
            tableColumn[i].setCellValueFactory(new PropertyValueFactory(colVars[i]));//3 Search?????
            tableView.getColumns().add(tableColumn[i]);// 4 add columns
        }
        
        ObservableList lstWine = FXCollections.observableArrayList(courseLists);
        tableView.setItems(lstWine);
        
        getChildren().addAll(tableView);
        setPadding(new Insets(30.0, 10.0, 20.0, 20.0));
    }


}

