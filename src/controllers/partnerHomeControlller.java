package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class partnerHomeControlller {
    public void logOut(MouseEvent mouseEvent) throws Exception{
        Parent partnerLoginViewParent = FXMLLoader.load(getClass().getResource("/resources/partnerLogin.fxml"));
        Scene partnerLoginViewScene = new Scene(partnerLoginViewParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

        window.setScene(partnerLoginViewScene);
        window.show();
    }

    public void viewAllMembers(MouseEvent mouseEvent) throws IOException {
        Parent viewAllMembersViewParent = FXMLLoader.load(getClass().getResource("/resources/viewMembers.fxml"));
        Scene viewAllMembersViewScene = new Scene(viewAllMembersViewParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

        window.setScene(viewAllMembersViewScene);
        window.show();
    }
}
