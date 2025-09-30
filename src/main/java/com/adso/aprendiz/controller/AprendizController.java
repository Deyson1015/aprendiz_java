package com.adso.aprendiz.controller;

import com.adso.aprendiz.entity.Aprendiz;
import com.adso.aprendiz.services.AprendizServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/v1/aprendiz")
public class AprendizController {

    @Autowired
    private AprendizServices aprendizServices;

    @PostMapping
    public void crearAprendiz(@RequestBody Aprendiz aprendiz){
        aprendizServices.crearAprendiz(aprendiz);
    }

    @GetMapping
    public List<Aprendiz> obtenerAprendices(){
        return aprendizServices.obtenerAprendices();
    }

    @GetMapping("/{id}")
    public Aprendiz buscarAprendiz(@PathVariable Long id){
        return aprendizServices.buscarAprendiz(id);
    }

    @PutMapping("/{id}")
    public Aprendiz actualizarAprendiz(@PathVariable Long id, @RequestBody Aprendiz aprendiz
    ){
        return aprendizServices.actualizarAprendiz(id, aprendiz);
    }

    @DeleteMapping("/{id}")
    public void eliminarAprendiz(@PathVariable Long id){
        aprendizServices.eliminarAprendiz(id);
    }
}
