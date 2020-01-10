package com.bookshop.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FilterServiceTest {

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private FilterService filterService;

    @Test
    public void shouldCallAuthorServiceFindBySurnameAndNameTwoTimesWhenFilterConsistsOfTwoWords(){
        String filter = "filter filter";
        filterService.authorsFilter(filter);
        verify(authorService,times(2)).findBySurnameAndName(isA(String.class),isA(String.class));
    }

}
