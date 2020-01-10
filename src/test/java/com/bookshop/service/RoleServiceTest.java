package com.bookshop.service;

import com.bookshop.model.dataService.RoleDataService;
import com.bookshop.model.entity.Author;
import com.bookshop.model.entity.Role;
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
public class RoleServiceTest {

    @Mock
    private RoleDataService roleDataService;

    @InjectMocks
    private RoleService roleService;

    @Test
    public void shouldReturnEmptyListWhenCallFindAllAndDataBaseIsEmpty(){
        when(roleDataService.findAll()).thenReturn(Collections.emptyList());
        List<Role> roleList = roleService.findAll();
        assertThat(roleList).isEmpty();
    }

    @Test
    public void shouldCallRoleDataServiceFindByIdWhenRoleFindById(){
        long id = 1L;
        roleService.findById(id);
        verify(roleDataService).findById(id);
    }

    @Test
    public void shouldReturnEntityWhenDataBaseHaveSatisfyingRole(){
        String title = "title";
        Role role = new Role();
        when(roleDataService.findByTitle(title)).thenReturn(role);
        Role roleByTitle = roleService.findByTitle(title);
        assertThat(roleByTitle).isEqualTo(role);
    }
}
