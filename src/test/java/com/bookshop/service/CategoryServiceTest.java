package com.bookshop.service;

import com.bookshop.model.dataService.CategoryDataService;
import com.bookshop.model.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @Mock
    private CategoryDataService categoryDataService;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void shouldReturnCollectionsSingletonWhenDataBaseHaveOneEntity() {
        Category category = new Category();
        when(categoryDataService.findAll()).thenReturn(Collections.singletonList(category));
        List<Category> categoryList = categoryService.findAll();
        assertThat(categoryList.get(0)).isEqualTo(category);
    }

    @Test
    public void shouldReturnNullWhenCallFindByIdAndDataBaseIsEmpty() {
        long id = 1L;
        Category category = categoryService.findById(id);
        assertThat(category).isNull();
    }

    @Test
    public void shouldReturnSingletonWhenDataBaseHaveManySatisfyingBook() {
        String titleEn = "some";
        String titleRu = "some";
        Category category = new Category();
        when(categoryDataService.findFirstByTitleEnOrTitleRu(titleEn, titleRu)).thenReturn(category);
        Category categoryByTitleEnOrTitleRu = categoryService.findFirstByTitleEnOrTitleRu(titleEn, titleRu);
        assertThat(categoryByTitleEnOrTitleRu).isEqualTo(category);
    }

    @Test
    public void shouldCallCategoryDataServiceSaveWhenSaveCategory() {
        categoryService.save(new Category());
        verify(categoryDataService).save(isA(Category.class));
    }
}
