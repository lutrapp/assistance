package com.evangelizacao_back.assistance.dto;


import java.util.List;

public record AssistanceDTO(
        Long id,
        String guardianName,
        String guardianPhone,
        String motherName,
        String motherMobile,
        AddressDTO address,
        List<ChildDTO> children
) {}
