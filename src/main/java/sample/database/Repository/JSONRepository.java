package sample.database.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import sample.database.Model.User;
import sample.utils.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JSONRepository<E> implements RepositoryInterface<E> {

    private ArrayList<E> data;

    private Class entity;
    private String entityName;
    private String entityStorage;
    private String entityPrimaryKey;
    private Type listType = new TypeToken<ArrayList<User>>(){}.getType();

    public JSONRepository(Class tClass) {

        this.data = new ArrayList<>();
        this.entity = tClass;
        this.entityName = entity.getSimpleName();
        this.entityStorage = "sample/data/"  + this.entityName + ".json";
        //this.entityPrimaryKey = $this->findPrimaryKey();

        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        FileReader file = null;
        try {
            file = new FileReader(classLoader.getResource(entityStorage).getFile());

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Echec lors de l\'ouverture du fichier" + this.entityName
                    + ".json !!");
        }

        JsonReader reader = new JsonReader(file);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<User>>(){}.getType();
        data = gson.fromJson(reader, listType); // contains the whole reviews list


    }

    public List<E> findAll() {
        return data;
    }

    public E findById(int id) {
        return null;
    }

    public List<E> findBy(String type, Object value) {

        ArrayList<E> result = new ArrayList<>();

        String methodName = "get" + StringUtils.ucfirst(type); // fieldName String
        System.out.println(methodName);

        Method m = null;
        try {
            m = entity.getMethod(methodName);
        } catch (NoSuchMethodException nsme) {
            nsme.printStackTrace();
        }

/*
        for (E one : data) {
            try {

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
*/
        return result;

    }

    public void create(List<E> entities) {

    }

    public void update(E entity, int id) {

    }

    public void delete(int id) {

    }

}
