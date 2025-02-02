package com.evangelizacao_back.assistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "crianca") // Nome da tabela no banco de dados
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_crianca")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dob;

    @Column(name = "alergias")
    private String allergies;

    @Column(name = "deficiencia_fisica")
    private String physicalDisability;

    @Column(name = "doenca")
    private String disease;

    @Column(name = "dificuldade_aprendizagem")
    private String learningDifficulty;

    @Column(name = "medicacao")
    private String medication;

    @Column(name = "comportamento_casa")
    private String homeBehavior;

    @Column(name = "comportamento_escola")
    private String schoolBehavior;

    @Column(name = "alfabetizado")
    private boolean literate;

    @Column(name = "necessita_apoio_escolar")
    private boolean needsSchoolSupport;

    @Column(name = "declaracao_vacinacao")
    private boolean vaccinationDeclaration;

    @Column(name = "saneamento_basico")
    private boolean basicSanitation;

    @Column(name = "informacoes_adicionais")
    private String additionalInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_assistencia") // Chave estrangeira para a tabela assistência
    private Assistance assistance;
}
