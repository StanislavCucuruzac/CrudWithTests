package com.example.demo.repository;

import com.example.demo.model.PersonEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepo {
    SessionFactory sessionFactory;

    PersonRepo(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }
    public List<PersonEntity> getAll(){
        Session session = sessionFactory.openSession();
        Query query= session.createQuery("from PersonEntity");
        List<PersonEntity> list =query.list();
        session.close();
        return list;
    }
    public PersonEntity save(PersonEntity person){
        Session session = sessionFactory.openSession();
        session.save(person);
        session.close();
        return person;
    }
    public PersonEntity getById(int id){
        Session session = sessionFactory.openSession();
        Query query= session.createQuery("from PersonEntity p where p.id = :id");
        query.setParameter("id",id);
        PersonEntity person = (PersonEntity) query.getSingleResult();
        session.close();
        return person;
    }
    public PersonEntity update(PersonEntity person, int id){
        person.setId(id);
        sessionFactory.openSession().saveOrUpdate(person);
        return person;
    }
    public String deleteById(int id){
        PersonEntity person= new PersonEntity();
        person.setId(id);
        sessionFactory.openSession().delete(person);
        return "succes deleted person with id: " +id;
    }
}
