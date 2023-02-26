package com.aliasgarli.mapper;

import com.aliasgarli.dto.ServerDTO;
import com.aliasgarli.model.Server;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public abstract class ServerMapper {
    public abstract List<ServerDTO> toDtoList(List<Server> servers);
    public abstract ServerDTO toDto(Server server);
}
