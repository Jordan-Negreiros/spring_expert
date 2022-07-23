package io.github.jordannegreiros.vendas.domain.repository;

import io.github.jordannegreiros.vendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
