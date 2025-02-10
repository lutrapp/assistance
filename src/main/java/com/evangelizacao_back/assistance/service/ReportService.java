package com.evangelizacao_back.assistance.service;


import com.evangelizacao_back.assistance.mapper.AssistanceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {

    private final AssistanceService assistanceService;
    private final AssistanceMapper assistanceMapper;
}