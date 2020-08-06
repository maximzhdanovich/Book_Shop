package com.bookshop.solr.repository;

import com.bookshop.solr.model.Category;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface CategoryRepository extends SolrCrudRepository<Category, Integer> {

}
