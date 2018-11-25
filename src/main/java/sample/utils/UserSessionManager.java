package sample.utils;

import sample.database.Model.User;
import sample.database.Repository.UserRepository;

import java.security.MessageDigest;
import java.util.ArrayList;

public class UserSessionManager {

    private static final String LOG_TAG = UserSessionManager.class.getSimpleName();

    // For Singleton instantiation
    private static UserSessionManager sInstance;
    private User currentUser;
    private UserRepository repository;

    public static UserSessionManager getInstance() {
        if (sInstance == null) {
            sInstance = new UserSessionManager();
        }
        return sInstance;
    }

    private UserSessionManager() {
        repository = UserRepository.getInstance();

    }

    public boolean connection(String email, String password) throws Exception {
        ArrayList<User> users = repository.findBy("email", email);
        for (User user: users) {
            if(user.getPassword().equals(sha1(password))) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public boolean isConnected() {
        if(currentUser != null) {
            return true;
        }
        return false;
    }

    public void disconnection() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return  currentUser;
    }

    private String sha1(String s) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(s.getBytes());
        byte[] bytes = md.digest();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String tmp = Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1);
            buffer.append(tmp);
        }
        return buffer.toString();
    }

}
