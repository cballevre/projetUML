package sample.database.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import sample.database.Model.User;
import sample.utils.StringUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;

abstract class Repository<E> implements RepositoryInterface {

    private ArrayList<E> data;
    private Class entity;
    private String entityName;
    private String entityStorage;
    private String entityPrimaryKey;
    private Type listType = new TypeToken<ArrayList<User>>(){}.getType();

    public Repository(Class tClass) {

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
        this.data = new ArrayList<>();
        data = gson.fromJson(reader, listType); // contains the whole reviews list


    }

    public ArrayList<E> findAll() {
        return data;
    }

    public E findById(int id) {
        return null;
    }

    public ArrayList<E> findBy(String type, Object value) {
        ArrayList<E> result = new ArrayList<>();

        String methodName = "get" + StringUtils.ucfirst(type); // fieldName String
        System.out.println(methodName);

        Method m = null;
        /* try {
            //m = this.e.getMethod(methodName);
        } catch (NoSuchMethodException nsme) {
            nsme.printStackTrace();
        }*/
        return null;
    }



}
