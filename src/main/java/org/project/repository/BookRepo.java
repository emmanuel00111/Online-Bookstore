package org.project.repository;

import org.project.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//TODO kinda wanna rename this to BookRepository
@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
    public Book findById(int id);
    public List<Book> findByISBN(String isbn);
    public List<Book> findByTitle(String title);
    public List<Book> findByAuthor(String author);
    public List<Book> findByPublisher(String publisher);
    public List<Book> findByDescription(String description);

    @Query("SELECT b FROM Book b " +
            "WHERE LOWER(b.ISBN) LIKE %:str% " +
            "OR LOWER(b.title) LIKE %:str% " +
            "OR LOWER(b.author) LIKE %:str% " +
            "OR LOWER(b.publisher) LIKE %:str% " +
            "OR LOWER(b.description) LIKE %:str%")
    public Iterable<Book> findByAllColumns(@Param("str") String str);
}