package com.lariss.bankproject.dto;

import com.lariss.bankproject.enumeration.DocumentType;
import com.lariss.bankproject.model.Person;

import java.time.LocalDate;

public record PersonViewDTO(
        Long id,
        String name,
        LocalDate birthDate,
        AddressDTO address,
        DocumentType documentType,
        String documentNumber,
        String email,
        String birthCity) {

    public PersonViewDTO(Person person) {
        this(person.getId(), person.getName(), person.getBirthDate(), new AddressDTO(person.getAddress()),
                person.getDocumentType(), person.getDocumentNumber(), person.getEmail(), person.getBirthCity());
    }
}
