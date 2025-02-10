package com.evangelizacao_back.assistance.controller;

import com.evangelizacao_back.assistance.dto.AssistanceDTO;
import com.evangelizacao_back.assistance.dto.ChildDTO;
import com.evangelizacao_back.assistance.service.AssistanceService;
import jakarta.annotation.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/export")
public class ExportController {

    private final AssistanceService assistanceService;

    public ExportController(AssistanceService assistanceService) {
        this.assistanceService = assistanceService;
    }

    @GetMapping("/assistance-csv")
    public ResponseEntity<Resource> exportAssistanceToCsv() {
        List<AssistanceDTO> assistances = assistanceService.findAll();

        // Cria o conteúdo do CSV
        StringBuilder csvContent = new StringBuilder();
        csvContent.append("Nome do Responsável,Telefone do Responsável,Nome da Criança,Data de Nascimento\n"); // Cabeçalho com os nomes das colunas da planilha

        for (AssistanceDTO assistance : assistances) {
            for (ChildDTO child : assistance.children()) {
                csvContent.append(assistance.guardianName()).append(",")
                        .append(assistance.guardianPhone()).append(",")
                        .append(child.name()).append(",")
                        .append(child.dob()).append("\n");
            }
        }

        // Converte o conteúdo para um Resource (arquivo)
        ByteArrayResource resource = new ByteArrayResource(csvContent.toString().getBytes(StandardCharsets.UTF_8));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=assistance_export.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body((Resource) resource);
    }
}
