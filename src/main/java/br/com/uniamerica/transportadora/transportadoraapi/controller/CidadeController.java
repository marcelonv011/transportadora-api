package br.com.uniamerica.transportadora.transportadoraapi.controller;


import br.com.uniamerica.transportadora.transportadoraapi.entity.Cidade;
import br.com.uniamerica.transportadora.transportadoraapi.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/cidades")
public class CidadeController {

    @Autowired
    public CidadeRepository cidadeRepository;

    @GetMapping
    public ResponseEntity<List<Cidade>> findAll() {
        return ResponseEntity.ok().body(this.cidadeRepository.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<Cidade>> findAll(
            @RequestParam("id") final Long id
    ){
        return ResponseEntity.ok().body(this.cidadeRepository.findById(id));
    }

    @GetMapping("/ativo")
    public ResponseEntity<List<Cidade>> findAll(
            @RequestParam("ativo") final boolean ativo
    ){
        return ResponseEntity.ok().body(this.cidadeRepository.findByAtivo(true));
    }

    @GetMapping("/cidade/{estadoId}")
    public ResponseEntity<?> findByEstado(@PathVariable("estadoId") Long estadoId){
        return ResponseEntity.ok().body(this.cidadeRepository.findByEstado(estadoId));
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Cidade cidade){
        this.cidadeRepository.save(cidade);
        return ResponseEntity.ok().body("Registro adicionado con exito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable final Long id,
            @RequestBody final Cidade cidade
    ) {
        if (id.equals(cidade.getId())) {
            this.cidadeRepository.save(cidade);
        }
        else {
        return ResponseEntity.badRequest().body("id no encontrado...");
        }
        return ResponseEntity.ok().body("registro actualizado con exito..");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @PathVariable final Long id,
            @RequestBody final Cidade cidade
    ){
        if (id.equals(cidade.getId()) && !this.cidadeRepository.findById(id).isEmpty()){
            this.cidadeRepository.delete(cidade);
        }
        else {
            return ResponseEntity.badRequest().body("id no encontrado");
        }
        return ResponseEntity.ok().body("Registro borrado con exito...");
    }

}
