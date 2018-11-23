package sample.database.Repository;

import sample.database.Model.User;

import java.util.HashMap;

public class RepositoryFactory {

    private final String LOG_TAG = RepositoryFactory.class.getSimpleName();
    public static HashMap<String, RepositoryInterface> instances = new HashMap<>();

    public static RepositoryInterface getRepository(Class classe)
    {
        if(instances.get(classe.getSimpleName()) == null) {
            instances.put(classe.getSimpleName(), createRepository(classe));
        }
        return instances.get(classe.getSimpleName()); 
    }

    private static RepositoryInterface createRepository(Class tClass) {

        switch (tClass.getSimpleName()) {
            case "User":
                return new UserRepository() {
                };
        }



        return null;
    }
}
