package com.bookshop.solr.model;


import com.bookshop.model.entity.Book;
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
public class Category {
    @Id
    @Field
    private long categoryId;
    @Field
    private String categoryTitleRu;
    @Field
    private String categoryTitleEn;

}
