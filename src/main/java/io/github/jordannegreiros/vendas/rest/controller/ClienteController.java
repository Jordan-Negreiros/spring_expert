package io.github.jordannegreiros.vendas.rest.controller;

import io.github.jordannegreiros.vendas.domain.ClienteNotFoundException;
import io.github.jordannegreiros.vendas.domain.entity.Cliente;
import io.github.jordannegreiros.vendas.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
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
}
