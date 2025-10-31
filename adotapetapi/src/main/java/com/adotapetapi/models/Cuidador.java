package com.adotapetapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cuidador") // Nome da tabela
public class Cuidador {

    @Id // Fala que é a PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SERIAL / AUTO_INCREMENT
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idCuidador;

    @NotBlank(message = "Nome é obrigatório") // Não pode ser vazio, (EX: "", " ", null) não é aceito
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres") // VARCHAR(100)
    @Column(nullable = false, length = 100)
    @JsonProperty("nomeCuidador")
    private String nomeCuidador;

    @Column(length = 500)
    @JsonProperty("urlFotoCuidador")
    private String fotoCuidador; // URL da foto de perfil

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Column(nullable = false, unique = true, length = 150)
    @JsonProperty("emailCuidador")
    private String emailCuidador;

    @NotBlank(message = "Telefone é obrigatório")
    @Column(nullable = false)
    @Pattern(regexp = "\\(\\d{2}\\) 9\\d{4}-\\d{4}", message = "Formato de telefone inválido, tente algo como: '(83) 9xxxx-xxxx'")
    @JsonProperty("telefone")
    private String telefone;

    @Column(nullable = false)
    @JsonProperty("isWhatsapp")
    private Boolean isWhatsapp = false;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    @Column(nullable = false, length = 255)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senhaCuidador;
                                                                // Garante que se apague cuidador, os dados que dependem dele, não fiquem sen dono
    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.ALL, orphanRemoval = true)
    //                                              ^
    //                                              |
    //                  Caso apague o cuidador, todos os dados deles vão embora junto

    @JsonIgnore // Evita recursão infinita no JSON
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Pet> pets = new ArrayList<>();

    public Cuidador() {}

    public Cuidador(String nomeCuidador, String emailCuidador, String senhaCuidador) {
        this.nomeCuidador = nomeCuidador;
        this.emailCuidador = emailCuidador;
        this.senhaCuidador = senhaCuidador;
    }

    public Long getIdCuidador() {
        return idCuidador;
    }

    public void setIdCuidador(Long idCuidador) {
        this.idCuidador = idCuidador;
    }

    public String getNomeCuidador() {
        return nomeCuidador;
    }

    public void setNomeCuidador(String nomeCuidador) {
        this.nomeCuidador = nomeCuidador;
    }

    public String getFotoCuidador() {
        return fotoCuidador;
    }

    public void setFotoCuidador(String fotoCuidador) {
        this.fotoCuidador = fotoCuidador;
    }

    public String getEmailCuidador() {
        return emailCuidador;
    }

    public void setEmailCuidador(String emailCuidador) {
        this.emailCuidador = emailCuidador;
    }

    public String getSenhaCuidador() {
        return senhaCuidador;
    }

    public void setSenhaCuidador(String senhaCuidador) {
        this.senhaCuidador = senhaCuidador;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    // Métodos auxiliares para manter sincronização bidirecional
    public void addPet(Pet pet) {
        pets.add(pet);
        pet.setCuidador(this);
    }

    public void removePet(Pet pet) {
        pets.remove(pet);
        pet.setCuidador(null);
    }

    public void addPost(Post post) {
        posts.add(post);
        post.setCuidador(this);
    }

    public void removePost(Post post) {
        posts.remove(post);
        post.setCuidador(null);
    }
}
