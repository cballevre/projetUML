package sample.database.Repository;

import sample.database.Model.User;

public class UserRepository extends Repository<User>{
    public UserRepository() {
        super(User.class);
    }
}
