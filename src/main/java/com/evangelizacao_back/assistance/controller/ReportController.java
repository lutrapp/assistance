package com.evangelizacao_back.assistance.controller;

import com.evangelizacao_back.assistance.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/generate")
    public ResponseEntity<StreamingResponseBody> generateReport(
            @RequestParam String cycle,
            @RequestParam String reportType) {

        // Configura os cabeçalhos da resposta
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "relatorio_ciclo_" + cycle + ".pdf");

        // Cria um StreamingResponseBody para gerar o PDF
        StreamingResponseBody responseBody = outputStream -> {
//            reportService.generateReportForCycle(cycle, reportType,outputStream);
        };

        // Retorna a resposta com o StreamingResponseBody
        return ResponseEntity.ok()
                .headers(headers)
                .body(responseBody);
    }
}