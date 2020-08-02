package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Member;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class viewMembersController implements Initializable {
    @FXML private TableView<Member> tableView;
    @FXML private TableColumn<Member, String> firstNameColumn;
    @FXML private TableColumn<Member, String> lastNameColumn;
    @FXML private TableColumn<Member, String> emailColumn;
    @FXML private TableColumn<Member, LocalDate> birthdayColumn;
    @FXML private TableColumn<Member, String> genderColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("email"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Member, LocalDate>("birthday"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("gender"));

        tableView.setItems(getMember());

    }

    public ObservableList<Member> getMember(){
        ObservableList<Member> member = FXCollections.observableArrayList();
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessApp", "root", "aiden950406");
            Statement stmt = con.createStatement();
            String s = "SELECT * FROM members";
            ResultSet rs = stmt.executeQuery(s);
            while(rs.next())
            {
                member.add(new Member(rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(6), rs.getString(7)));
            }
        }
        catch(Exception se)
        {
            System.out.println("Exception: " + se.toString());
        }

        return member;
    }


    public void goBack(MouseEvent mouseEvent) throws Exception{
        Parent partnerHomeViewParent = FXMLLoader.load(getClass().getResource("/resources/partnerHome.fxml"));
        Scene partnerHomeViewScene = new Scene(partnerHomeViewParent);

        Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

        window.setScene(partnerHomeViewScene);
        window.show();
    }

}
