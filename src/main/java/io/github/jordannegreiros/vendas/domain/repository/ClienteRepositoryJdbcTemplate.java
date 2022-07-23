package io.github.jordannegreiros.vendas.domain.repository;

import io.github.jordannegreiros.vendas.domain.ClienteMapper;
import io.github.jordannegreiros.vendas.domain.entity.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClienteRepositoryJdbcTemplate {

    private static final String INSERT = "insert into cliente (nome) values (?)";
    private static final String UPDATE = "update CLIENTE set nome = ? where id = ?";
    private static final String DELETE = "delete from CLIENTE where id = ?";
    private static final String SELECT_ALL = "select * from CLIENTE";
    private static final String SELECT_BY_NAME = "select * from CLIENTE where nome like ?";

    private final JdbcTemplate jdbcTemplate;
    private final ClienteMapper clienteMapper;

    public void save(Cliente cliente) {
        jdbcTemplate.update(INSERT, cliente.getName());
    }

    public void update(Cliente cliente) {
        jdbcTemplate.update(UPDATE, cliente.getName(), cliente.getId());
    }

    public void delete(Cliente cliente) {
        jdbcTemplate.update(DELETE, cliente.getId());
    }

    public void deleteById(Integer id) {
        jdbcTemplate.update(DELETE, id);
    }

    public List<Cliente> findByName(String name) {
        return jdbcTemplate.query(SELECT_BY_NAME, new Object[]{"%" + name + "%"}, clienteRowMapper());
    }

    public List<Cliente> list() {
        return jdbcTemplate.query(SELECT_ALL, clienteRowMapper());
    }

    private RowMapper<Cliente> clienteRowMapper() {
        return (rs, rowNum) -> {
            var id = rs.getInt("id");
            var name = rs.getString("nome");
            return clienteMapper.rowToEntity(id, name);
        };
    }
}
