package com.marombit.controller;

import com.marombit.model.Aluno;
import com.marombit.repository.AlunoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private AlunoRepository repository;

    public List<Aluno> listarTodos(){
        return repository.findAll();
    }
}
