package com.bookshop.controller;

import com.bookshop.model.entity.Author;
import com.bookshop.model.entity.Book;
import com.bookshop.service.AuthorService;
import com.bookshop.service.BookService;
import com.bookshop.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.byLessThan;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    @Mock
    private AuthorService authorService;

    @Mock
    private BookService bookService;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private BookController bookController;


    @Test
    public void shouldCallBookServiceFindAllPageWhenGetBookList() {
        Pageable pageable = getPageable();
        bookController.bookList(getModel(), pageable);
        verify(bookService).findAllPage(pageable);
    }

    @Test
    public void shouldCallBookServiceCreateWhenCreateBook() throws IOException {
        Map<String, String> map = new HashMap<>();
        String value = "1";
        String titleRu = "titleRu";
        String titleEn = "titleEn";
        String description = "description";
        String authorSurname = "authorSurname";
        String authorName = "authorName";
        map.put("price", value);
        map.put("titleRu", titleRu);
        map.put("titleEn", titleEn);
        map.put("description", description);
        map.put("authorSurname", authorSurname);
        map.put("authorName", authorName);
        Author author = new Author();
        when(authorService.findBySurnameAndName(authorSurname,authorName)).thenReturn(Optional.of(author));
        MultipartFile image = getImage();
        bookController.bookCreate(image, map, getModel(), getPageable());
        verify(bookService).create(Double.parseDouble(value), titleRu, titleEn, description, author, map, image);
    }

    @Test
    public void shouldReturnBookEditWhenGetBookEditPageAndBookIsPresent(){
        long id = 1L;
        Book book = new Book();
        when(bookService.findById(id)).thenReturn(book);
        String resultValue = bookController.bookEdit(String.valueOf(id), getModel());
        assertThat(resultValue).isEqualTo("bookEdit");
    }

    @Test
    public void shouldCallBookServiceUpdateWhenSaveEditBook() throws IOException {
        Map<String, String> map = new HashMap<>();
        String value = "1";
        String titleRu = "titleRu";
        String titleEn = "titleEn";
        String description = "description";
        String authorSurname = "authorSurname";
        String authorName = "authorName";
        map.put("price", value);
        map.put("titleRu", titleRu);
        map.put("titleEn", titleEn);
        map.put("description", description);
        map.put("authorSurname", authorSurname);
        map.put("authorName", authorName);
        Book book = new Book();
        Author author = new Author();
        when(authorService.findBySurnameAndName(authorSurname,authorName)).thenReturn(Optional.of(author));
        MultipartFile image = getImage();
        bookController.bookSaveEdit(image,map, book);
        verify(bookService).update(book, Double.parseDouble(value),titleEn,titleRu,authorSurname,authorName,description,map,image);
    }

    @Test
    public void shouldReturnBookWhenGetBookPage(){
        String bookId = "1";
        Book book = new Book();
        when(bookService.findById(Long.parseLong(bookId))).thenReturn(book);
        String result = bookController.book(bookId, getModel());
        assertThat(result).isEqualTo("book");
    }

    private MultipartFile getImage() {
        return new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File file) throws IOException, IllegalStateException {

            }
        };
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

    private Pageable getPageable() {
        return new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
    }

}
