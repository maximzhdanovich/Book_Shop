package com.bookshop.controller;

import com.bookshop.model.entity.Basket;
import com.bookshop.model.entity.CustomUserDetail;
import com.bookshop.model.entity.User;
import com.bookshop.service.BasketService;
import com.bookshop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.beans.PropertyEditor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private BasketService basketService;

    @InjectMocks
    private AccountController accountController;

    @Test
    public void shouldCallUserServiceGetCurrentUserWhenGetMyAccountPage() {
        User user = new User();
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        when(userService.getCurrentUser(customUserDetail)).thenReturn(user);
        accountController.myAccount(customUserDetail, getModel());
        verify(userService).getCurrentUser(customUserDetail);
    }

    @Test
    public void shouldCallUserServiceGetCurrentUserWhenGetMyAccountEditPage(){
        User user = new User();
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        when(userService.getCurrentUser(customUserDetail)).thenReturn(user);
        accountController.accountEdit(customUserDetail, getModel());
        verify(userService).getCurrentUser(customUserDetail);
    }

    @Test
    public void shouldCallUserServiceEditConfigurationWhenEditAccountSave(){
        User user = new User();
        CustomUserDetail customUserDetail = new CustomUserDetail(user);

        String newPassword = "newPassword";
        Model model = getModel();
        BindingResult bindingResult = getBindingResult();
        accountController.saveAccountEdit(customUserDetail, user, bindingResult, newPassword,newPassword, model);
        verify(userService).userEditConfiguration(customUserDetail, user, bindingResult, newPassword,newPassword, model);
    }

    @Test
    public void shouldCallUserServiceGetCurrentUserWhenGetUserCart(){
        User user = new User();
        Basket basket = new Basket();
        basket.setBooks(new ArrayList<>());
        basket.setBooksApproved(new ArrayList<>());
        user.setBasket(basket);
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        when(userService.getCurrentUser(customUserDetail)).thenReturn(user);
        accountController.myBasket(customUserDetail,getModel());
        verify(userService,times(3)).getCurrentUser(customUserDetail);
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

    private BindingResult getBindingResult() {
        return new BindingResult() {
            @Override
            public Object getTarget() {
                return null;
            }

            @Override
            public Map<String, Object> getModel() {
                return null;
            }

            @Override
            public Object getRawFieldValue(String s) {
                return null;
            }

            @Override
            public PropertyEditor findEditor(String s, Class<?> aClass) {
                return null;
            }

            @Override
            public PropertyEditorRegistry getPropertyEditorRegistry() {
                return null;
            }

            @Override
            public String[] resolveMessageCodes(String s) {
                return new String[0];
            }

            @Override
            public String[] resolveMessageCodes(String s, String s1) {
                return new String[0];
            }

            @Override
            public void addError(ObjectError objectError) {

            }

            @Override
            public String getObjectName() {
                return null;
            }

            @Override
            public void setNestedPath(String s) {

            }

            @Override
            public String getNestedPath() {
                return null;
            }

            @Override
            public void pushNestedPath(String s) {

            }

            @Override
            public void popNestedPath() throws IllegalStateException {

            }

            @Override
            public void reject(String s) {

            }

            @Override
            public void reject(String s, String s1) {

            }

            @Override
            public void reject(String s, Object[] objects, String s1) {

            }

            @Override
            public void rejectValue(String s, String s1) {

            }

            @Override
            public void rejectValue(String s, String s1, String s2) {

            }

            @Override
            public void rejectValue(String s, String s1, Object[] objects, String s2) {

            }

            @Override
            public void addAllErrors(Errors errors) {

            }

            @Override
            public boolean hasErrors() {
                return false;
            }

            @Override
            public int getErrorCount() {
                return 0;
            }

            @Override
            public List<ObjectError> getAllErrors() {
                return null;
            }

            @Override
            public boolean hasGlobalErrors() {
                return false;
            }

            @Override
            public int getGlobalErrorCount() {
                return 0;
            }

            @Override
            public List<ObjectError> getGlobalErrors() {
                return null;
            }

            @Override
            public ObjectError getGlobalError() {
                return null;
            }

            @Override
            public boolean hasFieldErrors() {
                return false;
            }

            @Override
            public int getFieldErrorCount() {
                return 0;
            }

            @Override
            public List<FieldError> getFieldErrors() {
                return null;
            }

            @Override
            public FieldError getFieldError() {
                return null;
            }

            @Override
            public boolean hasFieldErrors(String s) {
                return false;
            }

            @Override
            public int getFieldErrorCount(String s) {
                return 0;
            }

            @Override
            public List<FieldError> getFieldErrors(String s) {
                return null;
            }

            @Override
            public FieldError getFieldError(String s) {
                return null;
            }

            @Override
            public Object getFieldValue(String s) {
                return null;
            }

            @Override
            public Class<?> getFieldType(String s) {
                return null;
            }
        };
    }
}