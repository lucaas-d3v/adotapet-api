package com.adotapetapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPet;

    @NotBlank(message = "O Nome do pet não pode estar vazio.")
    private String nomePet;

    // O Cuidador pode não saber a idade do pet então sem @NotNull ou @NotBlank

    @Digits(integer = 3, fraction = 0) // Limite de 0 a 999 meses por exmplo
    @PositiveOrZero // Tem que ser positivo, pode ser 0 pois o cuidador pode não saber a idade do pet
    private Integer idadePet; // Em meses.

    @NotBlank(message = "A raça do pet não pode estar vazia.")
    @Size(min = 3, max = 100)
    private String racaPet;

    @NotNull
    private String localPet; // Endereço.

    @ManyToOne(fetch = FetchType.LAZY) // So carrega os post quando chamado, (EX: getCuidador())
    @JoinColumn(name = "idCuidador") // nome da FK no banco
    private Cuidador cuidador; // referência à entidade Cuidador

    public Long getIdPet() {
        return idPet;
    }

    public void setIdPet(Long id) {
        this.idPet = id;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public Integer getIdadePet() {
        return idadePet;
    }

    public void setIdadePet(Integer idadePet) {
        this.idadePet = idadePet;
    }

    public String getRacaPet() {
        return racaPet;
    }

    public void setRacaPet(String racaPet) {
        this.racaPet = racaPet;
    }

    public String getLocalPet() {
        return localPet;
    }

    public void setLocalPet(String localPet) {
        this.localPet = localPet;
    }

    public Cuidador getCuidador() {
        return cuidador;
    }

    public void setCuidador(Cuidador cuidador) {
        this.cuidador = cuidador;
    }
}
