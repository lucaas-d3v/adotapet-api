package com.adotapetapi.services;


import com.adotapetapi.dtos.LoginRequest;
import com.adotapetapi.dtos.LoginResponse;
import com.adotapetapi.models.Cuidador;
import com.adotapetapi.repositorys.CuidadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private CuidadorRepository cuidadorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtU jwtUtil;

    public LoginResponse login(LoginRequest request) {
        Optional<Cuidador> cuidador = Optional.ofNullable(cuidadorRepository.findCuidadorByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")));

        if (!passwordEncoder.matches(request.getSenha(), cuidador.get().getSenhaCuidador())) {
            throw new RuntimeException("Senha incorreta");
        }

        String token = jwtUtil.generateToken(usuario.getEmail());

        return new LoginResponse(
                token,
                cuidador.get().getIdCuidador(),
                cuidador.get().getEmailCuidador(),
                cuidador.get().getNomeCuidador()
        );
    }
}