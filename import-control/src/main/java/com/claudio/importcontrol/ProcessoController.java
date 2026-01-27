package com.claudio.importcontrol;

import com.claudio.importcontrol.dto.ProcessoDTO; // Importe o DTO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/processos")
public class ProcessoController {


    @Autowired
    private ProcessoService service;

    @GetMapping
    public ResponseEntity<List<ProcessoImportacao>> listarTodos() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<ProcessoImportacao> salvar(@RequestBody ProcessoDTO dados) {
        ProcessoImportacao processoSalvo = service.criar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(processoSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcessoImportacao> buscarPorId(@PathVariable String id) {
        Optional<ProcessoImportacao> processo = service.buscarPorId(id);
        if (processo.isPresent()) {
            return ResponseEntity.ok(processo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable String id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}