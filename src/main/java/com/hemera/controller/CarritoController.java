package com.hemera.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.hemera.exception.ResourceNotFoundException;
import com.hemera.model.Carrito;
import com.hemera.service.CarritoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/carrito")
@Validated
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    // Listar todos los carritos
    @GetMapping
    public ResponseEntity<List<Carrito>> listarCarritos() {
        List<Carrito> carritos = carritoService.listarCarritos();
        return ResponseEntity.ok(carritos);
    }

    // Crear un nuevo carrito con validación
    @PostMapping
    public ResponseEntity<Carrito> crearCarrito(@Valid @RequestBody Carrito carrito) {
        Carrito nuevoCarrito = carritoService.guardarCarrito(carrito);
        return new ResponseEntity<>(nuevoCarrito, HttpStatus.CREATED); // Devolver 201 Created
    }

    // Obtener un carrito por su ID con manejo de excepción
    @GetMapping("/{idCarrito}")
    public ResponseEntity<Carrito> obtenerCarrito(@PathVariable Long idCarrito) {
        Carrito carrito = carritoService.obtenerCarrito(idCarrito)
                .orElseThrow(() -> new ResourceNotFoundException("El carrito con ID " + idCarrito + " no fue encontrado."));
        return ResponseEntity.ok(carrito);
    }

    // Eliminar un carrito por su ID con manejo de excepción
    @DeleteMapping("/{idCarrito}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable Long idCarrito) {
        // Verifica si el carrito existe, si no, lanza la excepción
        carritoService.obtenerCarrito(idCarrito)
                .orElseThrow(() -> new ResourceNotFoundException("El carrito con ID " + idCarrito + " no fue encontrado."));

        // Elimina el carrito si existe
        carritoService.eliminarCarrito(idCarrito);

        return ResponseEntity.noContent().build(); // Devolver 204 No Content
    }

    // Eliminar un Producto de un carrito por su ID de producto y carrito
    // @DeleteMapping("/{idCarrito}")
    // public ResponseEntity<Void> eliminarProductoDeCarrito(@PathVariable int idCarrito, @PathVariable int idProducto) {
    //     // Verifica si el carrito existe, si no, lanza la excepción
    //     carritoService.obtenerCarrito(idCarrito)
    //             .orElseThrow(() -> new ResourceNotFoundException("El carrito con ID " + idCarrito + " no fue encontrado."));

    //     // Elimina el carrito si existe
    //     carritoService.eliminarCarrito(idCarrito);

    //     return ResponseEntity.noContent().build(); // Devolver 204 No Content
    // }

    // Actualizar un producto de un carrito
    @PutMapping("/{idCarrito}/{idProducto}")
    public ResponseEntity<Carrito> actualizarCarrito(@PathVariable Long idCarrito,@PathVariable Long idProducto, @Valid @RequestBody Carrito detallesCarrito) {
        Carrito carrito = carritoService.obtenerCarrito(idCarrito)
                .orElseThrow(() -> new ResourceNotFoundException("El carrito con ID " + idCarrito + " no fue encontrado."));

        carrito.setCantProducto(detallesCarrito.getCantProducto());
        Carrito carritoActualizado = carritoService.guardarCarrito(carrito);
        return ResponseEntity.ok(carritoActualizado);
    }
}
