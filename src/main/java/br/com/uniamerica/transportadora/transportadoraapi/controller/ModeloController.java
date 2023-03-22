package br.com.uniamerica.transportadora.transportadoraapi.controller;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Modelo;
import br.com.uniamerica.transportadora.transportadoraapi.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/modelos")
public class ModeloController {

    @Autowired
    public ModeloRepository modeloRepository;

    @GetMapping
    public ResponseEntity<List<Modelo>> findAll() {
        return ResponseEntity.ok().body(this.modeloRepository.findAll());
    }

    @GetMapping("/ativo")
    public ResponseEntity<List<Modelo>> findAll(
        @RequestParam("/ativo") final boolean ativo
    ){
        return ResponseEntity.ok().body(this.modeloRepository.findByAtivo(true));
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<Modelo>> findAll(
            @RequestParam("/id") final Long id
    ) {
        return ResponseEntity.ok().body(this.modeloRepository.findById(id));
    }

    @GetMapping("/marca/{marcaId}")
    public ResponseEntity<?> findByMarca(@PathVariable("marcaId") Long marcaId){
        return ResponseEntity.ok().body(this.modeloRepository.findByMarca(marcaId));
    }

     @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Modelo modelo){
        this.modeloRepository.save(modelo);
        return ResponseEntity.ok().body("Registro adicionado con exito");
     }

     @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody Modelo modelo
     ){
        if (id.equals(modelo.getId())){
            this.modeloRepository.save(modelo);
        }
        else {
            return ResponseEntity.badRequest().body("id no encontrado");
        }
        return ResponseEntity.ok().body("registro actualizado con exito....");
     }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @PathVariable Long id,
            @RequestBody Modelo modelo
    ){
        if (id.equals(modelo.getId()) && !this.modeloRepository.findById(id).isEmpty()){
            this.modeloRepository.delete(modelo);
        }
        else {
            return ResponseEntity.badRequest().body("id no encontrado");
        }
        return ResponseEntity.ok().body("Registro borrado con exito...");
    }

}
