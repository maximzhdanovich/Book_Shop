package com.bookshop.controller;

import com.bookshop.service.AuthorService;
import com.bookshop.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DeleteControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private DeleteController deleteController;

    @Test
    public void shouldCallBookServiceDeleteByIdWhenDeleteBook() {
        long id = 1L;
        deleteController.deleteBook(id);
        verify(bookService).deleteById(id);
    }

    @Test
    public void shouldCallAuthorServiceDeleteByIdWhenDeleteAuthor() {
        long id = 1L;
        deleteController.deleteAuthor(id);
        verify(authorService).deleteById(id);
    }


}
