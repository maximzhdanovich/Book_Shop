package com.bookshop.service;

import com.bookshop.model.dataService.AuthorDataService;
import com.bookshop.model.entity.Author;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorDataService authorDataService;

    @Mock
    private AuthorImageService authorImageService;

    @Test
    public void shouldCallAuthorDataServiceSaveWhenSaveAuthor() {
        Author author = new Author();
        authorService.save(author);
        verify(authorDataService).save(author);
    }

    @Test
    public void shouldCallAuthorDataServiceFindBySurnameAndNameAndReturnEmptyAuthorIfDataBaseIsEmpty(){
        String name = "name";
        String surname = "surname";
        Optional<Author> authorBySurnameAndName = authorService.findBySurnameAndName(surname, name);
        assertThat(authorBySurnameAndName).isEmpty();
        verify(authorDataService).findBySurnameAndName(surname,name);
    }

    @Test
    public void shouldCallAuthorDataServiceDeleteWhenDeleteAuthor(){
        long id = 1L;
        authorService.deleteById(id);
        verify(authorDataService).deleteById(id);
    }

    @Test
    public void shouldCallAuthorDataServiceFindByIdWhenAuthorFindById(){
        long id = 1L;
        authorService.findById(id);
        verify(authorDataService).findById(id);
    }

    @Test
    public void shouldReturnEmptyListWhenCallFindAllAndDataBaseIsEmpty(){
        when(authorDataService.findAll()).thenReturn(Collections.emptyList());
        List<Author> authorList = authorService.findAll();
        assertThat(authorList).isEmpty();
    }

    @Test
    public void shouldCallAuthorDataServiceSaveWhenCreateAuthor(){
        String name = "name";
        String surname = "surname";
        authorService.create(surname, name);
        verify(authorDataService).save(isA(Author.class));
    }

    @Test
    public void shouldCallAuthorDataServiceSaveWhenUpdateAuthor() throws IOException {
        String name = "name";
        String surname = "surname";
        Author author = new Author();
        authorService.update(surname, name, author, getImage());
        verify(authorDataService).save(author);
    }

    private MultipartFile getImage() {
        return new MultipartFile() {
            @Override
            public String getName() {
                return "null";
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
