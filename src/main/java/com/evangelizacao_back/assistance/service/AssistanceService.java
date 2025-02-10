package com.evangelizacao_back.assistance.service;

import com.evangelizacao_back.assistance.dto.AssistanceDTO;
import com.evangelizacao_back.assistance.entity.Assistance;
import com.evangelizacao_back.assistance.mapper.AssistanceMapper;
import com.evangelizacao_back.assistance.repository.AssistanceRepository;
import com.evangelizacao_back.assistance.validation.AssistanceValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssistanceService {

    private final AssistanceRepository repository;
    private final AssistanceMapper mapper;
    private final AssistanceValidator validator;

    @Transactional
    public AssistanceDTO save(AssistanceDTO assistanceDTO) {
        validator.validate(assistanceDTO);

        Assistance assistance = mapper.toEntity(assistanceDTO);
        Assistance savedAssistance = repository.save(assistance);

        return mapper.toDTO(savedAssistance);
    }

    @Transactional(value = jakarta.transaction.Transactional.TxType.NOT_SUPPORTED)
    public AssistanceDTO findById(Long id) {
        return repository.findByIdWithChildren(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Assistência não encontrada"));
    }

    @Transactional(value = jakarta.transaction.Transactional.TxType.NOT_SUPPORTED)
    public List<AssistanceDTO> findAll() {
        return repository.findAllWithChildren().stream()
                .map(mapper::toDTO)
                .toList();
    }
    public boolean isChildAlreadyRegistered(String guardianName, String guardianPhone, String childName, LocalDate dob) {
        return repository.existsByGuardianNameAndGuardianPhoneAndChildren_NameAndChildren_Dob(
                guardianName,
                guardianPhone,
                childName,
                dob
        );
    }

    @Transactional(value = jakarta.transaction.Transactional.TxType.NOT_SUPPORTED)
    public List<AssistanceDTO> findByCycle(String cycle) {
        return repository.findByCycle(cycle).stream()
                .map(mapper::toDTO)
                .toList();
    }
}
