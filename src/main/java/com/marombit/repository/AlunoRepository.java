package com.marombit.repository;

import com.marombit.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    boolean existsByCpf(String cpf);
}
