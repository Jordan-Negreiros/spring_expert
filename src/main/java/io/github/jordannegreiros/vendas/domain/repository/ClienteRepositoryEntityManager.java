package io.github.jordannegreiros.vendas.domain.repository;

import io.github.jordannegreiros.vendas.domain.entity.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClienteRepositoryEntityManager {

    private final EntityManager entityManager;

    @Transactional
    public void save(final Cliente cliente) {
        entityManager.persist(cliente);
    }

    @Transactional
    public void update(final Cliente cliente) {
        entityManager.merge(cliente);
    }

    @Transactional
    public void delete(final Cliente cliente) {
        if (!entityManager.contains(cliente)) {
            entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional
    public void deleteById(final Integer id) {
        var entity = entityManager.find(Cliente.class, id);
        entityManager.remove(entity);
    }

    @Transactional(readOnly = true)
    public List<Cliente> findAllByName(final String name) {
        var jpql = " select c from Cliente c where c.name = :name ";
        var query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return entityManager.createQuery("from Cliente", Cliente.class)
            .getResultList();
    }
}
