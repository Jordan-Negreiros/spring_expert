package io.github.jordannegreiros.vendas.domain.repository;

import io.github.jordannegreiros.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(value = " select c from Cliente c where c.name like :name ")
    List<Cliente> findByNameWithJpql(@Param("name") String name);

    @Query(value = " select * from CLIENTE where nome like '%:name%' ", nativeQuery = true)
    List<Cliente> findByNameWithNativeSql(@Param("name") String name);

    @Modifying
    @Query(value = " delete from Cliente c where c.name = :name")
    void deleteByName(@Param("name") String name);

    List<Cliente> findByNameOrId(String name, Integer id);

    boolean existsByName(String name);
}
