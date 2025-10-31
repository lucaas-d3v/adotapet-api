package com.adotapetapi.services;

import com.adotapetapi.models.Cuidador;
import com.adotapetapi.repositorys.CuidadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class CuidadorService {

    @Autowired
    private CuidadorRepository cuidadorRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public List<Cuidador> listarCuidadores(){
        return cuidadorRepository.findAll();
    }

    public Optional<Cuidador> findById(long id){
        return cuidadorRepository.findById(id);
    }

    public Optional<Cuidador> findByEmail(String emailCuidador){
        return cuidadorRepository.findByEmailCuidador(emailCuidador);
    }

    public Optional<String> getSenhaByEmail(String emailCuidador){
        return cuidadorRepository.getSenhaByEmail(emailCuidador);
    }

    public Cuidador saveCuidador(Cuidador cuidador){
        String hashSenha = encoder.encode(cuidador.getSenhaCuidador());
        cuidador.setSenhaCuidador(hashSenha);

        return cuidadorRepository.save(cuidador);
    }

    public void deletarCuidador(long id){
        cuidadorRepository.deleteById(id);
    }

    public long getTotalPetsCadastrados(Cuidador cuidador){
        return cuidador.getPets().size();
    }
}
