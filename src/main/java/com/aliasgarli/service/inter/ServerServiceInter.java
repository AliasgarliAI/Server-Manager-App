package com.aliasgarli.service.inter;

import com.aliasgarli.dto.ServerDTO;
import com.aliasgarli.model.Server;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ServerServiceInter {
    List<Server> getAllServers();

    ServerDTO getServerByIpAdress(String ipAddress);

    List<ServerDTO> getServersByPage(Integer page, Integer size);

    List<ServerDTO> getServerByName(String name);

    ServerDTO getServerById(Long id);

    ServerDTO createServer(Server server);

    ServerDTO updateServer(Server server);

    boolean deleteServer(Long serverId);

    ServerDTO ping(String serverIp) throws IOException;
}
