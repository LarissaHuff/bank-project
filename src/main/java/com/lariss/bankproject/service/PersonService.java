package com.lariss.bankproject.service;

import com.lariss.bankproject.dto.PersonDTO;
import com.lariss.bankproject.enumeration.DocumentType;
import com.lariss.bankproject.model.Person;

import java.util.List;

public interface PersonService {
    Long register(PersonDTO personDTO);

    Person findById(Long id);

    List<Person> findAllByName(String name);

    Person findByDocumentTypeAndNumber(DocumentType type, String documentNumber);

    void delete(Long id);

    void update(Long id, PersonDTO personDTO);

}
