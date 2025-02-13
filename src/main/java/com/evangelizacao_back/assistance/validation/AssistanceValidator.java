package com.evangelizacao_back.assistance.validation;

import com.evangelizacao_back.assistance.dto.AssistanceDTO;
import org.springframework.stereotype.Component;

@Component
public class AssistanceValidator {

    public void validate(AssistanceDTO dto) {
        if (dto.guardianName() == null || dto.guardianName().isBlank()) {
            throw new IllegalArgumentException("O nome do responsável é obrigatório.");
        }
        if (dto.guardianPhone() == null || dto.guardianPhone().isBlank()) {
            throw new IllegalArgumentException("O telefone do responsável é obrigatório.");
        }
    }
}
