package persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import persistence.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

public class JPABookDao implements Dao <Book> {

   @PersistenceContext
    private EntityManager em;


    public EntityManager getEm() {
        return em;
    }

    @Autowired
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Book> findAll() {
        CriteriaBuilder criteriaBuilder= em.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> bookRoot = criteriaQuery.from(Book.class);
        // Seleciona todos os registos da entidade Book
        criteriaQuery.select(bookRoot);
        // Executa a consulta e retorna a lista de resultados
        return em.createQuery(criteriaQuery).getResultList();
    }

    public Book findByIsbn(int isbn) {
        // Cria um CriteriaBuilder para construir a consulta
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        // Cria uma CriteriaQuery para a entidade Book
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);

        // Define a raiz da consulta (a tabela Book)
        Root<Book> bookRoot = criteriaQuery.from(Book.class);

        // Adiciona uma condição na consulta: where book.isbn = :isbn
        criteriaQuery.select(bookRoot).where(criteriaBuilder.equal(bookRoot.get("isbn"), isbn));

        // Executa a consulta e retorna o resultado
        List<Book> result = em.createQuery(criteriaQuery).getResultList();

        // Retorna o primeiro resultado ou null se não houver livros com o ISBN fornecido
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Book findById(Integer id) {
        return em.find(Book.class, id);
    }

    @Override
    public Book saveOrUpdate(Book book) {
       return em.merge(book);
    }

    @Override
    public void delete(Integer id) {
        em.remove(em.find(Book.class,id));

    }

    /*GET /api/books - Retrieve a list of all books.
            ● GET /api/books/{id} - Retrieve details of a specific book by ID.
            ● POST /api/books - Add a new book.
● PUT /api/books/{id} - Update an existing book by ID.
            ● DELETE /api/books/{id} - Delete a book by ID.*/
}
