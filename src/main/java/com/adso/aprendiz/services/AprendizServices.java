package com.adso.aprendiz.services;


import com.adso.aprendiz.entity.Aprendiz;
import com.adso.aprendiz.repository.AprendizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AprendizServices {
    @Autowired
    private AprendizRepository aprendizRepository;

    public void crearAprendiz(Aprendiz aprendiz) {
        aprendizRepository.save(aprendiz);
    }

    public List<Aprendiz> obtenerAprendices() {
        return aprendizRepository.findAll();
    }

    public void eliminarAprendiz(Long id) {
        aprendizRepository.deleteById(id);
    }

    public Aprendiz buscarAprendiz(Long id) {
        return aprendizRepository.findById(id).orElse(null);
    }

    public Aprendiz actualizarAprendiz(Long id, Aprendiz aprendiz) {
        aprendiz.setId(id);
        return aprendizRepository.save(aprendiz);
    }
}
