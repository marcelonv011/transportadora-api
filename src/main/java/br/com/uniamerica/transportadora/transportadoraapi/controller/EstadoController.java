package br.com.uniamerica.transportadora.transportadoraapi.controller;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Estado;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Modelo;
import br.com.uniamerica.transportadora.transportadoraapi.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public ResponseEntity<List<Estado>> findAll() {
        return ResponseEntity.ok().body(this.estadoRepository.findAll());
    }

    @GetMapping("/ativo")
    public ResponseEntity<List<Estado>> findAll(
            @RequestParam("/ativo") final boolean ativo
    ){
        return ResponseEntity.ok().body(this.estadoRepository.findByAtivo(true));
    }

   @GetMapping("/id")
    public ResponseEntity<Optional<Estado>> findAll(
        @RequestParam("id") final Long id
    ) {
       return ResponseEntity.ok().body(this.estadoRepository.findById(id));
    }


    @PostMapping
    public  ResponseEntity<?> cadastrar(@RequestBody final Estado estado){
        this.estadoRepository.save(estado);
        return ResponseEntity.ok().body("Registro adicionado con exito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable final Long id,
            @RequestBody final Estado estado
    ){
        if (id.equals(estado.getId())){
            this.estadoRepository.save(estado);
        }
        else {
            return ResponseEntity.badRequest().body("id no encontrado");
        }
        return ResponseEntity.ok().body("registro actualizado con exito....");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @PathVariable final Long id,
            @RequestBody final Estado estado
    ){
        if (id.equals(estado.getId()) && !this.estadoRepository.findById(id).isEmpty()){
            this.estadoRepository.delete(estado);
        }
        else {
            return ResponseEntity.badRequest().body("id no encontrado");
        }
        return ResponseEntity.ok().body("registro eliminado con exito....");
    }
}