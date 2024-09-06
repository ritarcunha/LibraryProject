package persistence.dao;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

public class JPAInventoryDao implements Dao {

    private EntityManager entityManager;


    @Override
    public List findAll() {
        return Collections.emptyList();
    }

    @Override
    public Object findById(Integer id) {
        return null;
    }

    @Override
    public Object saveOrUpdate(Object modelObject) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }



    /*GET /api/books - Retrieve a list of all books.
            ● GET /api/books/{id} - Retrieve details of a specific book by ID.
            ● POST /api/books - Add a new book.
● PUT /api/books/{id} - Update an existing book by ID.
            ● DELETE /api/books/{id} - Delete a book by ID.*/

}
