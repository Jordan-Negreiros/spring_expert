package io.github.jordannegreiros.vendas.domain;

import io.github.jordannegreiros.vendas.domain.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    Cliente rowToEntity(Integer id, String name);
}
