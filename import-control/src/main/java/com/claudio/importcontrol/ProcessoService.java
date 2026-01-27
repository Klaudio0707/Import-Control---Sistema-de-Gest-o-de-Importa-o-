package com.claudio.importcontrol;

import com.claudio.importcontrol.dto.ProcessoDTO; // Importe o DTO que criamos
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository repository; // O Service manda no Repository

    // --- 1. LISTAR TODOS ---
    public List<ProcessoImportacao> listar() {
        // O repository vai no banco e faz o "SELECT * FROM..."
        return repository.findAll();
    }

    // --- 2. CRIAR NOVO (Aqui usamos o DTO) ---
    public ProcessoImportacao criar(ProcessoDTO dados) {
        // Passo A: Criar uma Entidade vazia
        ProcessoImportacao novoProcesso = new ProcessoImportacao();

        // Passo B: Passar os dados do DTO para a Entidade
        // (Lembre-se: records não usam 'getNome', usam apenas 'nome()')
        novoProcesso.setNumeroProcesso(dados.numeroProcesso());
        novoProcesso.setIdentificadorInvoice(dados.identificadorInvoice());
        novoProcesso.setFornecedor(dados.fornecedor());
        novoProcesso.setProduto(dados.produto());
        novoProcesso.setQuantidade(dados.quantidade());
        novoProcesso.setPrecoPorQuilo(dados.precoPorQuilo());
        novoProcesso.setDataEmbarque(dados.dataEmbarque());

        // Passo C: Salvar no banco
        return repository.save(novoProcesso);
    }

    // --- 3. BUSCAR POR ID ---
    public Optional<ProcessoImportacao> buscarPorId(String id) {
        return repository.findById(id);
    }

    // --- 4. EXCLUIR ---
    public void excluir(String id) {
        repository.deleteById(id);
    }
}