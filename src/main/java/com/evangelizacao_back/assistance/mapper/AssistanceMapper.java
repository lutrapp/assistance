package com.evangelizacao_back.assistance.mapper;

import com.evangelizacao_back.assistance.dto.AddressDTO;
import com.evangelizacao_back.assistance.dto.AssistanceDTO;
import com.evangelizacao_back.assistance.dto.ChildDTO;
import com.evangelizacao_back.assistance.entity.Assistance;
import com.evangelizacao_back.assistance.entity.Child;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class AssistanceMapper {

    public Assistance toEntity(AssistanceDTO dto) {
        Assistance assistance = new Assistance();
        assistance.setId(dto.id());
        assistance.setGuardianName(dto.guardianName());
        assistance.setGuardianPhone(dto.guardianPhone());
        assistance.setMotherName(dto.motherName());
        assistance.setMotherPhone(dto.motherMobile());

        if (dto.address() != null) {
            assistance.setStreet(dto.address().street());
            assistance.setZip(dto.address().zip());
            assistance.setNumber(dto.address().number());
            assistance.setNeighborhood(dto.address().neighborhood());
            assistance.setComplement(dto.address().complement());
            assistance.setCity(dto.address().city());
            assistance.setState(dto.address().state());
        }

        List<Child> children = dto.children() == null ?
                Collections.emptyList() :
                dto.children().stream().map(childDto -> toChildEntity(childDto, assistance)).toList();

        assistance.setChildren(children); // Associando os filhos à assistência

        return assistance;
    }

    public AssistanceDTO toDTO(Assistance entity) {
        List<ChildDTO> children = entity.getChildren() == null ?
                Collections.emptyList() :
                entity.getChildren().stream().map(this::toChildDTO).toList();

        return new AssistanceDTO(
                entity.getId(),
                entity.getGuardianName(),
                entity.getGuardianPhone(),
                entity.getMotherName(),
                entity.getMotherPhone(),
                new AddressDTO(
                        entity.getStreet(),
                        entity.getZip(),
                        entity.getNumber(),
                        entity.getNeighborhood(),
                        entity.getComplement(),
                        entity.getCity(),
                        entity.getState()
                ),
                children
        );
    }

    private Child toChildEntity(ChildDTO dto, Assistance assistance) { // Corrigido o tipo do segundo parâmetro
        Child child = new Child();
        child.setName(dto.name());
        child.setDob(dto.dob());
        child.setCycle(dto.cycle());
        child.setAllergies(dto.allergies());
        child.setPhysicalDisability(dto.physicalDisability());
        child.setDisease(dto.disease());
        child.setLearningDifficulty(dto.learningDifficulty());
        child.setMedication(dto.medication());
        child.setHomeBehavior(dto.homeBehavior());
        child.setSchoolBehavior(dto.schoolBehavior());
        child.setLiterate(dto.literate());
        child.setNeedsSchoolSupport(dto.needsSchoolSupport());
        child.setVaccinationDeclaration(dto.vaccinationDeclaration());
        child.setBasicSanitation(dto.basicSanitation());
        child.setAdditionalInfo(dto.additionalInfo());
        child.setAssistance(assistance); // Agora está correto

        return child;
    }

    private ChildDTO toChildDTO(Child child) {
        return new ChildDTO(
                child.getId(),
                child.getName(),
                child.getDob(),
                child.getCycle(),
                child.getAllergies(),
                child.getPhysicalDisability(),
                child.getDisease(),
                child.getLearningDifficulty(),
                child.getMedication(),
                child.getHomeBehavior(),
                child.getSchoolBehavior(),
                child.isLiterate(),
                child.isNeedsSchoolSupport(),
                child.isVaccinationDeclaration(),
                child.isBasicSanitation(),
                child.getAdditionalInfo()
        );
    }
}
