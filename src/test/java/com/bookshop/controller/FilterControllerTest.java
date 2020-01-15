package com.bookshop.controller;

import com.bookshop.service.BookService;
import com.bookshop.service.CategoryService;
import com.bookshop.service.FilterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Map;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FilterControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private FilterService filterService;

    @InjectMocks
    private FilterController filterController;

    @Test
    public void shouldCallFindByTitleEnOrTitleRuAndFilterServiceAuthorsFilterWhenGetFilter() {
        String filter = "filter";
        Model model = getModel();
        filterController.filter(model, filter);
        verify(bookService).findByTitleEnOrTitleRu(filter, filter);
        verify(categoryService).findFirstByTitleEnOrTitleRu(filter, filter);
        verify(filterService).authorsFilter(filter);
    }

    private Model getModel() {
        return new Model() {
            @Override
            public Model addAttribute(String s, Object o) {
                return null;
            }

            @Override
            public Model addAttribute(Object o) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> collection) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> map) {
                return null;
            }

            @Override
            public boolean containsAttribute(String s) {
                return false;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };
    }

}
