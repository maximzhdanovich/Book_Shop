package com.test.db.dao;

import com.test.db.model.Author;
import com.test.db.model.Book;
import com.test.db.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookDAO extends CrudRepository<Book, Long> {
    List<Book> findAll();

    Book deleteById(long id);

    Book deleteByTitleEn(long id);

    List<Book> findAllByPrice(double price);

    List<Book> findAllByTitleEnOrTitleRu(String title);

    List<Book> deleteAllByTitleEnOrTitleRu(String title);

    List<Book> findAllByCategories(Category category);

    List<Book> findAllByAuthor(Author author);


}
