package com.bookshop.controller;


import com.bookshop.service.AuthorService;
import com.bookshop.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Map;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private MainController mainController;

    @Test
    public void shouldCallAuthorServiceFindAllWhenGetMainPage(){
        mainController.main(getModel());
        verify(authorService).findAll();
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
