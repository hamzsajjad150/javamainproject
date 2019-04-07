
package GPA;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;


public class SearchPane
extends BorderPane {
    protected TableView tblSearchView = new TableView();
    protected Button btnClose;
    private final String[] colNames = {"Evaluation", "Weight", "Grade", "Weight", "Alpha", "Partial Mark"};
    private TableColumn[] colHeaders;

    public SearchPane(ArrayList<Wine> wines) {
        
        ObservableList lstWine = FXCollections.observableArrayList(wines);
        
        
        tblSearchView.getFixedCellSize();
        tblSearchView.setMaxWidth(650.0);
        tblSearchView.setItems(lstWine);
        
        
        colHeaders = new TableColumn[6];
        
        
        for (int i = 0; i < 6; ++i) {
            colHeaders[i] = new TableColumn(colNames[i]);
            colHeaders[i].setPrefWidth(110.0);
            tblSearchView.getColumns().add(colHeaders[i]);
            colHeaders[i].setCellValueFactory(new PropertyValueFactory(colNames[i]));
        }
        btnClose = new Button("_Close");
        btnClose.setPrefSize(70.0, 20.0);
        setPadding(new Insets(15.0));
        BorderPane.setAlignment((Node)btnClose, (Pos)Pos.CENTER);
        BorderPane.setMargin((Node)btnClose, (Insets)new Insets(15.0));
        setCenter((Node)tblSearchView);
        setBottom((Node)btnClose);
        getStylesheets().add("styles.css");
    }
}

