package io.github.jordannegreiros.vendas.rest.controller;

import io.github.jordannegreiros.vendas.domain.ClienteNotFoundException;
import io.github.jordannegreiros.vendas.domain.entity.Cliente;
import io.github.jordannegreiros.vendas.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> get(@PathVariable Integer id) {
        var cliente = clienteRepository.findById(id)
            .orElseThrow(ClienteNotFoundException::new);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        var clienteSaved = clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSaved);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        var cliente = clienteRepository.findById(id)
            .orElseThrow(ClienteNotFoundException::new);
        clienteRepository.delete(cliente);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> update(@PathVariable Integer id, @RequestBody  Cliente clienteRequest) {
        var clienteUpdated = clienteRepository.findById(id)
            .map(clienteDB -> {
                clienteDB.setName(clienteRequest.getName());
                return clienteRepository.save(clienteDB);
            }).orElseThrow(ClienteNotFoundException::new);

        return ResponseEntity.ok(clienteUpdated);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Cliente>> getList(Cliente filtro) {

        var matcher = ExampleMatcher
            .matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        var example =  Example.of(filtro, matcher);

        var clientes = clienteRepository.findAll(example);
        return ResponseEntity.ok(clientes);
    }
}
