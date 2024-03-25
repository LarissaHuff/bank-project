package com.lariss.bankproject.controller;

import com.lariss.bankproject.dto.PersonDTO;
import com.lariss.bankproject.dto.PersonViewDTO;
import com.lariss.bankproject.enumeration.DocumentType;
import com.lariss.bankproject.model.Person;
import com.lariss.bankproject.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody PersonDTO personDTO) {
        Long createdId = personService.register(personDTO);
        return ResponseEntity.created(URI.create("/persons/" + createdId)).build();
    }

    @ResponseBody
    @GetMapping("/{id}")
    public PersonViewDTO findById(@PathVariable Long id) {
        return new PersonViewDTO(personService.findById(id));
    }

    @ResponseBody
    @GetMapping
    public List<PersonViewDTO> findAllByName(@RequestParam(required = false) String name) {
        List<Person> personList = personService.findAllByName(name);
        return personList.stream()
                .map(PersonViewDTO::new)
                .toList();
    }

    @ResponseBody
    @GetMapping("/document")
    public PersonViewDTO findByDocument(@RequestParam DocumentType type, @RequestParam String number) {
        return new PersonViewDTO(personService.findByDocumentTypeAndNumber(type, number));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }


    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        personService.update(id, personDTO);
    }

}

