package io.github.jordannegreiros.vendas.rest.controller;

import io.github.jordannegreiros.vendas.domain.ClienteNotFoundException;
import io.github.jordannegreiros.vendas.domain.entity.Cliente;
import io.github.jordannegreiros.vendas.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        var cliente = clienteRepository.findById(id)
            .orElseThrow(ClienteNotFoundException::new);
        return ResponseEntity.ok(cliente);
    }
}
