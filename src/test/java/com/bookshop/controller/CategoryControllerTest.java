package com.bookshop.controller;

import com.bookshop.model.entity.Category;
import com.bookshop.service.BookService;
import com.bookshop.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Map;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private BookService bookService;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    public void shouldCallCategoryServiceWhenGetCategoryListPage(){
        categoryController.allCategory(getModel());
        verify(categoryService).findAll();
    }

    @Test
    public void shouldCallCategoryServiceCreateWhenCreateCategory(){
        String titleEn = "titleEn";
        String titleRu = "titleRu";
        categoryController.createCategory(titleEn, titleRu);
        verify(categoryService).create(titleEn,titleRu);
    }

    @Test
    public void shouldCallBookServiceFindAllByCategoriesWhenGetBooksOfCategory(){
        Category category = new Category();
        Model model = getModel();
        Pageable pageable = getPageable();
        categoryController.singleCategory(category, model, pageable);
        verify(bookService).findAllByCategories(category, pageable);
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
}
