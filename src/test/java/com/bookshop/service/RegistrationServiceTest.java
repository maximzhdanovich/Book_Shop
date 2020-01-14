package com.bookshop.service;

import com.bookshop.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private CartService cartService;

    @InjectMocks
    private RegistrationService registrationService;

    @Autowired
    private BindingResult bindingResult;

    private Model model;

    @Test
    public void shouldReturnSuccessRegistrationWhenBindingResultDontHaveErrors(){
        User user = new User();
        String password = "password";
        user.setPassword(password);
        String email = "email@email.com";
        user.setEmail(email);
        String username = "username";
        user.setUsername(username);
        BindingResult bindingResult = getBindingResult();
        String s = registrationService.addNewUser(password, user, bindingResult, model);
        assertThat(s).isEqualTo("successRegistration");
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
