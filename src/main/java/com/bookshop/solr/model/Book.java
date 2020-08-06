package com.bookshop.solr.model;

import com.bookshop.model.entity.Author;
import com.bookshop.model.entity.BookImage;
import com.bookshop.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(collection = "shop")
public class Book {

    @Id
    @Field
    private long bookId;
    @Field
    private double price;
    @Field
    private String titleRu;
    @Field
    private String titleEn;
    @Field
    private String description;
}
