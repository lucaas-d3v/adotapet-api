package com.adotapetapi.services;

import com.adotapetapi.models.Cuidador;
import com.adotapetapi.models.Pet;
import com.adotapetapi.repositorys.PetsRepository;
import com.adotapetapi.repositorys.CuidadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetsRepository petsRepository;
    private final CuidadorRepository cuidadorRepository;

    public PetService(PetsRepository petsRepository, CuidadorRepository cuidadorRepository) {
        this.petsRepository = petsRepository;
        this.cuidadorRepository = cuidadorRepository;
    }

    // Criar ou atualizar um pet
    public Pet salvarPet(Pet pet) {
        // Aqui você poderia adicionar validações: por exemplo, limitar número de pets por cuidador
        return petsRepository.save(pet);
    }

    // Buscar todos os pets
    public List<Pet> listarTodosPets() {
        return petsRepository.findAll();
    }

    // Buscar pets de um cuidador específico
    public Optional<List<Pet>> buscarPetsPorCuidador(Long idCuidador) {
        Cuidador cuidador = cuidadorRepository.findById(idCuidador)
                .orElseThrow(() -> new RuntimeException("Cuidador não encontrado"));
        return petsRepository.findByCuidador(cuidador);
    }

    // Buscar pet por ID
    public Pet buscarPorId(Long id) {
        return petsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet não encontrado"));
    }

    // Deletar pet
    public void deletarPet(Long id) {
        if (!petsRepository.existsById(id)) {
            throw new RuntimeException("Pet não encontrado");
        }
        petsRepository.deleteById(id);
    }

    // Contar pets de um cuidador
    public long contarPetsDoCuidador(Long idCuidador) {
        Cuidador cuidador = cuidadorRepository.findById(idCuidador)
                .orElseThrow(() -> new RuntimeException("Cuidador não encontrado"));
        return petsRepository.countByCuidador(cuidador);
    }
}
