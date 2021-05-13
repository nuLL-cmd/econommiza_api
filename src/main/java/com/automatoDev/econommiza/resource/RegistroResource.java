package com.automatoDev.econommiza.resource;

import javax.validation.Valid;

import com.automatoDev.econommiza.entity.Registro;
import com.automatoDev.econommiza.service.RegistroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
//@ApiOperation(value = "Destinado a manusera os registros das perspectivas e usuários.")
@RequestMapping("registros")
public class RegistroResource {
    
    @Autowired
    private RegistroService registroService;
    
    @ApiOperation(value = "Recurso responsave um registro com base no seu id.")
    @GetMapping(value = "{id}", produces = "application/json")
    private ResponseEntity<?> fetchPorId(@PathVariable Long id){
        return ResponseEntity.ok(registroService.fetchPorId(id));
    }

    @ApiOperation(value = "Grava um novo registro no banco.")
    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    private Registro postRegistro(@Valid @RequestBody Registro registro){
        return registroService.postRegistro(registro);
    }

    @ApiOperation(value = "Recurso que atualiza um registro existente.")
    @PutMapping(produces = "application/json", consumes = "application/json")
    private ResponseEntity<?> putRegistro(@Valid @RequestBody Registro registro){
        return ResponseEntity.ok(registroService.putRegistro(registro));
    }


}
