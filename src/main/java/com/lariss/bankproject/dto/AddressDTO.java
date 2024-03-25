package com.lariss.bankproject.dto;

import com.lariss.bankproject.model.Address;

public record AddressDTO(
        String street,
        Integer number,
        String neighbourhood,
        String city,
        String country,
        String postalCode) {

    public AddressDTO(Address address) {
        this(address.getStreet(), address.getNumber(), address.getNeighbourhood(),
                address.getCity(), address.getCountry(), address.getPostalCode());
    }
}
