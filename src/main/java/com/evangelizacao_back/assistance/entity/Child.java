package com.evangelizacao_back.assistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "crianca") // Nome da tabela no banco
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 255)
    private String name;

    @Column(name = "data_nascimento")
    private LocalDate dob;

    @Column(name = "ciclo", length = 50)
    private String cycle;

    @Column(name = "alergias", columnDefinition = "TEXT")
    private String allergies;

    @Column(name = "deficiencia_fisica", columnDefinition = "TEXT")
    private String physicalDisability;

    @Column(name = "doenca", columnDefinition = "TEXT")
    private String disease;

    @Column(name = "dificuldade_aprendizado", columnDefinition = "TEXT")
    private String learningDifficulty;

    @Column(name = "medicacao", columnDefinition = "TEXT")
    private String medication;

    @Column(name = "comportamento_casa", columnDefinition = "TEXT")
    private String homeBehavior;

    @Column(name = "comportamento_escola", columnDefinition = "TEXT")
    private String schoolBehavior;

    @Column(name = "alfabetizado")
    private boolean literate;

    @Column(name = "precisa_apoio_escolar")
    private boolean needsSchoolSupport;

    @Column(name = "declaracao_vacinacao")
    private boolean vaccinationDeclaration;

    @Column(name = "saneamento_basico")
    private boolean basicSanitation;

    @Column(name = "info_adicional", columnDefinition = "TEXT")
    private String additionalInfo;

    // Relacionamento com Assistance
    @ManyToOne
    @JoinColumn(name = "assistencia_id", nullable = false)
    private Assistance assistance;
}
