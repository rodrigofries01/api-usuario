package com.fries.api_usuario.controllers;

import com.fries.api_usuario.dtos.UsuarioDTO;
import com.fries.api_usuario.entities.Usuario;
import com.fries.api_usuario.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody UsuarioDTO usuarioDTO){
        Usuario novoUsuario = new Usuario();

        novoUsuario.setName(usuarioDTO.name());
        novoUsuario.setEmail(usuarioDTO.email());
        novoUsuario.setPassword(usuarioDTO.password());

        this.usuarioRepository.save(novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario cadastrado com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll(){
        return ResponseEntity.ok(this.usuarioRepository.findAll());
    }

    @GetMapping(path = "/{id}")
    public Object getOne(@PathVariable(name = "id") Long id) {
        Usuario usuarioEncontrado = this.usuarioRepository.findById(id).orElse(null);
        if (usuarioEncontrado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario não encontrado");
        } else {
            UsuarioDTO usuarioDTO = new UsuarioDTO(
                    usuarioEncontrado.getId(),
                    usuarioEncontrado.getName(),
                    usuarioEncontrado.getEmail(),
                    usuarioEncontrado.getPassword()
            );
            return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
        }
    }}