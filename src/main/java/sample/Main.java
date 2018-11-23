package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.utils.Configuration;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Load skeleton
        FXMLLoader fxmlLoader = new FXMLLoader();
        System.out.println(this.getClass().getResource("views"));

        Parent root = null;
        try {
            root = fxmlLoader.load(this.getClass().getResource("views/skeletonView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        primaryStage.setScene(new Scene(root, primaryStage.getWidth()- Configuration.getToolbarWidth(), primaryStage.getHeight()-Configuration.getToolbarHeight()));

        // Window preparation
        primaryStage.setWidth(Configuration.getWidth());
        primaryStage.setHeight(Configuration.getHeight());
        primaryStage.setMinWidth(Configuration.getWidth());
        primaryStage.setMinHeight(Configuration.getHeight());
        primaryStage.setTitle("Personnel manager");
        primaryStage.sizeToScene();
        primaryStage.setOnCloseRequest(e->Platform.exit());
        primaryStage.centerOnScreen();

        // Showing the window
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
