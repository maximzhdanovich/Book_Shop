package com.bookshop.service;

import com.bookshop.model.dataService.AuthorImageDataService;
import com.bookshop.model.entity.Author;
import com.bookshop.model.entity.AuthorImage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Fields;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthorImageServiceTest {

    @Mock
    private AuthorImageDataService authorImageDataService;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorImageService authorImageService;

    private MultipartFile multipartFile;

    @Test
    public void shouldCallAuthorImageDataServiceSaveWhenSaveAuthorImage() {
        AuthorImage authorImage = new AuthorImage();

        authorImageService.save(authorImage);

        verify(authorImageDataService).save(authorImage);
    }

    @Test
    public void shouldReturnEmptyListWhenCallFindAllAndDatabaseIsEmpty() {
        when(authorImageDataService.findAll()).thenReturn(Collections.emptyList());

        List<AuthorImage> authorImages = authorImageService.findAll();
        assertThat(authorImages).isEmpty();

//        assertThatThrownBy(() -> authorImageService.save(null)).isInstanceOf(IOException.class);
    }

    @Test
    public void shouldReturnEntityWhenCallFindByIdAndDatabaseIsNotEmpty() {
        long id = 1L;
        when(authorImageDataService.findById(id)).thenReturn(new AuthorImage());
        AuthorImage authorImage = authorImageService.findById(id);
        assertThat(authorImage).isNotNull();
        verify(authorImageDataService).findById(id);
    }

    @Test
    public void shouldCallAuthorImageDataServiceDeleteByIdWhenDeleteByIdAuthorImage(){
        long id = 1L;
        authorImageService.deleteById(id);
        verify(authorImageDataService).deleteById(id);
    }

    @Test
    public void shouldCallAuthorImageServiceSaveWhenAddNotNullAuthorImage() throws Exception {
        Author author = new Author();
        setUploadPath();
        authorImageService.add(new MultipartFile() {
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
        },author);
        verify(authorImageDataService).save(notNull());
    }

    private void setUploadPath() throws NoSuchFieldException, IllegalAccessException {
        Field field = authorImageService.getClass().getDeclaredField("uploadPath");
        field.setAccessible(true);
        field.set(authorImageService, "qwe");
        field.setAccessible(false);
    }
}
