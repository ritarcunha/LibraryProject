package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import persistence.Book;
import persistence.dao.JPABookDao;

import java.util.List;

@Service
public class BookService {

    private JPABookDao jpaBookDao;

    public JPABookDao getJpaBookDao() {
        return jpaBookDao;
    }

    @Autowired
    public void setJpaBookDao(JPABookDao jpaBookDao) {
        this.jpaBookDao = jpaBookDao;
    }

    public List<Book> allBooks () {
        return jpaBookDao.findAll();
    }

    public Book findBook (Integer id) {
        return jpaBookDao.findById(id);
    }




    @Transactional
    public void addAnewBook (Book book) {

       Book bookTest = jpaBookDao.findByIsbn(book.getIsnb());
       //this result it could be null, if there is no book with that isbn, or it is a book that already exists
        //return result.isEmpty() ? null : result.get(0);

        if(bookTest!=null){
           throw new IllegalArgumentException("this book already exists");
       }

        jpaBookDao.saveOrUpdate(book);
    }

    @Transactional
    public void updateBook (Integer id, Book updatedBook) {
        Book book = jpaBookDao.findById(id);

        if(book==null){
            throw new IllegalArgumentException("This book doesn't exist");
        }

            book.setAuthor(updatedBook.getAuthor());
            book.setPrice(updatedBook.getPrice());
            book.setIsnb(updatedBook.getIsnb());
            book.setTitle(updatedBook.getTitle());
            book.setPublishedDate(updatedBook.getPublishedDate());
            //vai receber um id, com esse id vamos alterar algo num book ja existente

        jpaBookDao.saveOrUpdate(book);

    }

    @Transactional
    public void deleteAbook (Integer id) {
        Book book = jpaBookDao.findById(id);//if the book exists it will be a not null object

        if(book==null){
            throw new IllegalArgumentException("This book doesn't exist");
        }
        jpaBookDao.delete(id);
    }






    //vai ter de ter os daos
//com os metodos que os daos tem para aceder a base de dados

/*GET /api/books - Retrieve a list of all books.
            ● GET /api/books/{id} - Retrieve details of a specific book by ID.
            ● POST /api/books - Add a new book.
● PUT /api/books/{id} - Update an existing book by ID.
            ● DELETE /api/books/{id} - Delete a book by ID.*/
}

