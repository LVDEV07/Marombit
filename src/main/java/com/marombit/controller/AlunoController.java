package com.marombit.controller;

import com.marombit.model.Aluno;
import com.marombit.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @GetMapping
    public List<Aluno> listarTodos(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno){
        var salvo = repository.save(aluno);
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @RequestBody Aluno aluno){
        Optional<Aluno> alunoExist = repository.findById(id);

        if (alunoExist.isPresent()){
            Aluno alunoAtualizado = alunoExist.get();
            alunoAtualizado.setNome(aluno.getNome());
            alunoAtualizado.setCpf(aluno.getCpf());
            alunoAtualizado.setDtNascimento(aluno.getDtNascimento());
            alunoAtualizado.setPlano(aluno.getPlano());

            Aluno salvo = repository.save(alunoAtualizado);
            return ResponseEntity.ok(salvo);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> Deletar(@PathVariable Long id) {

        if (!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);


        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarAlunoPorID (@PathVariable Long id){
        Optional<Aluno> alunoExist = repository.findById(id);

        return alunoExist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());

    }

    @GetMapping("/{id}/status")
    public ResponseEntity<String> verificarMatricula (@PathVariable Long id){
        Optional<Aluno> alunoExist = repository.findById(id);

        if (!alunoExist.isPresent()){
            return ResponseEntity.noContent().build();
        }

        if (alunoExist.get().getMatriculaAtiva()) {
                return ResponseEntity.ok("Matricula_Ativa");
        }

        return ResponseEntity.ok("Matricula_Inativa");

    }
}
