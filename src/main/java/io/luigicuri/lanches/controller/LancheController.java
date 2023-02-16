package io.luigicuri.lanches.controller;

import io.luigicuri.lanches.exceptions.ResourceNotFoundException;
import io.luigicuri.lanches.model.Lanche;
import io.luigicuri.lanches.repository.LancheRepository;
import io.luigicuri.lanches.service.FazerPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("api/lanches")
public class LancheController {
    @Autowired
    LancheRepository lancheRepository;
    @Autowired
    FazerPedidoService fazerPedidoService;

    @GetMapping
    public List<Lanche> listarLanches(){
        return lancheRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity procurarLanche(@PathVariable Long id) throws ResourceNotFoundException{
        Lanche lanche = lancheRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Lanche não encontrado"));
        return ResponseEntity.ok().body(lanche);
    }
    @PostMapping
    public Lanche fazerPedido(@Validated @RequestBody Lanche lanche) {
        return fazerPedidoService.fazerPedido(lanche);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Lanche> atualizarLanche(@PathVariable Long id, @Validated @RequestBody Lanche lancheDetails)
            throws ResourceNotFoundException{
        Lanche lanche = lancheRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Lanche não encontrado"));
        lanche.setCliente(lancheDetails.getCliente());
        lanche.setNomeLanche(lanche.getNomeLanche());
        lanche.setAdicional(lanche.getAdicional());
        lanche.setValorTotal(lanche.getValorTotal());

        final Lanche updatedLanche = lancheRepository.save(lanche);
        return ResponseEntity.ok(updatedLanche);
    }
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deletarLanche(@PathVariable Long id) throws ResourceNotFoundException{
        Lanche lanche = lancheRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Lanche não encontrado"));
        lancheRepository.delete(lanche);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Pedido Deletado", Boolean.TRUE);
        return response;
    }
    @DeleteMapping
    public Map<String, Boolean> deletarTodosLanches(){
        lancheRepository.deleteAll();
        Map<String, Boolean> response = new HashMap<>();
        response.put("Todos os pedidos foram deletados", Boolean.TRUE);
        return response;
    }
}
