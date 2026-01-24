package com.claudio.importcontrol;

import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public ProcessoImportacao salvar(@RequestBody ProcessoImportacao processo){
        bancoDeDados.add(processo);
        return processo;
    }

    @GetMapping("/{id}")
    public ProcessoImportacao buscarProcesso(@PathVariable String id){
            return bancoDeDados.stream()
                   .filter(p -> p.getNumeroProcesso().equals(id))
                   .findFirst()
                   .orElse(null);
    }
}