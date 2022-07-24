package io.github.jordannegreiros.vendas.rest.controller;

import io.github.jordannegreiros.vendas.domain.ClienteNotFoundException;
import io.github.jordannegreiros.vendas.domain.entity.Cliente;
import io.github.jordannegreiros.vendas.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Cliente get(@PathVariable Integer id) {
        return clienteRepository.findById(id)
            .orElseThrow(ClienteNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        var cliente = clienteRepository.findById(id)
            .orElseThrow(ClienteNotFoundException::new);
        clienteRepository.delete(cliente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Cliente update(@PathVariable Integer id, @RequestBody  Cliente clienteRequest) {
        return clienteRepository.findById(id)
            .map(clienteDB -> {
                clienteDB.setName(clienteRequest.getName());
                return clienteRepository.save(clienteDB);
            }).orElseThrow(ClienteNotFoundException::new);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Cliente> getList(Cliente filtro) {

        var matcher = ExampleMatcher
            .matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        var example =  Example.of(filtro, matcher);

        return clienteRepository.findAll(example);
    }
}
