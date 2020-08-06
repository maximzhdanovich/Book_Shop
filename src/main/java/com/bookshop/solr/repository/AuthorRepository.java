package com.bookshop.solr.repository;

import com.bookshop.solr.model.Author;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface AuthorRepository extends SolrCrudRepository<Author, Integer> {

}
