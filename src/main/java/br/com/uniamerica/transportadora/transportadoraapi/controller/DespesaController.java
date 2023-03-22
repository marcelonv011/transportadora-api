package br.com.uniamerica.transportadora.transportadoraapi.controller;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Despesa;

import br.com.uniamerica.transportadora.transportadoraapi.repository.DespesaRepository;
import br.com.uniamerica.transportadora.transportadoraapi.services.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/api/despesa")
public class DespesaController {

    @Autowired
    public DespesaRepository despesaRepository;

    @Autowired
    public DespesaService despesaService;

    @GetMapping
    public ResponseEntity<List<Despesa>> findAll() {
        return ResponseEntity.ok().body(this.despesaRepository.findAll());
    }


    @GetMapping("/ativo")
    public ResponseEntity<List<Despesa>> findAll(
            @RequestParam("/ativo") final boolean ativo
    ){
        return ResponseEntity.ok().body(this.despesaRepository.findByAtivo(true));
    }


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final  Despesa despesa){
        this.despesaRepository.save(despesa);
        return ResponseEntity.ok().body("Registro adicionado con exito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody Despesa despesa
    ){
        if (id.equals(despesa.getId())){
            this.despesaRepository.save(despesa);
        }
        else {
            return ResponseEntity.badRequest().body("id no encontrado");
        }
        return ResponseEntity.ok().body("registro actualizado con exito....");
    }

    @PutMapping("/despesaadmin/{id}")
    public ResponseEntity<?> despesaativo(@PathVariable("id") final Long id){
        try {
            this.despesaService.despesaativo(id);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Frete faturado com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @PathVariable Long id,
            @RequestBody Despesa despesa
    ){
        if (id.equals(despesa.getId()) && !this.despesaRepository.findById(id).isEmpty()){
            this.despesaRepository.delete(despesa);
        }
        else {
            return ResponseEntity.badRequest().body("id no encontrado");
        }
        return ResponseEntity.ok().body("Registro borrado con exito...");
    }

}
