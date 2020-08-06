package com.bookshop.solr.repository;

import com.bookshop.solr.model.Book;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface BookRepository extends SolrCrudRepository<Book, Integer> {


}
