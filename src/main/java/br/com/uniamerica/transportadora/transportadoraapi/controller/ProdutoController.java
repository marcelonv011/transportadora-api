package br.com.uniamerica.transportadora.transportadoraapi.controller;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Produto;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Usuario;
import br.com.uniamerica.transportadora.transportadoraapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    public ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok().body(this.produtoRepository.findByAtivo(true));
    }


    @GetMapping("/id")
    public ResponseEntity<Optional<Produto>> findAll(
            @RequestParam("id") final Long id
    ) {
        return ResponseEntity.ok().body(this.produtoRepository.findById(id));
    }

    @PostMapping
    public  ResponseEntity<?> cadastrar(@RequestBody final Produto produto){
        this.produtoRepository.save(produto);
        return ResponseEntity.ok().body("Registro adicionado con exito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable final Long id,
            @RequestBody final  Produto produto
    ){
        if (id.equals(produto.getId())) {
            this.produtoRepository.save(produto);
        }
        else {
            return ResponseEntity.badRequest().body("id no encontrado");
        }
        return ResponseEntity.ok().body("Registro actualizado con exito...");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @PathVariable final Long id,
            @RequestBody final  Produto produto
    ){
        if (id.equals(produto.getId()) && !this.produtoRepository.findById(id).isEmpty()) {
            this.produtoRepository.delete(produto);
        }
        else {
            return ResponseEntity.badRequest().body("id no encontrado");
        }
        return ResponseEntity.ok().body("Registro borrado con exito...");
    }

}
