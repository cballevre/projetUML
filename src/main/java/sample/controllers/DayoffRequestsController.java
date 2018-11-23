package sample.controllers;

import javafx.fxml.Initializable;
import sample.database.Model.User;
import sample.database.Repository.RepositoryFactory;
import sample.database.Repository.RepositoryInterface;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DayoffRequestsController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RepositoryInterface<User> repository = RepositoryFactory.getRepository(User.class);
        ArrayList<User> test = (ArrayList<User>) repository.findBy("firstname", "Célestin");
    }
}
