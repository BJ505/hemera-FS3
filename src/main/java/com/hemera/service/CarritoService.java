package com.hemera.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemera.model.Carrito;
import com.hemera.repository.CarritoRepository;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> listarCarritos() {
        return carritoRepository.findAll();
    }

    public Optional<Carrito> obtenerCarrito(Long idCarrito) {
        return carritoRepository.findById(idCarrito);
    }

    public Carrito guardarCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public void eliminarCarrito(Long id) {
        carritoRepository.deleteById(id);
    }

    // public void eliminarProductoDeCarrito(int idCarrito, int idProducto) {
    //     carritoRepository.deleteById(idCarrito,idProducto);
    // }
}

