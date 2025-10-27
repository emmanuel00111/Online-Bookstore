package org.project.repositories;

import org.project.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepo extends CrudRepository<Book,Integer> {
    public Book findBookById(int id);
    public List<Book> findBooksByName(String name);
    public List<Book> findBooksByAuthor(String author);
}
