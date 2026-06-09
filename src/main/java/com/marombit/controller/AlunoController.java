package com.marombit.controller;

import com.marombit.exception.CpfJaCadastradoException;
import com.marombit.model.Aluno;
import com.marombit.repository.AlunoRepository;
import com.marombit.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<Aluno> listarTodos(){
        return alunoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@Valid @RequestBody Aluno aluno){
        Aluno salvo = alunoService.criarAluno(aluno);
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @Valid @RequestBody Aluno aluno){
        Aluno atualizado = alunoService.atualizar(id,aluno);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Deletar(@PathVariable Long id) {
        alunoService.Deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarAlunoPorID (@PathVariable Long id){
       return ResponseEntity.ok(alunoService.buscarAlunoPorID(id));
    }


    @GetMapping("/{id}/status")
    public ResponseEntity<String> verificarMatricula (@PathVariable Long id){
        Aluno alunoExist = alunoService.buscarAlunoPorID(id);


        if (alunoExist.getMatriculaAtiva()) {
                return ResponseEntity.ok("Matricula_Ativa");
        }

        return ResponseEntity.ok("Matricula_Inativa");

    }
}
