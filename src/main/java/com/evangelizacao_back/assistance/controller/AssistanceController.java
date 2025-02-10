package com.evangelizacao_back.assistance.controller;

import com.evangelizacao_back.assistance.dto.AssistanceDTO;
import com.evangelizacao_back.assistance.dto.ChildDTO;
import com.evangelizacao_back.assistance.mapper.AssistanceMapper;
import com.evangelizacao_back.assistance.service.AssistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:3000")
public class AssistanceController {

    private final AssistanceService service;
    private final AssistanceMapper assistanceMapper;

    @PostMapping("/assistances")
    public ResponseEntity<AssistanceDTO> save(@RequestBody AssistanceDTO assistanceDTO) {
        for (ChildDTO childDTO : assistanceDTO.children()) {
            boolean alreadyExists = service.isChildAlreadyRegistered(
                    assistanceDTO.guardianName(),
                    assistanceDTO.guardianPhone(),
                    childDTO.name(),
                    childDTO.dob()
            );

            if (alreadyExists) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
        }

        AssistanceDTO savedDTO = service.save(assistanceDTO); // Passa o DTO diretamente
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDTO);
    }

    @GetMapping("/assistances/{id}")
    public ResponseEntity<AssistanceDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/assistances")
    public ResponseEntity<List<AssistanceDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/assistances/cycle/{cycle}")
    public ResponseEntity<List<AssistanceDTO>> getByCycle(@PathVariable String cycle) {
        return ResponseEntity.ok(service.findByCycle(cycle));
    }

}
