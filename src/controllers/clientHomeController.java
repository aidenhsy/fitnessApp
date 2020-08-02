package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class clientHomeController {
    public void goBack(MouseEvent mouseEvent) throws IOException {
        Parent clientLoginViewParent = FXMLLoader.load(getClass().getResource("/resources/clientLogin.fxml"));
        Scene clientLoginViewScene = new Scene(clientLoginViewParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

        window.setScene(clientLoginViewScene);
        window.show();
    }
}
