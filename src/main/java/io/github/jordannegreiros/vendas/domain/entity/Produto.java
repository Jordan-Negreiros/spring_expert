package io.github.jordannegreiros.vendas.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

}
