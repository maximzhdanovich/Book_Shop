package com.bookshop.controller;

import com.bookshop.model.entity.Author;
import com.bookshop.service.AuthorImageService;
import com.bookshop.service.AuthorService;
import com.bookshop.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @Mock
    private AuthorImageService authorImageService;

    @Mock
    private BookService bookService;

    @InjectMocks
    private AuthorController authorController;

    @Test
    public void shouldReturnAuthorEditWhenAdminGetAuthorEditPage() {
        long id = 1L;
        String url = authorController.authorEditPage(id, getModel());
        assertThat(url).isEqualTo("authorEdit");
    }

    @Test
    public void shouldCallAuthorServiceUpdateWhetSaveAuthorEditedInformation() throws IOException {
        String surname = "surname";
        String name = "name";
        Author author = new Author();
        MultipartFile image = getImage();
        authorController.authorSaveEditedInformation(surname, name, author, image);
        verify(authorService).update(surname, name, author, image);
    }

    @Test
    public void shouldCallAuthorServiceFindAllWhenGetAuthorListPage() {
        authorController.authorList(getModel());
        verify(authorService).findAll();
    }

    @Test
    public void shouldReturnAuthorListWhenAddAuthorAndAuthorAlreadyExist() throws IOException {
        String surname = "surname";
        String name = "name";
        Author author = new Author();
        when(authorService.findBySurnameAndName(surname, name)).thenReturn(java.util.Optional.of(author));
        MultipartFile image = getImage();
        String url = authorController.addAuthor(surname, name, image, getModel());
        assertThat(url).isEqualTo("authorList");
    }

    @Test
    public void shouldCallBookServiceFindByAuthorWhenGetAuthorBooks() {
        Author author = new Author();
        Pageable pageable = getPageable();
        long id = 1L;
        when(authorService.findById(id)).thenReturn(author);
        authorController.authorBooks(getModel(), id, pageable);
        verify(bookService).findAllByAuthor(author, pageable);
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

    private MultipartFile getImage() {
        return new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return "null";
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
