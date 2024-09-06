

import persistence.Book;
import persistence.dao.JPABookDao;
import services.BookService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Configurar EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookPU");
        EntityManager em = emf.createEntityManager();

        // Instanciar JPABookDao e injetar o EntityManager
        JPABookDao bookDao = new JPABookDao();
        bookDao.setEm(em);

        // Instanciar BookService e injetar o JPABookDao
        BookService bookService = new BookService();
        bookService.setJpaBookDao(bookDao);

        // Adicionar um novo livro
        Book newBook = new Book();
        newBook.setTitle("O Senhor dos Anéis");
        newBook.setAuthor("J.R.R. Tolkien");
        newBook.setIsnb(123456789);
        newBook.setPublishedDate(1954);
        newBook.setPrice(25);
        bookService.addAnewBook(newBook);

        // Recuperar todos os livros
        List<Book> books = bookService.allBooks();
        for (Book book : books) {
            System.out.println("Livro: " + book.getTitle() + " de " + book.getAuthor());
        }

        // Buscar um livro por ID
        Book foundBook = bookService.findBook(newBook.getId());
        if (foundBook != null) {
            System.out.println("Livro encontrado: " + foundBook.getTitle());
        } else {
            System.out.println("Livro não encontrado.");
        }

        // Deletar o livro
        bookService.deleteAbook(newBook.getId());
        System.out.println("Livro deletado com sucesso.");

        // Fechar o EntityManager e o EntityManagerFactory
        em.close();
        emf.close();
    }
}