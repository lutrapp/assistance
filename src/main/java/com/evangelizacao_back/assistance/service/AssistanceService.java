package com.evangelizacao_back.assistance.service;

import com.evangelizacao_back.assistance.dto.AddressDTO;
import com.evangelizacao_back.assistance.dto.AssistanceDTO;
import com.evangelizacao_back.assistance.dto.ChildDTO;
import com.evangelizacao_back.assistance.entity.Assistance;
import com.evangelizacao_back.assistance.entity.Child;
import com.evangelizacao_back.assistance.enumeration.CycleEnum;
import com.evangelizacao_back.assistance.repository.AssistanceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssistanceService {

    private final AssistanceRepository repository;

    @Transactional
    public Assistance saveAssistance(Assistance assistance) {
        return repository.save(assistance);
    }

    @Transactional
    public AssistanceDTO save(AssistanceDTO assistanceDTO) {
        Assistance assistance = toEntity(assistanceDTO);

        assistance.getChildren().forEach(child -> child.setCycle(determineCiclo(child.getDob())));

        Assistance savedAssistance = repository.save(assistance);
        return toDTO(savedAssistance);
    }

    @Transactional
    public AssistanceDTO findById(Long id) {
        Assistance assistance = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Assistência não encontrada"));
        return toDTO(assistance);
    }

    @Transactional
    public List<AssistanceDTO> findAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private Assistance toEntity(AssistanceDTO dto) {
        Assistance assistance = new Assistance();
        assistance.setId(dto.id());
        assistance.setResponsibleName(dto.responsibleName());
        assistance.setResponsiblePhone(dto.responsiblePhone());
        assistance.setMotherName(dto.motherName());
        assistance.setMotherMobile(dto.motherMobile());

        if (dto.address() != null) {
            assistance.setStreet(dto.address().street());
            assistance.setZip(dto.address().zip());
            assistance.setNumber(dto.address().number());
            assistance.setNeighborhood(dto.address().neighborhood());
            assistance.setComplement(dto.address().complement());
            assistance.setCity(dto.address().city());
            assistance.setState(dto.address().state());
        }

        if (dto.children() != null && !dto.children().isEmpty()) {
            List<Child> children = dto.children().stream()
                    .map(childDTO -> {
                        Child child = new Child();
                        child.setName(childDTO.name());
                        child.setDob(childDTO.dob());
                        child.setCycle(determineCiclo(childDTO.dob()));
                        child.setAllergies(childDTO.allergies());
                        child.setPhysicalDisability(childDTO.physicalDisability());
                        child.setDisease(childDTO.disease());
                        child.setLearningDifficulty(childDTO.learningDifficulty());
                        child.setMedication(childDTO.medication());
                        child.setHomeBehavior(childDTO.homeBehavior());
                        child.setSchoolBehavior(childDTO.schoolBehavior());
                        child.setLiterate(childDTO.literate());
                        child.setNeedsSchoolSupport(childDTO.needsSchoolSupport());
                        child.setVaccinationDeclaration(childDTO.vaccinationDeclaration());
                        child.setBasicSanitation(childDTO.basicSanitation());
                        child.setAdditionalInfo(childDTO.additionalInfo());
                        child.setAssistance(assistance);
                        return child;
                    }).collect(Collectors.toList());
            assistance.setChildren(children);
        } else {
            assistance.setChildren(Collections.emptyList());
        }

        return assistance;
    }

    private AssistanceDTO toDTO(Assistance entity) {
        List<ChildDTO> children = Optional.ofNullable(entity.getChildren())
                .orElse(Collections.emptyList())
                .stream()
                .map(child -> new ChildDTO(
                        child.getId(),
                        child.getName(),
                        child.getDob(),
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
                )).collect(Collectors.toList());

        AddressDTO addressDTO = new AddressDTO(
                entity.getStreet(),
                entity.getZip(),
                entity.getNumber(),
                entity.getNeighborhood(),
                entity.getComplement(),
                entity.getCity(),
                entity.getState()
        );

        return new AssistanceDTO(
                entity.getId(),
                entity.getResponsibleName(),
                entity.getResponsiblePhone(),
                entity.getMotherName(),
                entity.getMotherMobile(),
                addressDTO,
                children
        );
    }

    private CycleEnum determineCiclo(LocalDate dob) {
        if (dob == null) {
            throw new IllegalArgumentException("Data de nascimento não pode ser nula");
        }
        LocalDate referenceDate = LocalDate.of(LocalDate.now().getYear(), Month.MAY, 30);
        int age = Period.between(dob, referenceDate).getYears();
        return CycleEnum.fromAge(age);
    }
}
