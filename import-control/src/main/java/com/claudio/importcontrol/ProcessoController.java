package com.claudio.importcontrol;

import org.apache.coyote.Response;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/processos")
public class ProcessoController {

    private static List<ProcessoImportacao> bancoDeDados = new ArrayList<>();

    @GetMapping
    public List<ProcessoImportacao> lista(){
        return bancoDeDados;
    }

    @PostMapping
    public ResponseEntity<ProcessoImportacao> salvar(@RequestBody ProcessoImportacao processo){
        bancoDeDados.add(processo);
        return ResponseEntity.status(HttpStatus.CREATED).body(processo);
    }

    @GetMapping("/{id}")
    public ResponseEntity  <ProcessoImportacao> buscarProcesso(@PathVariable String id){
             ProcessoImportacao processoEncontrado = bancoDeDados.stream()
                    .filter(p -> p.getNumeroProcesso().equals(id))
                    .findFirst()
                    .orElse(null);
                  if(processoEncontrado != null) {
                      return ResponseEntity.ok(processoEncontrado);
        }

            return ResponseEntity.notFound().build();
    }
}