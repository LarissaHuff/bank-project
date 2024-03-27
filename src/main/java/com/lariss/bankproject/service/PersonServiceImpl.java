package com.lariss.bankproject.service;

import com.lariss.bankproject.dto.PersonDTO;
import com.lariss.bankproject.enumeration.DocumentType;
import com.lariss.bankproject.exception.NotFoundException;
import com.lariss.bankproject.model.Address;
import com.lariss.bankproject.model.Person;
import com.lariss.bankproject.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public Long register(PersonDTO personDTO) {
        //TODO use model mapper
        Address address = new Address();
        address.setPostalCode(personDTO.address().postalCode());
        address.setCity(personDTO.address().city());
        address.setNumber(personDTO.address().number());
        address.setCountry(personDTO.address().country());
        address.setNeighbourhood(personDTO.address().neighbourhood());
        address.setStreet(personDTO.address().street());

        Person person = new Person();
        person.setName(personDTO.name());
        person.setEmail(personDTO.email());
        person.setBirthCity(personDTO.birthCity());
        person.setDocumentNumber(personDTO.documentNumber());
        person.setBirthDate(personDTO.birthDate());
        person.setDocumentType(personDTO.documentType());
        person.setAddress(address);

        return personRepository.save(person).getId();

    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person"));
    }

    @Override
    public List<Person> findAllByName(String name) {
        if (name == null) {
            return personRepository.findAll();
        } else {
            return personRepository.findAllByNameContainingIgnoreCase(name);
        }
    }

    @Override
    public Person findByDocumentTypeAndNumber(DocumentType type, String documentNumber) {
        return personRepository.findByDocumentTypeAndDocumentNumber(type, documentNumber);
    }

    @Override
    public void delete(Long id) {
        Person person = findById(id);
        personRepository.delete(person);
    }

    @Override
    public void update(Long id, PersonDTO personDTO) {
        Person person = findById(id);

        //TODO use model mapper
        Address address = person.getAddress();
        address.setPostalCode(personDTO.address().postalCode());
        address.setCity(personDTO.address().city());
        address.setNumber(personDTO.address().number());
        address.setCountry(personDTO.address().country());
        address.setNeighbourhood(personDTO.address().neighbourhood());
        address.setStreet(personDTO.address().street());

        person.setDocumentType(personDTO.documentType());
        person.setName(personDTO.name());
        person.setEmail(personDTO.email());
        person.setAddress(address);
        person.setDocumentNumber(personDTO.documentNumber());
        person.setBirthCity(personDTO.birthCity());
        person.setBirthDate(personDTO.birthDate());

        personRepository.save(person);
    }
}
