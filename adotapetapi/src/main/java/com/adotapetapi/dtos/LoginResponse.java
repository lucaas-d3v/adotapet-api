package com.adotapetapi.dtos;

public class LoginResponse {
    private String token;
    private String tipo = "Bearer";
    private Long id;
    private String email;
    private String nome;

    public LoginResponse(String token, Long id, String email, String nome) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.nome = nome;
    }

    // Getters e Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getTipo() { return tipo; }
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getNome() { return nome; }
}