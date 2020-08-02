package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class passwordResetController {


    public TextField tfEmail;
    public TextField tfNewPassword;
    public Hyperlink hlCreateNewMember;

    public void goBack(MouseEvent mouseEvent) throws IOException {
        Parent clientLoginViewParent = FXMLLoader.load(getClass().getResource("/resources/clientLogin.fxml"));
        Scene clientLoginViewScene = new Scene(clientLoginViewParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

        window.setScene(clientLoginViewScene);
        window.show();
    }

    public void resetPassword(MouseEvent mouseEvent) {
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessApp", "root", "aiden950406");
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement();
            String s = "SELECT password FROM members WHERE email= '" + tfEmail.getText() + "'";
            ResultSet rs = stmt.executeQuery(s);
            String s1 = "UPDATE members SET password = '" + tfNewPassword.getText() + "' WHERE email ='" + tfEmail.getText() + "'";
            while(rs.next()) {
                if(!rs.getString(1).isEmpty()){
                    int rs1 = stmt1.executeUpdate(s1);
                    Parent clientLoginViewParent = FXMLLoader.load(getClass().getResource("/resources/clientLogin.fxml"));
                    Scene clientLoginViewScene = new Scene(clientLoginViewParent);

                    Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

                    window.setScene(clientLoginViewScene);
                    window.show();
                }
                //this feature is not working
                else {
                    hlCreateNewMember.setVisible(true);
                }
            }

        }
        catch(Exception se) {
            System.out.println("Exception: " + se.toString());
        }
    }

    public void createNewAccount(MouseEvent mouseEvent) throws Exception {
        Parent createNewMemberViewParent = FXMLLoader.load(getClass().getResource("/resources/createNewMember.fxml"));
        Scene createNewMemberViewScene = new Scene(createNewMemberViewParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

        window.setScene(createNewMemberViewScene);
        window.show();
    }

    public void hideLink(MouseEvent mouseEvent) {
        hlCreateNewMember.setVisible(false);
    }
}
