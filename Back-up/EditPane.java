
package GPA;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class EditPane extends GridPane {
    private String[] colNames = {"ID", "Curse Name", "Proffesor", "Credits","GPA","Alpha"};
    private Label[] lblColumns;
    protected TextField[] txtColumns;
    protected Button btnSave;
    protected Button btnReset;
    protected Button btnUpdate;

    public EditPane() {
        setPadding(new Insets(20.0, 10.0, 20.0, 20.0));
        setHgap(20.0);
        setVgap(10.0);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.RIGHT);
        getColumnConstraints().add(column1);
        
        lblColumns = new Label[6];
        txtColumns = new TextField[6];
        
        for (int i = 0; i < 6; ++i) {
            lblColumns[i] = new Label(colNames[i]);
            txtColumns[i] = new TextField();
            add((Node)lblColumns[i], 0, i + 2);
            add((Node)txtColumns[i], 1, i + 2);
        }
        txtColumns[0].setEditable(false);
        txtColumns[0].setOpacity(0.5);
        
        HBox pnlButtons = new HBox(10.0);
        pnlButtons.setPadding(new Insets(30.0));
        
        btnSave = new Button("_Save");
        btnSave.setMnemonicParsing(true);
        btnSave.setPrefSize(70.0, 20.0);
        btnUpdate = new Button("_Update");
        btnUpdate.setMnemonicParsing(true);
        btnUpdate.setPrefSize(70.0, 20.0);
        btnReset = new Button("_Reset");
        btnReset.setMnemonicParsing(true);
        btnReset.setPrefSize(70.0, 20.0);
        pnlButtons.getChildren().addAll(btnSave, btnUpdate, btnReset);
        GridPane.setColumnSpan((Node)pnlButtons, (Integer)3);
        pnlButtons.setAlignment(Pos.CENTER_RIGHT);
        add((Node)pnlButtons, 0, 9);
    }

    public void resetFields() {
        for (int i = 0; i < 6; ++i) {
            txtColumns[i].clear();
        }
    }
}

