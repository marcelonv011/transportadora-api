package br.com.uniamerica.transportadora.transportadoraapi.controller;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Estado;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Marca;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Modelo;
import br.com.uniamerica.transportadora.transportadoraapi.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/marcas")
public class MarcaController {

    @Autowired
    public MarcaRepository marcaRepository;

    @GetMapping
    public ResponseEntity<List<Marca>> findAll() {
        return ResponseEntity.ok().body(this.marcaRepository.findAll());
    }

    @GetMapping("/ativo")
    public ResponseEntity<List<Marca>> findAll(
            @RequestParam("/ativo") final boolean ativo
    ){
        return ResponseEntity.ok().body(this.marcaRepository.findByAtivo(true));
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<Marca>> findAll(
            @RequestParam("id") final Long id
    ){
        return ResponseEntity.ok().body(this.marcaRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Marca marca) {
        this.marcaRepository.save(marca);
                return ResponseEntity.ok().body("Registro adicionado con exito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable final Long id,
            @RequestBody final Marca marca
    ) {
        if (id.equals(marca.getId())) {
            this.marcaRepository.save(marca);
        }
        else {
            return ResponseEntity.badRequest().body("id no encontrado");
        }
        return ResponseEntity.ok().body("registro actualizado con exito....");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @PathVariable final Long id,
            @RequestBody final Marca marca
    ){
        if (id.equals(marca.getId()) && !this.marcaRepository.findById(id).isEmpty()){
            this.marcaRepository.delete(marca);
        }
        else {
            return ResponseEntity.badRequest().body("id no encontrado");
        }
        return ResponseEntity.ok().body("registro eliminado con exito....");
    }
}
