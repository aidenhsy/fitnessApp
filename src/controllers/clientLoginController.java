package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class clientLoginController {
    public TextField tfEmail;
    public PasswordField tfPassword;

    public void createNewAccount(MouseEvent mouseEvent) throws IOException {
        Parent createNewMemberViewParent = FXMLLoader.load(getClass().getResource("/resources/createNewMember.fxml"));
        Scene createNewMemberViewScene = new Scene(createNewMemberViewParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

        window.setScene(createNewMemberViewScene);
        window.show();
    }

    public void partnerLogin(MouseEvent mouseEvent) throws IOException{
        Parent partnerLoginViewParent = FXMLLoader.load(getClass().getResource("/resources/partnerLogin.fxml"));
        Scene partnerLoginViewScene = new Scene(partnerLoginViewParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

        window.setScene(partnerLoginViewScene);
        window.show();
    }

    public void logIn(MouseEvent mouseEvent) {
        System.out.println(tfEmail.getText());
        System.out.println(tfPassword.getText());
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessApp", "root", "aiden950406");
            Statement stmt = con.createStatement();
            String s = "SELECT password FROM members WHERE email= '" + tfEmail.getText() + "'";
            ResultSet rs = stmt.executeQuery(s);
            while(rs.next()) {
                if(rs.getString(1).equals(tfPassword.getText())){
                    Parent clientHomeViewParent = FXMLLoader.load(getClass().getResource("/resources/clientHome.fxml"));
                    Scene clientHomeViewScene = new Scene(clientHomeViewParent);

                    Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

                    window.setScene(clientHomeViewScene);
                    window.show();
                }
                else {
                    System.out.println("failed");
                    System.out.println(tfPassword.getText());
                }
            }
        }
        catch(Exception se)
        {
            System.out.println("Exception: " + se.toString());
        }
    }

    public void passwordReset(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/resources/passwordReset.fxml"));
        Scene scene = new Scene(parent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
}
