package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import persistence.Book;
import persistence.dao.JPABookDao;
import services.BookService;

import java.util.List;

@RequestMapping ("/api/books")
public class restBookController {

    private BookService bookService;
    private JPABookDao jpaBookDao;

    public BookService getBookService() {
        return bookService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public JPABookDao getJpaBookDao() {
        return jpaBookDao;
    }

    @Autowired
    public void setJpaBookDao(JPABookDao jpaBookDao) {
        this.jpaBookDao = jpaBookDao;
    }

    @RequestMapping(method = RequestMethod.GET ,path= {"/", ""})
    public ResponseEntity <List <Book>> getAllbooks (){

        List<Book> books= jpaBookDao.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path="/{id}")
    public ResponseEntity <Book> getAbook (@PathVariable Integer id){
        Book book= jpaBookDao.findById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""} )
    public ResponseEntity <Book> addAbook (@RequestBody Book book){
        bookService.addAnewBook(book);

        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/{id}"} )
    public ResponseEntity <Book> updateAbook ( @PathVariable Integer id, @RequestBody Book book){
        bookService.updateBook(id,book);

        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/{id}"} )
    public ResponseEntity <Book> deleteAbook (@PathVariable Integer id){
        if(jpaBookDao.findById(id)==null){
            throw new IllegalArgumentException("this book doesn't exist");
        }

        bookService.deleteAbook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }






}

/*GET /api/books - Retrieve a list of all books.
            ● GET /api/books/{id} - Retrieve details of a specific book by ID.
            ● POST /api/books - Add a new book.
● PUT /api/books/{id} - Update an existing book by ID.
            ● DELETE /api/books/{id} - Delete a book by ID.*/
