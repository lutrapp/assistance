package com.evangelizacao_back.assistance.dto;

public record AddressDTO(
        String street,
        String zip,
        String number,
        String neighborhood,
        String complement,
        String city,
        String state
) {}
