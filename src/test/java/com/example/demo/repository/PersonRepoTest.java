package com.example.demo.repository;

import com.example.demo.model.PersonEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Tag("DAO")
class PersonRepoTest {
    PersonEntity person1;
    PersonEntity person2;
    List<PersonEntity> personList= new ArrayList<>();

    @MockBean
    private SessionFactory sessionFactory;
    @MockBean
    private Session session;

    private PersonRepo repo;

    @BeforeEach
    void setUp() {
        when(sessionFactory.openSession()).thenReturn(session);
        repo = new PersonRepo(sessionFactory);
        person1 = new PersonEntity(1, "Ivan", 22);
        person2 = new PersonEntity(2, "Vasil", 33);
        personList.add(person1);
        personList.add(person2);
    }

    @AfterEach
    void tearDown() {
        personList.clear();
        person1 = null;
        person2 = null;
    }

    @Test
    void getAll() {
        Query q = Mockito.mock(Query.class);
        when(session.createQuery("from PersonEntity")).thenReturn(q);
        when((q).list()).thenReturn(personList);
        List list = repo.getAll();
        assertAll("PersonEntity",()-> assertNotEquals(list,null),
                ()->assertEquals(list.size(),2));
    }

    @Test
    void save() {
        repo.save(person1);
        verify(session,times(1)).save(person1);
    }

    @Test
    void getById() {
        Query query = Mockito.mock(Query.class);
        when(session.createQuery("from PersonEntity p where p.id = :id")).thenReturn(query);
        when(query.setParameter("id",1)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(person1);
        assertEquals(person1, repo.getById(1));

    }

    @Test
    void update() {
        repo.update(person1,1);
        verify(session,times(1)).saveOrUpdate(person1);
    }

    @Test
    void deleteById() {
        repo.deleteById(1);
        verify(session,times(1)).delete(new PersonEntity(1,null,0));
    }
}