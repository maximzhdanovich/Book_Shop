package com.bookshop.controller;

import com.bookshop.model.entity.Author;
import com.bookshop.service.AuthorService;
import com.bookshop.service.BookService;
import com.bookshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/filter")
public class FilterController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String filter(Model model, @RequestParam(defaultValue = "") String filter) {
        if (filter != null && !filter.equals("")) {
            model.addAttribute("books", bookService.findByTitleEnOrTitleRu(filter, filter));
//            List<Author> authors = getAuthors(filter);
            model.addAttribute("authors", getAuthors(filter));
            model.addAttribute("category", categoryService.findFirstByTitleEnOrTitleRu(filter, filter));
        }
        model.addAttribute("filter", filter);
        return "filter";
    }

    private List<Author> getAuthors(@RequestParam(defaultValue = "") String filter) {
        List<String> items = Arrays.asList(filter.split("\\s* \\s*"));
        List<Author> authors = new ArrayList<>();
        if (items.size() > 1) {
            boolean present = authorService.findBySurnameAndName(items.get(0), items.get(1)).isPresent();
            boolean present1 = authorService.findBySurnameAndName(items.get(1), items.get(0)).isPresent();
            if (present) {
                authors.add(authorService.findBySurnameAndName(items.get(0), items.get(1)).get());
            }
            if (present1) {
                authors.add(authorService.findBySurnameAndName(items.get(1), items.get(0)).get());
            }
            if (!present && !present1) {
                for (String item : items) {
                    if (!authors.containsAll(authorService.findBySurnameOrName(item, item)))
                        authors.addAll(authorService.findBySurnameOrName(item, item));
                }
            }
        } else {
            authors.addAll(authorService.findBySurnameOrName(filter, filter));
        }
        return authors;
    }
}
