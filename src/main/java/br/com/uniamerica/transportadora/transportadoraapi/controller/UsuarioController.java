package br.com.uniamerica.transportadora.transportadoraapi.controller;

import br.com.uniamerica.transportadora.transportadoraapi.entity.Grupo;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Modelo;
import br.com.uniamerica.transportadora.transportadoraapi.entity.Usuario;
import br.com.uniamerica.transportadora.transportadoraapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok().body(this.usuarioRepository.findAll());
    }

    @GetMapping("/ativo")
    public ResponseEntity<List<Usuario>> findAll(
            @RequestParam("/ativo") final boolean ativo
    ){
        return ResponseEntity.ok().body(this.usuarioRepository.findByAtivo(true));
    }

    @GetMapping("/{grupo}")
    public ResponseEntity<List<Usuario>> findAll(
            @PathVariable("grupo") final Grupo grupo
    ){
        return  ResponseEntity.ok().body(this.usuarioRepository.findByGrupo(grupo));
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<Usuario>> findAll(
            @RequestParam("id") final Long id
    ) {
        return ResponseEntity.ok().body(this.usuarioRepository.findById(id));
    }

    @PostMapping
    public  ResponseEntity<?> cadastrar(@RequestBody final  Usuario usuario){
        this.usuarioRepository.save(usuario);
        return ResponseEntity.ok().body("Registro adicionado con exito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable final Long id,
            @RequestBody final  Usuario usuario
    ){
        if (id.equals(usuario.getId())) {
            this.usuarioRepository.save(usuario);
        }
        else {
            return ResponseEntity.badRequest().body("id no encontrado");
        }
        return ResponseEntity.ok().body("Registro actualizado con exito...");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @PathVariable final Long id,
            @RequestBody final  Usuario usuario
    ){
        if (id.equals(usuario.getId()) && !this.usuarioRepository.findById(id).isEmpty()) {
            this.usuarioRepository.delete(usuario);
        }
        else {
            return ResponseEntity.badRequest().body("id no encontrado");
        }
        return ResponseEntity.ok().body("Registro borrado con exito...");
    }
}
