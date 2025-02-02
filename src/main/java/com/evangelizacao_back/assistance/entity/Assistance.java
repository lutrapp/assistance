package com.evangelizacao_back.assistance.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "assistencia") // Nome da tabela no banco de dados
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Assistance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_assistencia")
    private Long id;

    @Column(name = "nome_responsavel", nullable = false)
    private String responsibleName;

    @Column(name = "telefone_responsavel", nullable = false)
    private String responsiblePhone;

    @Column(name = "nome_mae")
    private String motherName;

    @Column(name = "celular_mae")
    private String motherMobile;

    @Column(name = "rua", nullable = false)
    private String street;

    @Column(name = "cep", nullable = false)
    private String zip;

    @Column(name = "numero", nullable = false)
    private String number;

    @Column(name = "bairro", nullable = false)
    private String neighborhood;

    @Column(name = "complemento")
    private String complement;

    @Column(name = "cidade", nullable = false)
    private String city;

    @Column(name = "estado", nullable = false)
    private String state;

    @OneToMany(mappedBy = "assistance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> children;

    @CreationTimestamp
    @Column(name = "criado_em", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "atualizado_em")
    private LocalDateTime updatedAt;
}
