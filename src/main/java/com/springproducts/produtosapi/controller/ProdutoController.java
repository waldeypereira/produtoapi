package com.springproducts.produtosapi.controller;

import com.springproducts.produtosapi.model.Produto;
import com.springproducts.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Salvar produto
    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        System.out.println("Produto recebido: " + produto);
        var id = UUID.randomUUID().toString();
        produto.setId(id);
        produtoRepository.save(produto);
        return produto;
    }

    // Retornar produto ler
    @GetMapping("{id}")
    public Produto obterPorId(@PathVariable("id") String id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            System.out.println("Produto encontrado: " + produtoOptional.get());
            return produtoOptional.get();
        } else {
            System.out.println("Produto não encontrado para o ID: " + id);
            return null;
        }
    }

    //Deletar produto
    @DeleteMapping("{id}")
    public void deletar(@PathVariable("id") String id) {
        produtoRepository.deleteById(id);
    }

    // Atualizar
    @PutMapping("{id}")
    public void atualizar(@PathVariable("id") String id, @RequestBody Produto produto) {
        produto.setId(id);
        produtoRepository.save(produto);
    }


}
