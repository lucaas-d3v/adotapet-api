package com.adotapetapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

@Entity
@Table(name = "foto_post")
public class FotoPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFotoPost;

    @NotNull(message = "Foto deve estar associada a um post")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idPost", nullable = false)
    @JsonIgnore // Evita recursão infinita ao serializar JSON
    private Post post;

    @NotNull(message = "URL da foto é obrigatória")
    @Size(max = 500, message = "URL deve ter no máximo 500 caracteres")
    @Column(nullable = false, length = 500)
    private String urlFoto;

    // Ordem de exibição da foto no post
    @Column(nullable = false)
    private Integer ordemFoto = 0;

    // Data de upload
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataUpload;

    // A data é sempre a da mesma do upload.
    @PrePersist
    protected void onCreate() {
        if (dataUpload == null) {
            dataUpload = LocalDateTime.now();
        }
    }

    public FotoPost() {}

    public FotoPost(Post post, String urlFoto, Integer ordemFoto) {
        this.post = post;
        this.urlFoto = urlFoto;
        this.ordemFoto = ordemFoto;
    }

    public FotoPost(Post post, String urlFoto) {
        this(post, urlFoto, 0);
    }

    public Long getIdFotoPost() {
        return idFotoPost;
    }

    public void setIdFotoPost(Long idFotoPost) {
        this.idFotoPost = idFotoPost;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public Integer getOrdemFoto() {
        return ordemFoto;
    }

    public void setOrdemFoto(Integer ordemFoto) {
        this.ordemFoto = ordemFoto;
    }

    public LocalDateTime getDataUpload() {
        return dataUpload;
    }

    public void setDataUpload(LocalDateTime dataUpload) {
        this.dataUpload = dataUpload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FotoPost)) return false;
        FotoPost fotoPost = (FotoPost) o;
        return idFotoPost != null && idFotoPost.equals(fotoPost.idFotoPost);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}