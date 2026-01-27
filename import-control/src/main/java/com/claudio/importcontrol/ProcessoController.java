package com.claudio.importcontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/processos")
public class ProcessoController {

    private static List<ProcessoImportacao> bancoDeDados = new ArrayList<>();

    @GetMapping
    public List<ProcessoImportacao> lista() {
        return bancoDeDados;
    }

    @DeleteMapping("/{id}") // metodo delete 
    public ResponseEntity<Object> remover(@PathVariable String id) {
        ProcessoImportacao processo = bancoDeDados.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (processo == null) {
            return ResponseEntity.notFound().build();
        }
        bancoDeDados.remove(processo);

        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", "Processo Removido da Base de Dados. ");
        return ResponseEntity.ok(resposta);
    }

    @PostMapping // metodo post - salvar
    public ResponseEntity<ProcessoImportacao> salvar(@RequestBody ProcessoImportacao processo) {
        bancoDeDados.add(processo);
        return ResponseEntity.status(HttpStatus.CREATED).body(processo);
    }

    @PutMapping("/{id}") // metodo put para atualizar
    public ResponseEntity<Object> atualizar(@PathVariable String id, @RequestBody ProcessoImportacao novosDados) {
        ProcessoImportacao processoOriginal = bancoDeDados.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (processoOriginal == null) {
            Map<String, String> erro = new HashMap<>();
            erro.put("mensagem", "Erro: Processo não encontrado para atualizar.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        }
        processoOriginal.setProduto(novosDados.getProduto());
        processoOriginal.setQuantidade(novosDados.getQuantidade());
        processoOriginal.setPrecoPorQuilo(novosDados.getPrecoPorQuilo());

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "Processo Atualizaddo com Sucesso!");
        resposta.put("novosDados", processoOriginal);

        return ResponseEntity.ok(resposta);

    }

    @GetMapping("/{id}") // metodo get
    public ResponseEntity<ProcessoImportacao> buscarProcesso(@PathVariable String id) {
        ProcessoImportacao processoEncontrado = bancoDeDados.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (processoEncontrado != null) {
            return ResponseEntity.ok(processoEncontrado);
        }
        return ResponseEntity.notFound().build();
    }
}
