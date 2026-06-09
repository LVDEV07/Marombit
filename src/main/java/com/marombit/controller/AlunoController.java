package com.marombit.controller;

import com.marombit.exception.CpfJaCadastradoException;
import com.marombit.model.Aluno;
import com.marombit.repository.AlunoRepository;
import com.marombit.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
@Tag(name = "Alunos", description = "Endpoints de gerenciamento de alunos da academia" )
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Operation(summary = "Listar todos os alunos", description = "Retorna uma lista completa de todos alunos cadastrados")
    @GetMapping
    public List<Aluno> listarTodos(){
        return alunoService.listarTodos();
    }

    @Operation(summary = "Cadastra novos Alunos", description = "Cadastra um novo aluno na academia")
    @ApiResponse(responseCode = "200", description = "Retorna o aluno salvo")
    @ApiResponse(responseCode = "409", description = "CPF já cadastrado")
    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@Valid @RequestBody Aluno aluno){
        Aluno salvo = alunoService.criarAluno(aluno);
        return ResponseEntity.status(201).body(salvo);
    }

    @Operation(summary = "Atualiza os dados dos alunos", description = "Atualiza todos os dados de um aluno existente")
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @Valid @RequestBody Aluno aluno){
        Aluno atualizado = alunoService.atualizar(id,aluno);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Deletar Aluno", description = "Deleta um Aluno por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Deletar(@PathVariable Long id) {
        alunoService.Deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar um aluno por id", description = "Busca um usuario existente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarAlunoPorID (@PathVariable Long id){
        return ResponseEntity.ok(alunoService.buscarAlunoPorID(id));
    }

    @Operation(summary = "Verificar status da Matricula", description = "Verifica se a matricula do Aluno está ativa")
    @GetMapping("/{id}/status")
    public ResponseEntity<String> verificarMatricula (@PathVariable Long id){
        Aluno alunoExist = alunoService.buscarAlunoPorID(id);


        if (alunoExist.getMatriculaAtiva()) {
                return ResponseEntity.ok("Matricula_Ativa");
        }

        return ResponseEntity.ok("Matricula_Inativa");

    }
}
