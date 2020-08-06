package com.bookshop.controller;

import com.bookshop.service.BookService;
import com.bookshop.service.CategoryService;
import com.bookshop.service.FilterService;
import com.bookshop.service.SolrService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/filter")
public class FilterController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FilterService filterService;

    @Autowired
    private SolrService solrService;

    @GetMapping
    public String filter(Model model, @RequestParam(defaultValue = "") String filter) throws IOException, SolrServerException {
        if (filter != null && !filter.equals("")) {
//            model.addAttribute("books", bookService.findByTitleEnOrTitleRu(filter, filter));
            solrService.bookSearch(filter,model);
            solrService.authorSearch(filter,model);
            solrService.categorySearch(filter,model);
//            model.addAttribute("authors", filterService.authorsFilter(filter));
//            model.addAttribute("category", categoryService.findFirstByTitleEnOrTitleRu(filter, filter));
        }
        model.addAttribute("filter", filter);
        return "filter";
    }


}
