package com.evangelizacao_back.assistance.repository;

import com.evangelizacao_back.assistance.entity.Assistance;
import com.evangelizacao_back.assistance.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {}
