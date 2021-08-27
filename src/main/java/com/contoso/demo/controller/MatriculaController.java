package com.contoso.demo.controller;

import com.contoso.demo.model.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.contoso.demo.repository.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="api/matricula", produces ="application/json")
public class MatriculaController {
    private final MatriculaRepository matriculaData;
    private final DetalleMatriculaRepository detalleMatriculaData;

    public MatriculaController(MatriculaRepository matriculaData,
        DetalleMatriculaRepository detalleMatriculaData){
        this.matriculaData = matriculaData;
        this.detalleMatriculaData = detalleMatriculaData;
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> create(@RequestBody Matricula p){
        matriculaData.save(p);
        matriculaData.flush(); //-> id
        Matricula generada = p;
        List<DetalleMatricula> listItems = p.getDetalleMatriculas();
        listItems.stream().forEach(o -> o.setMatricula(generada));
        detalleMatriculaData.saveAllAndFlush(listItems);
        return new ResponseEntity<Integer>(p.getId(),HttpStatus.CREATED);
    }

    @GetMapping(value = "/{numeroMatricula}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Matricula> findByNumber(@PathVariable String numeroMatricula){
        Optional<Matricula> optMatricula =matriculaData.findByNumero(numeroMatricula);
        if(optMatricula.isPresent()){
            Matricula matricula = optMatricula.get();
            List<DetalleMatricula> detalleMatriculas = detalleMatriculaData.findItemsByMatricula(matricula);
            matricula.setDetalleMatriculas(detalleMatriculas);
            return new ResponseEntity<Matricula>(matricula,HttpStatus.OK);
        }else{
            return new ResponseEntity<Matricula>(HttpStatus.NOT_FOUND);
        }

        
    }

    
}

