package io.github.jordannegreiros.vendas.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "cliente")
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome", length = 100)
    private String name;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private Set<Pedido> pedidos;

}
