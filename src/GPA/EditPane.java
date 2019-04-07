
package GPA;

import javafx.geometry.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class EditPane extends VBox {

    private Label lblCourseID;    
    private Label lblCourseName;
    private Label lblProffesorName;
    private Label lblCourseCredits;

    protected TextField txtCourseId;    
    protected TextField txtCourseName;
    protected TextField txtProffesorName;
    protected TextField txtCourseCredits;
   
    protected Button btnAdd;
    protected Button btnClear;
    protected Button btnUpdate;
    protected Button btnSearch;
    protected Button btnDelete;
   
    public EditPane() {
        setPadding(new Insets(20));
       
// Upper Panel
    
    lblCourseID = new Label("Course ID");
    lblCourseName = new Label("Course");
    lblProffesorName = new Label("Proffesor");
    lblCourseCredits = new Label("Credits");
    
    txtCourseId = new TextField();
    txtCourseName = new TextField();
    txtProffesorName = new TextField();
    txtCourseCredits = new TextField();
        
    VBox pnlUpper= new VBox(30, lblCourseName, txtCourseName, 
            lblProffesorName, txtProffesorName, 
            lblCourseCredits, txtCourseCredits);   

// Middle Panel        
        
        btnAdd = new Button("_Add");
        btnAdd.setMnemonicParsing(true);
        btnAdd.setPrefSize(70, 20);
        btnUpdate = new Button("_Update");
        btnUpdate.setMnemonicParsing(true);
        btnUpdate.setPrefSize(70, 20);
        btnClear = new Button("_Clear");
        btnClear.setMnemonicParsing(true);
        btnClear.setPrefSize(70.0, 20.0);
        
        HBox pnlMiddle = new HBox(10.0, btnAdd, btnUpdate,btnClear);
        pnlMiddle.setPadding(new Insets(30.0)); 
        pnlMiddle.setAlignment(Pos.CENTER);
        
// Button Panel        
        
        btnSearch = new Button("_Search");
        btnSearch.setMnemonicParsing(true);
        btnSearch.setPrefSize(70.0, 20.0);
        btnDelete = new Button("_Delete");
        btnDelete.setMnemonicParsing(true);
        btnDelete.setPrefSize(70.0, 20.0);
        
        HBox pnlBottom = new HBox(10,btnSearch,btnDelete);
        pnlBottom.setPadding(new Insets(30));
        pnlBottom.setAlignment(Pos.CENTER);
        

        getStylesheets().add("styles.css");
        
        this.getChildren().addAll(pnlUpper,pnlMiddle,pnlBottom);
        
    }

    public void Clear() {
    }

}


