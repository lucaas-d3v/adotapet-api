package com.adotapetapi.controllers;


import com.adotapetapi.models.Cuidador;
import com.adotapetapi.models.Post;
import com.adotapetapi.services.CuidadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cuidador")
public class CuidadorControler {

    @Autowired
    private CuidadorService cuidadorService;

    @GetMapping("/{id}")
    public ResponseEntity<Cuidador> buscarPorId(@PathVariable Long id) {
        return cuidadorService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public CuidadorControler(){}


    @PostMapping("/cadastrar")
    public ResponseEntity<Cuidador> criar(@RequestBody @Valid Cuidador cuidador) {
        System.out.println("=== RECEBIDO ===");
        System.out.println("Nome: " + cuidador.getNomeCuidador());
        System.out.println("Email: " + cuidador.getEmailCuidador());
        System.out.println("Senha: " + cuidador.getSenhaCuidador());

        Cuidador salvo = cuidadorService.saveCuidador(cuidador);

        System.out.println("=== SALVO ===");
        System.out.println("ID: " + salvo.getIdCuidador());

        URI location = URI.create("/cuidador/" + salvo.getIdCuidador());
        return ResponseEntity.created(location).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Cuidador>> listarTodos() {
        List<Cuidador> cuidadores = cuidadorService.listarCuidadores();
        if (cuidadores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cuidadores);
    }

}
