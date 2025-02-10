package com.evangelizacao_back.assistance.repository;

import com.evangelizacao_back.assistance.entity.Assistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AssistanceRepository extends JpaRepository<Assistance, Long> {

    @Query("SELECT a FROM Assistance a LEFT JOIN FETCH a.children WHERE a.id = :id")
    Optional<Assistance> findByIdWithChildren(@Param("id") Long id);

    @Query("SELECT DISTINCT a FROM Assistance a LEFT JOIN FETCH a.children")
    List<Assistance> findAllWithChildren();

    boolean existsByGuardianNameAndGuardianPhoneAndChildren_NameAndChildren_Dob(
            String guardianName,
            String guardianPhone,
            String childName,
            LocalDate dob
    );

    @Query("SELECT DISTINCT a FROM Assistance a JOIN FETCH a.children c WHERE c.cycle = :cycle")
    List<Assistance> findByCycle(@Param("cycle") String cycle);
}
