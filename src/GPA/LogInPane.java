
package GPA;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class LogInPane
extends GridPane {
    protected Button btnWelcome;
    protected Button btnRegister;

    public LogInPane() {
        this.setHgap(10.0);
        this.setVgap(10.0);
        this.setAlignment(Pos.CENTER);
        
        Label lblUser = new Label("Username: ");
        Label lblPasswd = new Label("Password: ");
        TextField txtUser = new TextField();
        PasswordField txtPasswd = new PasswordField();
        this.btnWelcome = new Button("Login");
        this.btnRegister = new Button("Register");
        //this.add((Node)logo, 0, 0, 3, 1);
        this.add((Node)lblUser, 0, 1);
        this.add((Node)txtUser, 1, 1, 2, 1);
        this.add((Node)lblPasswd, 0, 2);
        this.add((Node)txtPasswd, 1, 2, 2, 1);
        this.add((Node)this.btnWelcome, 1, 3);
        this.add((Node)this.btnRegister, 2, 3);
        this.btnWelcome.setPrefWidth(70.0);
        this.btnRegister.setPrefWidth(70.0);
    }
}

