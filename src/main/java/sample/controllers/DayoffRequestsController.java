package sample.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class DayoffRequestsController implements Initializable {

    @FXML DatePicker beginDateField, endDateField;
    @FXML ChoiceBox beginDayMoment, endDayMoment;

    @FXML ChoiceBox typeField;
    @FXML Button submitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        beginDateField.setValue(LocalDate.now());
        endDateField.setValue(LocalDate.now());
        endDateField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(endDateField.getValue().isBefore(beginDateField.getValue()));

                if(endDateField.getValue().isBefore(beginDateField.getValue()) ) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Erreur de date");
                    alert.setHeaderText("Erreur de date");
                    alert.setContentText("Votre date de fin ne peut pas être inférieur à la date de début");
                    alert.showAndWait();
                    endDateField.setValue(LocalDate.now());
                }
            }
        });

        beginDayMoment.setItems(FXCollections.observableArrayList("Matin", "Soir"));
        beginDayMoment.getSelectionModel().selectFirst();

        endDayMoment.setItems(FXCollections.observableArrayList("Matin", "Soir"));
        endDayMoment.getSelectionModel().selectFirst();

        typeField.setItems(FXCollections.observableArrayList("Congés", "Arrêt Maladie"));
        typeField.getSelectionModel().selectFirst();

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmer la demande de congés");
                alert.setHeaderText("Confirmer la demande de congés");
                System.out.println(beginDateField.getValue());
                System.out.println(beginDayMoment.getValue());
                System.out.println(endDayMoment.getValue());
                System.out.println(endDateField.getValue());
                alert.setContentText("Are you ok with this?");
                alert.show();
            }
        });



    }
}
