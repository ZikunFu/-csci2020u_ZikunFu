package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

import javafx.scene.control.TextField;

import java.time.LocalDate;

public class Controller {
    @FXML private TextField userField;
    @FXML private TextField nameField;
    @FXML private TextField passwordField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private DatePicker datepicker;


    public void reg(ActionEvent actionEvent) {
        LocalDate date = datepicker.getValue();
        String Name = nameField.getText();
        String User = userField.getText();
        String Phone = phoneField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();

        System.out.print(User+"\n");
        System.out.print(password+"\n");
        System.out.print(Name+"\n");
        System.out.print(email+"\n");
        System.out.print(Phone+"\n");
        System.out.print( date+"\n");
    }
}
