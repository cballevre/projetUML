package sample.database.Repository;

import sample.database.Model.User;

import java.util.List;

public class UserRepository extends Repository<User>{

    public UserRepository() {
        super(User.class);
    }


    @Override
    public void create(List entities) {

    }

    @Override
    public void update(Object entity, int id) {

    }

    @Override
    public void delete(int id) {

    }
}
