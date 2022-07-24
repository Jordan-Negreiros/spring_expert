package io.github.jordannegreiros.vendas.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Cliente not found")
public class ClienteNotFoundException extends RuntimeException{
}
