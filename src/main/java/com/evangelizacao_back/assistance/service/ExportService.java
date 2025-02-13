package com.evangelizacao_back.assistance.service;

import com.evangelizacao_back.assistance.dto.AssistanceDTO;
import com.evangelizacao_back.assistance.dto.ChildDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ExportService {

    private final AssistanceService assistanceService;

    public ExportService(AssistanceService assistanceService) {
        this.assistanceService = assistanceService;
    }

    public void exportAssistanceToCsv() throws IOException {
        List<AssistanceDTO> assistances = assistanceService.findAll();

        StringBuilder csvContent = new StringBuilder();
        csvContent.append("Nome do Responsável,Telefone do Responsável,Nome da Criança,Data de Nascimento\n");

        for (AssistanceDTO assistance : assistances) {
            for (ChildDTO child : assistance.children()) {
                csvContent.append(assistance.guardianName()).append(",")
                        .append(assistance.guardianPhone()).append(",")
                        .append(child.name()).append(",")
                        .append(child.dob()).append("\n");
            }
        }

        // Salva o CSV em um arquivo
        Path path = Paths.get("assistance_export.csv");
        Files.writeString(path, csvContent.toString());
    }
}
