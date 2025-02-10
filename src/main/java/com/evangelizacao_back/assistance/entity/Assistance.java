package com.evangelizacao_back.assistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "assistencia") // Nome da tabela no banco
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Assistance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_responsavel", length = 255)
    private String guardianName;

    @Column(name = "telefone_responsavel", length = 20)
    private String guardianPhone;

    @Column(name = "nome_mae", length = 255)
    private String motherName;

    @Column(name = "telefone_mae", length = 20)
    private String motherPhone;

    @Column(name = "email", length = 255)
    private String email;

    // Endereço dentro da entidade Assistance
    @Column(name = "rua", length = 255)
    private String street;

    @Column(name = "cep", length = 20)
    private String zip;

    @Column(name = "numero", length = 20)
    private String number;

    @Column(name = "bairro", length = 255)
    private String neighborhood;

    @Column(name = "complemento", length = 255)
    private String complement;

    @Column(name = "cidade", length = 255)
    private String city;

    @Column(name = "estado", length = 2)
    private String state;

    // Relacionamento com os filhos
    @OneToMany(mappedBy = "assistance", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Child> children;
}
