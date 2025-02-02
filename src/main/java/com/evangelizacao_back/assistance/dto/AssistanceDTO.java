package com.evangelizacao_back.assistance.dto;


import java.util.List;

public record AssistanceDTO(
        Long id,
        String responsibleName,
        String responsiblePhone,
        String motherName,
//        String motherPhone,
        String motherMobile,
        AddressDTO address,
        List<ChildDTO> children
) {}
