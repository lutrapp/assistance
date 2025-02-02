package com.evangelizacao_back.assistance.repository;

import com.evangelizacao_back.assistance.entity.Assistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistanceRepository extends JpaRepository<Assistance, Long> {}
