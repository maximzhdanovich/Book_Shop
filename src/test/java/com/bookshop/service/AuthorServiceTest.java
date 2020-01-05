package com.bookshop.service;

import com.bookshop.model.dataService.AuthorImageDataService;
import com.bookshop.model.entity.AuthorImage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {

    @Mock
    private AuthorImageDataService authorImageDataService;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorImageService authorImageService;

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


}
