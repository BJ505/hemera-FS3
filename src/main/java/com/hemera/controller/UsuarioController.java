package com.hemera.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hemera.exception.ResourceNotFoundException;
import com.hemera.model.LoginRequest;
import com.hemera.model.Usuario;
import com.hemera.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@Validated
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listasUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    // Crear un nuevo usuario con validación
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED); // Devolver 201 Created
    }

    // Obtener un usuario por su ID con manejo de excepción
    @GetMapping("/user/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody LoginRequest loginRequest) {
        // Imprimir los parámetros recibidos
        System.out.println("Login request received: Username = " + loginRequest.getUsername() + ", Password = " + loginRequest.getPassword());

        Usuario usuario = usuarioService.login(loginRequest.getUsername(), loginRequest.getPassword())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado o credenciales incorrectas."));
        return ResponseEntity.ok(usuario);
    }

    // Eliminar un usuario por su ID con manejo de excepción
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        // Verifica si el usuario existe, si no, lanza la excepción
        usuarioService.obtenerUsuarioPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con ID " + id + " no fue encontrado."));

        // Elimina el usuario si existe
        usuarioService.eliminarUsuario(id);

        return ResponseEntity.noContent().build(); // Devolver 204 No Content
    }

    // Actualizar un usuario existente con manejo de excepción
    @PutMapping("/user/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody Usuario detallesUsuario) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con ID " + id + " no fue encontrado."));

        usuario.setUsername(detallesUsuario.getUsername());
        usuario.setPassword(detallesUsuario.getPassword());
        usuario.setRol(detallesUsuario.getRol());
        Usuario usuarioActualizado = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }
}
