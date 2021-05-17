package com.example.demo.controller;

import com.example.demo.service.PersonService;
import com.example.demo.model.PersonEntity;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
@AllArgsConstructor
public class PersonController {

   PersonService personService;

    @GetMapping
    public List<PersonEntity> getAll(){
        return personService.getAll();
    }
    @PostMapping
    public PersonEntity savePerson(@RequestBody PersonEntity person){
        return personService.save(person);
    }
    @GetMapping("{id}")
    public PersonEntity getById(@PathVariable int id){
        return personService.getById(id);
    }
    @PutMapping("/put/{id}")
    public PersonEntity updatePerson(@RequestBody PersonEntity person, @PathVariable int id){
        return personService.update(person, id);
    }
    @DeleteMapping
    public void deletePerson(@PathVariable int id){
        personService.delete(id);
    }
}
