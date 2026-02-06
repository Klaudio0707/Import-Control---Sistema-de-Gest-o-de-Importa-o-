package com.claudio.importcontrol.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.claudio.importcontrol.dto.EventoDTO;
import com.claudio.importcontrol.entity.EventoRastreio;
import com.claudio.importcontrol.service.EventoService;

@RestController // infroamando que é um controller
@RequestMapping("/eventos") // mapeando a rota para eventos
public class EventoController {

    private final EventoService service; //injetando o serviço de evento

    public EventoController(@Autowired EventoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<String> registrarEvento(@RequestBody EventoDTO dados) {
        EventoRastreio eventoSalvo = service.registrar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body("Evento registrado com ID: " + eventoSalvo.getId());
    }
    
}