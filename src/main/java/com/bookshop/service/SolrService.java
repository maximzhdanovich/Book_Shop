package com.bookshop.service;

import com.bookshop.solr.model.Author;
import com.bookshop.solr.model.Book;
import com.bookshop.solr.model.Category;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.*;

@Service
public class SolrService {

    @Autowired
    private HttpSolrClient solrClient;

    public void bookSearch(String filter, Model model) throws IOException, SolrServerException {
        List<String> items = Arrays.asList(filter.split("\\s* \\s*"));
        List<Book> books = new ArrayList<>();
        Set<SolrDocument> findingBooks = new HashSet<>();
        for (String item : items) {
            SolrQuery query = new SolrQuery();
            query.set("q", "title_Ru:*" + item + "* or title_En:*" + item + "* or price:*" + item + "* or description:*" + item + "*");
            QueryResponse response = solrClient.query(query);
            findingBooks.addAll(response.getResults()) ;

        }
        model.addAttribute("books", findingBooks);
    }
    public void authorSearch(String filter, Model model) throws IOException, SolrServerException {
        List<String> items = Arrays.asList(filter.split("\\s* \\s*"));
        List<Book> authors = new ArrayList<>();
        Set<SolrDocument> findingBooks = new HashSet<>();
        for (String item : items) {
            SolrQuery query = new SolrQuery();
            query.set("q", "surname:*" + item + "* or name:*" + item +"*");
            QueryResponse response = solrClient.query(query);
            findingBooks.addAll(response.getResults()) ;
        }
        model.addAttribute("authors", findingBooks);
    }
    public void categorySearch(String filter, Model model) throws IOException, SolrServerException {
        List<String> items = Arrays.asList(filter.split("\\s* \\s*"));
        List<Book> categories = new ArrayList<>();
        Set<SolrDocument> findingBooks = new HashSet<>();
        for (String item : items) {
            SolrQuery query = new SolrQuery();
            query.set("q", "category_Title_Ru:*" + item + "* or category_Title_En:*" + item +"*");
            QueryResponse response = solrClient.query(query);
            findingBooks.addAll(response.getResults()) ;
        }
        model.addAttribute("categories", findingBooks);
    }


}
