package com.adotapetapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.engine.spi.ManagedEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;

    @NotNull(message = "Post deve referenciar um pet")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPet", nullable = false)
    private Pet pet;

    @NotNull(message = "Post deve ter um cuidador")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCuidador", nullable = false)
    private Cuidador cuidador;

    @ElementCollection // Indica que não é uma entidade, apenas uma coleção de elementos tipo enum.
    @CollectionTable(name = "post_fotos", joinColumns = @JoinColumn(name = "idPost"))
    @Column(name = "url_foto", length = 500)
    @OrderColumn(name = "ordem") // Mantém a ordem de inserção
    @Size(min = 1, max = 5)
    @NotNull(message = "O post precisa ter no mínimo uma imagem")
    private List<String> fotosPet = new ArrayList<>();

    @NotBlank(message = "Título é obrigatório")
    @Size(max = 100, message = "Título deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String tituloPost;

    @Size(max = 2000, message = "Descrição deve ter no máximo 2000 caracteres")
    @Column(columnDefinition = "TEXT")
    private String descricaoPost;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column
    private LocalDateTime dataAtualizacao;

    // Métodos do ciclo de vida
    @PrePersist // Executa o método abaixo antes de salvar no banco
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
        dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate // Chamado antes de atualizar no banco
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }

    // Construtores
    public Post() {}

    public Post(Pet pet, Cuidador cuidador, String tituloPost) {
        this.pet = pet;
        this.cuidador = cuidador;
        this.tituloPost = tituloPost;
    }

    // Getters e Setters
    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Cuidador getCuidador() {
        return cuidador;
    }

    public void setCuidador(Cuidador cuidador) {
        this.cuidador = cuidador;
    }

    public List<String> getFotosPet() {
        return fotosPet;
    }

    public void setFotosPet(List<String> fotosPet) {
        this.fotosPet = fotosPet;
    }

    public String getTituloPost() {
        return tituloPost;
    }

    public void setTituloPost(String tituloPost) {
        this.tituloPost = tituloPost;
    }

    public String getDescricaoPost() {
        return descricaoPost;
    }

    public void setDescricaoPost(String descricaoPost) {
        this.descricaoPost = descricaoPost;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    // Métodos auxiliares

    // Adiciona uma única foto ao post

    public void addFoto(String urlFoto) {
        if (urlFoto != null && !urlFoto.trim().isEmpty()) {
            this.fotosPet.add(urlFoto);
        }
    }

    // Adiciona múltiplas fotos ao post

    public void addFotos(List<String> urls) {
        if (urls != null && !urls.isEmpty()) {
            urls.stream()
                    .filter(url -> url != null && !url.trim().isEmpty())
                    .forEach(this.fotosPet::add);
        }
    }

    // Remove uma foto específica

    public void removeFoto(String urlFoto) {
        this.fotosPet.remove(urlFoto);
    }

    // Remove todas as fotos

    public void clearFotos() {
        this.fotosPet.clear();
    }

    // Verifica se o post tem fotos

    public boolean hasFotos() {
        return !this.fotosPet.isEmpty();
    }

    // Retorna a quantidade de fotos

    public int getQuantidadeFotos() {
        return this.fotosPet.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return idPost != null && idPost.equals(post.idPost);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Post{" +
                "idPost=" + idPost +
                ", tituloPost='" + tituloPost + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", quantidadeFotos=" + getQuantidadeFotos() +
                '}';
    }
}