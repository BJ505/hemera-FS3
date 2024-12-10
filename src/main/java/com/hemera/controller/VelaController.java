package com.hemera.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.hemera.exception.ResourceNotFoundException;
import com.hemera.model.Vela;
import com.hemera.service.VelaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/velas")
@Validated
public class VelaController {

    @Autowired
    private VelaService velaService;

    // Listar todos los velas
    @GetMapping
    public ResponseEntity<List<Vela>> listarVelas() {
        List<Vela> velas = velaService.listarVelas();
        return ResponseEntity.ok(velas);
    }

    // Crear un nuevo vela con validación
    @PostMapping
    public ResponseEntity<Vela> crearVela(@Valid @RequestBody Vela vela) {
        Vela nuevoVela = velaService.guardarVela(vela);
        return new ResponseEntity<>(nuevoVela, HttpStatus.CREATED); // Devolver 201 Created
    }

    // Obtener un vela por su ID con manejo de excepción
    @GetMapping("/{id}")
    public ResponseEntity<Vela> obtenerVela(@PathVariable Long id) {
        Vela vela = velaService.obtenerVela(id)
                .orElseThrow(() -> new ResourceNotFoundException("El vela con ID " + id + " no fue encontrado."));
        return ResponseEntity.ok(vela);
    }

    // Eliminar un vela por su ID con manejo de excepción
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVela(@PathVariable Long id) {
        // Verifica si el vela existe, si no, lanza la excepción
        velaService.obtenerVela(id)
                .orElseThrow(() -> new ResourceNotFoundException("El vela con ID " + id + " no fue encontrado."));

        // Elimina el vela si existe
        velaService.eliminarVela(id);

        return ResponseEntity.noContent().build(); // Devolver 204 No Content
    }

    // Actualizar un vela existente con manejo de excepción
    @PutMapping("/{id}")
    public ResponseEntity<Vela> actualizarVela(@PathVariable Long id, @Valid @RequestBody Vela detallesVela) {
        Vela vela = velaService.obtenerVela(id)
                .orElseThrow(() -> new ResourceNotFoundException("El vela con ID " + id + " no fue encontrado."));

        vela.setNombre(detallesVela.getNombre());
        vela.setPrecio(detallesVela.getPrecio());
        vela.setImagen(detallesVela.getImagen());
        Vela velaActualizado = velaService.guardarVela(vela);
        return ResponseEntity.ok(velaActualizado);
    }
}
