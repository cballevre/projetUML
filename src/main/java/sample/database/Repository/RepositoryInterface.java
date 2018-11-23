package sample.database.Repository;

import java.util.List;

public interface RepositoryInterface<E> {

    public List<E> findAll();

    public E findById(int id);

    public List<E> findBy(String type, Object value);

    public void create(List<E> entities);

    public void update(E entity, int id);

    public void delete(int id);

}
