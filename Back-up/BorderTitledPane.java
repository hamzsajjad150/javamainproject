
package GPA;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class BorderTitledPane
extends StackPane {
    BorderTitledPane(String strTitle, Node content) {
        Label lblTitle = new Label(" " + strTitle + " ");
        lblTitle.getStyleClass().add("bordered-titled-title");
        StackPane.setAlignment((Node)lblTitle, (Pos)Pos.TOP_CENTER);
        StackPane contentPane = new StackPane();
        content.getStyleClass().add("bordered-titled-content");
        contentPane.getChildren().add(content);
        this.getStyleClass().add("bordered-titled-border");
        this.getChildren().addAll(lblTitle, contentPane);
    }
}

