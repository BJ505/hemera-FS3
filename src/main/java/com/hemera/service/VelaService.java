package com.hemera.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemera.model.Vela;
import com.hemera.repository.VelaRepository;

@Service
public class VelaService {

    @Autowired
    private VelaRepository velaRepository;

    public List<Vela> listarVelas() {
        return velaRepository.findAll();
    }

    public Optional<Vela> obtenerVela(Long id) {
        return velaRepository.findById(id);
    }

    public Vela guardarVela(Vela vela) {
        return velaRepository.save(vela);
    }

    public void eliminarVela(Long id) {
        velaRepository.deleteById(id);
    }
}

