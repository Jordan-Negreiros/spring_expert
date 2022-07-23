package io.github.jordannegreiros.vendas.domain.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CLIENTE")
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome", length = 100)
    private String name;

}
