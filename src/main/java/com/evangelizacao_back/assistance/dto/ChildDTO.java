package com.evangelizacao_back.assistance.dto;

import com.evangelizacao_back.assistance.enumeration.CycleEnum;

import java.time.LocalDate;

public record ChildDTO(
        Long id,
        String name,
        LocalDate dob, //date of birth
        String allergies,
        String physicalDisability,
        String disease,
        String learningDifficulty,
        String medication,
        String homeBehavior,
        String schoolBehavior,
        boolean literate,
        boolean needsSchoolSupport,
        boolean vaccinationDeclaration,
        boolean basicSanitation,
        String additionalInfo
) {}
