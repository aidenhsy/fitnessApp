package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.ZoneId;

public class createNewMemberController {

    public TextField tfFirstName;
    public TextField tfLastName;
    public TextField tfEmail;
    public TextField tfPassword;
    public DatePicker tfBirthday;
    public ToggleGroup toggleGroup;
    public RadioButton rbtnMale;
    public RadioButton rbtnFemale;

    public void signUp(MouseEvent mouseEvent) throws IOException {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessApp", "root", "aiden950406");
            java.util.Date date =
                    java.util.Date.from(tfBirthday.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date sqlDate = new Date(date.getTime());

            PreparedStatement stmt = con.prepareStatement("INSERT INTO members VALUES (null, ?, ?, ?, ?, ?, ?, 0)");
            stmt.setString(1, tfFirstName.getText());
            stmt.setString(2, tfLastName.getText());
            stmt.setString(3, tfEmail.getText());
            stmt.setString(4, tfPassword.getText());
            stmt.setDate(5, sqlDate);

            if(this.toggleGroup.getSelectedToggle().equals(this.rbtnMale))
                stmt.setString(6, "Male");
            if(this.toggleGroup.getSelectedToggle().equals(this.rbtnFemale))
                stmt.setString(6, "Female");

            int rs = stmt.executeUpdate();

        } catch (Exception e){
            System.out.println(e.toString());
        }

        Parent homeClientViewParent = FXMLLoader.load(getClass().getResource("/resources/clientLogin.fxml"));
        Scene homeClientViewScene = new Scene(homeClientViewParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

        window.setScene(homeClientViewScene);
        window.show();
    }

    public void backHome(MouseEvent mouseEvent) throws IOException {
        Parent homeClientViewParent = FXMLLoader.load(getClass().getResource("/resources/clientLogin.fxml"));
        Scene homeClientViewScene = new Scene(homeClientViewParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

        window.setScene(homeClientViewScene);
        window.show();
    }
}
