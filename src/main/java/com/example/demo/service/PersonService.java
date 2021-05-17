package com.example.demo.service;

import com.example.demo.model.PersonEntity;
import com.example.demo.repository.PersonRepo;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {
    PersonRepo personRepo;
    private SessionFactory sessionFactory;

    @Transactional
    public List<PersonEntity> getAll(){
        return personRepo.getAll();
    }
    @Transactional
    public PersonEntity save(PersonEntity person){
        return personRepo.save(person);
    }
    @Transactional
    public PersonEntity getById(int id){
        return personRepo.getById(id);
    }
    @Transactional
    public void delete(int id){
        personRepo.deleteById(id);
    }
    @Transactional
    public PersonEntity update(PersonEntity person, int id){
        person.setId(id);
        return personRepo.save(person);
    }

}
