package com.bookshop.service;

import com.bookshop.model.dataService.BookImageDataService;
import com.bookshop.model.entity.Author;
import com.bookshop.model.entity.AuthorImage;
import com.bookshop.model.entity.Book;
import com.bookshop.model.entity.BookImage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookImageServiceTest {

    @Mock
    private BookImageDataService bookImageDataService;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookImageService bookImageService;

    @Test
    public void shouldCallBookImageDataServiceSaveWhenSaveBookImage() {
        BookImage bookImage = new BookImage();

        bookImageService.save(bookImage);

        verify(bookImageDataService).save(bookImage);
    }

    @Test
    public void shouldReturnEmptyListWhenCallFindAllAndDatabaseIsEmpty() {
        when(bookImageDataService.findAll()).thenReturn(Collections.emptyList());

        List<BookImage> authorImages = bookImageService.findAll();
        assertThat(authorImages).isEmpty();

    }

    @Test
    public void shouldReturnEntityWhenCallFindByIdAndDatabaseIsNotEmpty() {
        long id = 1L;
        when(bookImageDataService.findById(id)).thenReturn(new BookImage());
        BookImage bookImage = bookImageService.findById(id);
        assertThat(bookImage).isNotNull();
        verify(bookImageDataService).findById(id);
    }

    @Test
    public void shouldCallBookImageDataServiceDeleteByIdWhenDeleteByIdBookImage(){
        long id = 1L;
        bookImageService.deleteById(id);
        verify(bookImageDataService).deleteById(id);
    }

    @Test
    public void shouldCallBookImageServiceSaveWhenAddNotNullBookImage() throws Exception {
        Book book = new Book();
        setUploadPath();
        bookImageService.add(getImage(),book);
        verify(bookImageDataService).save(notNull());
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

    private void setUploadPath() throws NoSuchFieldException, IllegalAccessException {
        Field field = bookImageService.getClass().getDeclaredField("uploadPath");
        field.setAccessible(true);
        field.set(bookImageService, "some");
        field.setAccessible(false);
    }


}
