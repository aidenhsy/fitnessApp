package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class partnerLoginController {
    public TextField adminEmail;
    public PasswordField adminPassword;
    public Label txtWrongInfo;

    public void goHome(MouseEvent mouseEvent) throws IOException {
        Parent clientLoginViewParent = FXMLLoader.load(getClass().getResource("/resources/clientLogin.fxml"));
        Scene clientLoginViewScene = new Scene(clientLoginViewParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

        window.setScene(clientLoginViewScene);
        window.show();
    }

    public void logIn(MouseEvent mouseEvent) throws IOException {
        if(adminEmail.getText().equals("admin") & adminPassword.getText().equals("123")){
            Parent partnerHomeViewParent = FXMLLoader.load(getClass().getResource("/resources/partnerHome.fxml"));
            Scene partnerHomeViewScene = new Scene(partnerHomeViewParent);

            Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

            window.setScene(partnerHomeViewScene);
            window.show();
        }
        else {
            txtWrongInfo.setText("Wrong log in info, please try again");
        }
    }

    public void reset(MouseEvent mouseEvent) {
        txtWrongInfo.setText("");
    }
}
