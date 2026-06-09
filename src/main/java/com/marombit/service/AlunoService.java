package com.marombit.service;

import com.marombit.exception.AlunoNotFoundException;
import com.marombit.exception.CpfJaCadastradoException;
import com.marombit.model.Aluno;
import com.marombit.repository.AlunoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;
    private Aluno usuárioNãoEncontrado;

    public List<Aluno> listarTodos(){
        return repository.findAll();
    }

    public Aluno criarAluno(Aluno aluno){
        if (repository.existsByCpf(aluno.getCpf())){
            throw new CpfJaCadastradoException(aluno.getCpf());
        }

        return repository.save(aluno);

    }

    public Aluno atualizar( Long id, Aluno aluno){
        Aluno alunoExist = usuárioNãoEncontrado;


            alunoExist.setNome(aluno.getNome());
            alunoExist.setCpf(aluno.getCpf());
            alunoExist.setDtNascimento(aluno.getDtNascimento());
            alunoExist.setPlano(aluno.getPlano());

            return repository.save(alunoExist);




    }

    public void Deletar(Long id) {

        repository.findById(id)
                .orElseThrow(()-> new AlunoNotFoundException(id));

        repository.deleteById(id);

    }

    public Aluno buscarAlunoPorID (Long id){
        return repository.findById(id).orElseThrow(()-> new AlunoNotFoundException(id));

    }
}
