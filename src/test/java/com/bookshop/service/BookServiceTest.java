package com.bookshop.service;

import com.bookshop.model.dataService.BookDataService;
import com.bookshop.model.entity.Author;
import com.bookshop.model.entity.Book;
import com.bookshop.model.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    @Mock
    private BookDataService bookDataService;

    @Mock
    private BookImageService bookImageService;

    @Mock
    private AuthorService authorService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private Pageable pageable;

    @InjectMocks
    private BookService bookService;

    @Test
    public void shouldCallBookDataServiceGetLastBookWhenGetBookByIdDesc() {
        bookService.getLastBooks();
        verify(bookDataService).getLastBooks();
    }

    @Test
    public void shouldCallBookDataServiceSaveWhenCreateBook() throws IOException {
        bookService.create(1, "1", "1", "1", new Author(), new HashMap<String, String>(), getImage());
        verify(bookDataService).save(isA(Book.class));
    }

    @Test
    public void shouldCallBookDataServiceSaveWhenSaveBook() {
        Book book = new Book();
        bookService.save(book);
        verify(bookDataService).save(book);
    }

    @Test
    public void shouldReturnEntityWhenCallFindByIdAndDataBaseIsNotEmpty() {
        long id = 1L;
        Book book = new Book();
        when(bookDataService.findById(id)).thenReturn(book);
        Book bookById = bookService.findById(id);
        assertThat(bookById).isEqualTo(book);
    }

    @Test
    public void shouldReturnEmptyListWhenCallFindAllAndDataBaseIsEmpty() {
        when(bookDataService.findAll()).thenReturn(Collections.emptyList());
        assertThat(bookService.findAll()).isEmpty();
    }

    @Test
    public void shouldReturnCollectionsSingletonWhenDataBaseHaveOneSatisfyingBook() {
        String titleEn = "some";
        String titleRu = "some";
        Book book = new Book();
        when(bookDataService.findByTitleEnOrTitleRu(titleEn, titleRu)).thenReturn(Collections.singletonList(book));
        List<Book> bookByTitleEnOrTitleRu = bookService.findByTitleEnOrTitleRu(titleEn, titleRu);
        assertThat(bookByTitleEnOrTitleRu.get(0)).isEqualTo(book);
    }

    @Test
    public void shouldCallBookDataServiceDeleteWhenDeleteBook() {
        long id = 1L;
        bookService.deleteById(id);
        verify(bookDataService).deleteById(id);
    }

    @Test
    public void shouldReturnEmptyPageOfBooksWhenFindByAuthorDataBaseIsEmpty() {
        Author author = new Author();
        when(bookDataService.findAllByAuthor(author, pageable)).thenReturn(null);
        Page<Book> bookAllByAuthor = bookService.findAllByAuthor(author, pageable);
        assertThat(bookAllByAuthor).isNull();
    }

    @Test
    public void shouldReturnEmptyPageOfBooksWhenFindByCategoryDataBaseIsEmpty() {
        Category category = new Category();
        when(bookDataService.findAllByCategories(category, pageable)).thenReturn(null);
        Page<Book> bookAllByCategory = bookService.findAllByCategories(category, pageable);
        assertThat(bookAllByCategory).isNull();
    }

    @Test
    public void shouldReturnEmptyPageOfBooksWhenFindAllDataBaseIsEmpty() {
        when(bookDataService.findAllPage(pageable)).thenReturn(null);
        Page<Book> bookAll = bookService.findAllPage(pageable);
        assertThat(bookAll).isNull();
    }

    @Test
    public void shouldCallBookDataServiceSaveWhenUpdateBook() throws IOException {
        Book book = new Book();
        book.setCategories(new ArrayList<>());
        bookService.update(book, 1, "1", "1", "1", "surname", "name", new HashMap<String, String>(), getImage());
        verify(bookDataService).save(book);
    }

    private MultipartFile getImage() {
        return new MultipartFile() {
            @Override
            public String getName() {
                return "some";
            }

            @Override
            public String getOriginalFilename() {
                return "some";
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
}
