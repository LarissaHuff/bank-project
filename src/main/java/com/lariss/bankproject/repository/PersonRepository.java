package com.lariss.bankproject.repository;

import com.lariss.bankproject.enumeration.DocumentType;
import com.lariss.bankproject.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAllByNameContainingIgnoreCase(String name);
    Person findByDocumentTypeAndDocumentNumber(DocumentType type, String documentNumber);



}
