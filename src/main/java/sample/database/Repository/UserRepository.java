package sample.database.Repository;

import sample.database.Model.User;

public class UserRepository extends Repository<User> {

    private final String LOG_TAG = UserRepository.class.getSimpleName();

    private static UserRepository sInstance;

    public static UserRepository getInstance() {
        if(sInstance == null) {
            sInstance = new UserRepository();
        }
        return sInstance;
    }

    private UserRepository() {
        super(User.class);
    }

}
