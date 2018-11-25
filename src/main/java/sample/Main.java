package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.utils.Configuration;
import sample.utils.FXRouter;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXRouter.bind(this, primaryStage, "Reliance", Configuration.getWidth(),  Configuration.getHeight());
        FXRouter.when("login", "views/loginView.fxml");
        FXRouter.when("skeleton", "views/skeletonView.fxml");
        FXRouter.goTo("login");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
