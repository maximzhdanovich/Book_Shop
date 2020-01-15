package com.bookshop.service;

import com.bookshop.model.dataService.UserDataService;
import com.bookshop.model.entity.CustomUserDetail;
import com.bookshop.model.entity.User;
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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserDataService userDataService;

    @Mock
    private RoleService roleService;

    @Mock
    private CartService cartService;

    @InjectMocks
    private UserService userService;

    private Model model;

    @Test
    public void shouldReturnEmptyListWhenCallFindAllAndDataBaseIsEmpty() {
        when(userDataService.findAll()).thenReturn(Collections.emptyList());
        List<User> userList = userService.findAll();
        assertThat(userList).isEmpty();
    }

    @Test
    public void shouldCallUserDataServiceFindByIdWhenUserFindById() {
        long id = 1L;
        userService.findById(id);
        verify(userDataService).findById(id);
    }

    @Test
    public void shouldReturnEntityWhenFindByUsernameAndDataBaseHaveSatisfyingUser() {
        String username = "username";
        User user = new User();
        when(userDataService.findByUsername(username)).thenReturn(java.util.Optional.of(user));
        Optional<User> byUsername = userService.findByUsername(username);
        assertThat(byUsername.get()).isEqualTo(user);
    }

    @Test
    public void shouldReturnEntityWhenFindByEmailAndDataBaseHaveSatisfyingUser() {
        String email = "email@email.com";
        User user = new User();
        when(userDataService.findByEmail(email)).thenReturn(java.util.Optional.of(user));
        Optional<User> byEmail = userService.findByEmail(email);
        assertThat(byEmail.get()).isEqualTo(user);
    }

    @Test
    public void shouldCallUserDataServiceDeleteWhenDeleteUser() {
        long id = 1L;
        userService.deleteById(id);
        verify(userDataService).deleteById(id);
    }

    @Test
    public void shouldCallUserDataServiceSaveWhenSaveUser() {
        User user = new User();
        userService.save(user);
        verify(userDataService).save(user);
    }

    @Test
    public void shouldCallUserDataServiceFindByIdWhenGetCurrentUser() {
        User user = new User();
        long id = 1L;
        user.setId(id);
        userService.getCurrentUser(user);
        verify(userDataService).findById(id);
    }

    @Test
    public void shouldCallUserDataServiceWhenCreateUser() {
        User user = new User();
        userService.create(user);
        verify(userDataService).save(user);
    }

    @Test
    public void shouldCallUserDataServiceWhenUpdateUser() {
        User user = new User();
        String email = "email@email.com";
        String password = "password";
        String username = "username";
        String role = "role";
        userService.update(user, role, username, password, email);
        verify(userDataService).save(user);
    }

    @Test
    public void shouldReturnRedirectWhenSuccessEditConfiguration() {
        User user = new User();
        long id = 1L;
        user.setId(id);
        String username = "username";
        user.setUsername(username);
        String email = "email@eamil.com";
        user.setEmail(email);
        String oldPassword = "oldPassword";
        user.setPassword(oldPassword);
        CustomUserDetail customUserDetail = new CustomUserDetail(user);
        String password = "password";

        when(userService.getCurrentUser(customUserDetail)).thenReturn(user);
        String userEditConfiguration = userService.userEditConfiguration(customUserDetail, user, getBindingResult(), password, password, model);
        assertThat(userEditConfiguration).isEqualTo("redirect:/account");
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