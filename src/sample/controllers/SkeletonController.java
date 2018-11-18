package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.utils.ViewsEnum;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SkeletonController implements Initializable {
    @FXML private HBox navOption1, navOption2, navOption3, navOption4, navOption5, menuOption1, menuOption2;
    @FXML private Label sectionTitleLbl;
    @FXML private VBox body, navBanner;

    private HashMap<ViewsEnum, String> fxmlList;
    private int selectedMenuOption;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxmlList = new HashMap<>();
        fxmlList.put(ViewsEnum.DAYOFF_REQUESTS, "dayoffRequestsView");
        fxmlList.put(ViewsEnum.PLANNING, "planningView");
        fxmlList.put(ViewsEnum.REPORT, "reportsView");
        fxmlList.put(ViewsEnum.SETTINGS, "settingsView");
        fxmlList.put(ViewsEnum.VALIDATE, "validateView");

        // Set default options
        selectedMenuOption = 0;
        sectionTitleLbl.setText("Demandes de congés");
        fxml2Node(ViewsEnum.DAYOFF_REQUESTS, navOption1);

        // Set events
        navOption1.setOnMouseClicked(e -> {
            sectionTitleLbl.setText("Demandes de congés");
            fxml2Node(ViewsEnum.DAYOFF_REQUESTS, navOption1);
        });

        navOption2.setOnMouseClicked(e -> {
            sectionTitleLbl.setText("Planning");
            fxml2Node(ViewsEnum.PLANNING, navOption2);
        });

        navOption3.setOnMouseClicked(e -> {
            sectionTitleLbl.setText("Validations");
            fxml2Node(ViewsEnum.VALIDATE, navOption3);
        });

        navOption4.setOnMouseClicked(e -> {
            sectionTitleLbl.setText("Rapports");
            fxml2Node(ViewsEnum.REPORT, navOption4);
        });

        navOption5.setOnMouseClicked(e -> {
            sectionTitleLbl.setText("Paramètres");
            fxml2Node(ViewsEnum.SETTINGS, navOption5);
        });

        menuOption1.setOnMouseClicked(e -> {
            System.out.println("TODO : directory");
        }); //TODO : choice the display mode of this option (into the window or in a popup)

        menuOption2.setOnMouseClicked(e -> {
            System.out.println("TODO : User's options");
        }); //TODO : choice the display mode of this option (into the window or in a popup)
    }

    private void fxml2Node(ViewsEnum fxml, Node node) {
        // Unselect the previous category and select the right one
        navBanner.getChildren().get(selectedMenuOption).getStyleClass().remove("selected_option");
        int newIndex = navBanner.getChildren().indexOf(node);
        selectedMenuOption = (newIndex != -1) ? newIndex : selectedMenuOption;
        navBanner.getChildren().get(selectedMenuOption).getStyleClass().add("selected_option");
        // Clean body
        Node tmp[] = new Node[2];
        tmp[0] = body.getChildren().get(0);
        tmp[1] = body.getChildren().get(1);
        body.getChildren().clear();
        body.getChildren().addAll(tmp[0], tmp[1]);
        // Load new content
        FXMLLoader fxmlLoader = new FXMLLoader();
        Node root = null;
        try {
            root = fxmlLoader.load(sample.Main.class.getResource("views/"+fxmlList.get(fxml)+".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Add new node
        body.getChildren().add(root);
    }
}
