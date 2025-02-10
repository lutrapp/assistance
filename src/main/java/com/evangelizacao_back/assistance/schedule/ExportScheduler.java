package com.evangelizacao_back.assistance.schedule;

import com.evangelizacao_back.assistance.service.ExportService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

@Configuration
@EnableScheduling
public class ExportScheduler {

    private final ExportService exportService;

    public ExportScheduler(ExportService exportService) {
        this.exportService = exportService;
    }

    @Scheduled(cron = "0 0 8 * * ?") // Executa todos os dias às 8h
    public void scheduleExport() {
        try {
            exportService.exportAssistanceToCsv();
            System.out.println("Exportação concluída com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao exportar dados: " + e.getMessage());
        }
    }
}
