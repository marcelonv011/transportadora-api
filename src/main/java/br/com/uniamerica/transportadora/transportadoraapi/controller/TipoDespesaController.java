package br.com.uniamerica.transportadora.transportadoraapi.controller;


import br.com.uniamerica.transportadora.transportadoraapi.entity.TipoDespesa;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Usuario;
import br.com.uniamerica.transportadora.transportadoraapi.repository.TipoDespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/tipodespesa")
public class TipoDespesaController {

    @Autowired
    public TipoDespesaRepository tipoDespesaRepository;

    @GetMapping
    public ResponseEntity<List<TipoDespesa>> findAll() {
        return ResponseEntity.ok().body(this.tipoDespesaRepository.findAll());
    }

    @GetMapping("/ativo")
    public ResponseEntity<List<TipoDespesa>> findAll(
            @RequestParam("/ativo") final boolean ativo
    ){
        return ResponseEntity.ok().body(this.tipoDespesaRepository.findByAtivo(true));
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<TipoDespesa>> findAll(
            @RequestParam("id") final Long id
    ) {
        return ResponseEntity.ok().body(this.tipoDespesaRepository.findById(id));
    }

    @PostMapping
    public  ResponseEntity<?> cadastrar(@RequestBody final  TipoDespesa tipoDespesa){
        this.tipoDespesaRepository.save(tipoDespesa);
        return ResponseEntity.ok().body("Registro adicionado con exito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable final Long id,
            @RequestBody final  TipoDespesa tipoDespesa
    ){
        if (id.equals(tipoDespesa.getId())) {
            this.tipoDespesaRepository.save(tipoDespesa);
        }
        else {
            return ResponseEntity.badRequest().body("id no encontrado");
        }
        return ResponseEntity.ok().body("Registro actualizado con exito...");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @PathVariable final Long id,
            @RequestBody final TipoDespesa tipoDespesa
    ){
        if (id.equals(tipoDespesa.getId()) && !this.tipoDespesaRepository.findById(id).isEmpty()) {
            this.tipoDespesaRepository.delete(tipoDespesa);
        }
        else {
            return ResponseEntity.badRequest().body("id no encontrado");
        }
        return ResponseEntity.ok().body("Registro borrado con exito...");
    }

}
