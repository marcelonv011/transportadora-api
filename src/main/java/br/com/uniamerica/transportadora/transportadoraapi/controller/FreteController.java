package br.com.uniamerica.transportadora.transportadoraapi.controller;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Estado;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Frete;
import br.com.uniamerica.transportadora.transportadoraapi.repository.FreteRepository;
import br.com.uniamerica.transportadora.transportadoraapi.services.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/fretes")
public class FreteController {

    @Autowired
    public FreteRepository freteRepository;
    @Autowired
    public FreteService freteService;

    @GetMapping
    public ResponseEntity<List<Frete>> findAll() {
        return ResponseEntity.ok().body(this.freteRepository.findAll());
    }

    @GetMapping("/ativo")
    public ResponseEntity<List<Frete>> findAll(
            @RequestParam("/ativo") final boolean ativo
    ){
        return ResponseEntity.ok().body(this.freteRepository.findByAtivo(true));
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<Frete>> findAll(
            @RequestParam("id") final Long id
    ) {
        return ResponseEntity.ok().body(this.freteRepository.findById(id));
    }

    @PostMapping
    public  ResponseEntity<?> cadastrar(@RequestBody final Frete frete){
        this.freteRepository.save(frete);
        return ResponseEntity.ok().body("Registro adicionado con exito");
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> verificaUpdateFrete(
            @PathVariable final Long id,
            @RequestBody final Frete frete
    ){
        try {
            this.freteService.verificaUpdateFrete(id, frete);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Frete actualizado con exito....");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @PathVariable final Long id,
            @RequestBody final Frete frete
    ){
        if (id.equals(frete.getId()) && !this.freteRepository.findById(id).isEmpty()){
            this.freteRepository.delete(frete);
        }
        else {
            return ResponseEntity.badRequest().body("id no encontrado");
        }
        return ResponseEntity.ok().body("registro eliminado con exito....");
    }

    @PutMapping("/status/faturado/{id}")
    public ResponseEntity<?> atualizarStatusFaturado(@PathVariable("id") final Long id){
        try {
            this.freteService.atualizarStatusFaturado(id);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Frete faturado com sucesso");
    }

    @PutMapping("/status/em_transporte/{id}")
    public ResponseEntity<?> atualizarStatusEmTransporte(@PathVariable("id") final Long id){
        try {
            this.freteService.atualizarStatusEmTransporte(id);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Frete faturado com sucesso");
    }

    @PutMapping("/status/interrompido/{id}")
    public ResponseEntity<?> atualizarStatusInterrompido(@PathVariable("id") final Long id){
        try {
            this.freteService.atualizarStatusInterrompido(id);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Frete faturado com sucesso");
    }

    @PutMapping("/status/cancelado/{id}")
    public ResponseEntity<?> atualizarStatusCancelado(@PathVariable("id") final Long id){
        try {
            this.freteService.atualizarStatusCancelado(id);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Frete faturado com sucesso");
    }

    @PutMapping("/status/descarga/{id}")
    public ResponseEntity<?> atualizarStatusDescarga(@PathVariable("id") final Long id){
        try {
            this.freteService.atualizarStatusDescarga(id);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Frete faturado com sucesso");
    }



}

