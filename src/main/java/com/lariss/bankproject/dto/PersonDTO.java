package com.lariss.bankproject.dto;

import com.lariss.bankproject.enumeration.DocumentType;

import java.time.LocalDate;

public record PersonDTO(
        String name,
        LocalDate birthDate,
        AddressDTO address,
        DocumentType documentType,
        String documentNumber,
        String email,
        String birthCity) {

}
