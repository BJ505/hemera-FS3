package com.hemera.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistro;

    @NotNull(message = "El idcarrito no puede ser nulo")
    private int idCarrito;

    @NotNull(message = "Falta id del producto a agregar")
    private int idProducto;

    @Min(value = 1, message = "La cantidad mínima de producto es 1")
    @Max(value = 20, message = "La cantidad máxima de producto es 20")
    private int cantProducto;

    @NotNull(message = "Falta id del usuario")
    private int idUsuario;

    // Getters y Setters
    public Long getIdRegistro() {
        return idRegistro;
    }

    public void setId(Long idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantProducto() {
        return cantProducto;
    }

    public void setCantProducto(int cantProducto) {
        this.cantProducto = cantProducto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
