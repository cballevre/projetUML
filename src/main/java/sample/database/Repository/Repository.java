package sample.database.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import sample.database.Model.User;
import sample.utils.StringUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

abstract class Repository<E> {

    private final String LOG_TAG = Repository.class.getSimpleName();

    private ArrayList<E> data;
    private Class entity;
    private String entityName;
    private String entityStorage;
    private String entityPrimaryKey;
    private Type listType = new TypeToken<ArrayList<User>>(){}.getType();

    Repository(Class tClass) {

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
        Method m = null;

        try {
            m = entity.getMethod(methodName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        for (E item: data) {
            Object currentValue = null;
            try {
                currentValue = m.invoke(item);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            if(value.equals(currentValue)) {
                result.add(item);
            }
        }

        return result;
    }

    public void create(ArrayList<E> entities) {

    }

    public void create(E entity) {

    }

    public void update(Object entity, int id) {

    }

    public void delete(int id) {

    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("c'est fini pour moi veuillez m'enregistrer avant de me supprimer");
        super.finalize();

    }
}
