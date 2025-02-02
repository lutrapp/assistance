package com.evangelizacao_back.assistance.controller;

import com.evangelizacao_back.assistance.dto.AssistanceDTO;
import com.evangelizacao_back.assistance.service.AssistanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AssistanceController {

    private final AssistanceService service;

    public AssistanceController(AssistanceService service) {
        this.service = service;
    }

    @PostMapping("/assistances")
    public ResponseEntity<AssistanceDTO> save(@RequestBody AssistanceDTO assistanceDTO) {
        return ResponseEntity.ok(service.save(assistanceDTO));
    }

    @GetMapping("/assistances/{id}")
    public ResponseEntity<AssistanceDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/assistances")
    public ResponseEntity<List<AssistanceDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
