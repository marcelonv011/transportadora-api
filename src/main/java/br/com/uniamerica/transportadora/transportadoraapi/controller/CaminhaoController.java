package br.com.uniamerica.transportadora.transportadoraapi.controller;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Caminhao;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Modelo;
import br.com.uniamerica.transportadora.transportadoraapi.repository.CaminhaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/caminhoes")
public class CaminhaoController {

    @Autowired
    public CaminhaoRepository caminhaoRepository;

    @GetMapping
    public ResponseEntity<List<Caminhao>> findAll() {
        return ResponseEntity.ok().body(this.caminhaoRepository.findAll());
    }

    @GetMapping("/ativo")
    public ResponseEntity<List<Caminhao>> findAll(
            @RequestParam("/ativo") final boolean ativo
    ){
        return ResponseEntity.ok().body(this.caminhaoRepository.findByAtivo(true));
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<Caminhao>> findAll(
            @RequestParam("id") final Long id
    ){
        return ResponseEntity.ok().body(this.caminhaoRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Caminhao caminhao){
        this.caminhaoRepository.save(caminhao);
        return ResponseEntity.ok().body("Registro adicionado con exito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable final Long id,
            @RequestBody final Caminhao caminhao
    ) {
        if (id.equals(caminhao.getId())) {
            this.caminhaoRepository.save(caminhao);
        }
        else {
            return ResponseEntity.badRequest().body("id no encontrado...");
        }
        return ResponseEntity.ok().body("registro actualizado con exito...");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @PathVariable final Long id,
            @RequestBody final Caminhao caminhao
    ) {
        if (id.equals(caminhao.getId()) && !this.caminhaoRepository.findById(id).isEmpty()) {
            this.caminhaoRepository.delete(caminhao);
        }
        else {
            return ResponseEntity.badRequest().body("id no encontrado");
        }
        return ResponseEntity.ok().body("Registro borrado con exito...");
    }

}
