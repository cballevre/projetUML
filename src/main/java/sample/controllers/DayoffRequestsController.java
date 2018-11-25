package sample.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DayoffRequestsController implements Initializable {

    @FXML DatePicker beginDateField;
    @FXML
    ChoiceBox<String> beginDayMoment;

    @FXML DatePicker endDateField;
    @FXML ChoiceBox endDayMoment;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        beginDateField = new DatePicker(LocalDate.now());

        beginDayMoment.setItems(FXCollections.observableArrayList("Matin", "Soir"));
        endDayMoment.setItems(FXCollections.observableArrayList("Matin","Soir"));

    }
}
