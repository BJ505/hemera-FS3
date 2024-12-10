package com.hemera.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

@Entity
public class Carrito {

    @Id
    @NotNull(message = "Falta id del usuario")
    private Long idUsuario;

    @NotNull(message = "El idcarrito no puede ser nulo")
    private int idCarrito;

    @NotNull(message = "Falta id del producto a agregar")
    private int idProducto;

    @Min(value = 1, message = "La cantidad mínima de producto es 1")
    private int cantProducto;

    // Getters y Setters
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

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
