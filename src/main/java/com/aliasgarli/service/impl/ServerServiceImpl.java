package com.aliasgarli.service.impl;

import com.aliasgarli.dto.ServerDTO;
import com.aliasgarli.exception.ServerException;
import com.aliasgarli.mapper.ServerMapper;
import com.aliasgarli.model.Server;
import com.aliasgarli.model.Status;
import com.aliasgarli.repository.ServerRepository;
import com.aliasgarli.service.inter.ServerServiceInter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerServiceInter {

    private final ServerRepository serverRepository;
    private ServerMapper serverMapper;

    @Override
    public List<Server> getAllServers() {
        return serverRepository.findAll();
    }

    @Override
    public ServerDTO getServerByIpAdress(String ipAddress) {
        return serverRepository
                .findByIpAddress(ipAddress)
                .map(serverMapper::toDto)
                .orElseThrow(() -> new ServerException("There is not any existing server with given server ip address"));
    }

    @Override
    public List<ServerDTO> getServersByPage(Integer page, Integer size) {
        Pageable pageable = Pageable.unpaged();
        if (page !=null && size !=null ){
            pageable = PageRequest.of(page,size);
        }
        return Optional.of(serverRepository.findAll(pageable).toList())
                .map(serverMapper::toDtoList).orElseThrow(()-> new ServerException(""));
    }

    @Override
    public List<ServerDTO> getServerByName(String name) {
        return serverRepository.findByName(name)
                .map(serverMapper::toDtoList)
                .orElseThrow(()->new ServerException("There is not any existing  server with given server name"));
    }

    @Override
    public ServerDTO getServerById(Long id) {
        return serverRepository.findById(id)
                .map(serverMapper::toDto)
                .orElseThrow(()->new ServerException("There is not any existing server with specified server id"));
    }

    @Override
    public ServerDTO createServer(Server server) {
        server.setImageUrl(setServerImgaeUrl());
        return Optional.of(serverRepository.save(server))
                .map(serverMapper::toDto)
                .orElseThrow(()-> new ServerException("Something went wrong"));
    }

    @Override
    public ServerDTO updateServer(Server server) {
        return Optional.of(serverRepository.save(server))
                .map(serverMapper::toDto)
                .orElseThrow(()-> new ServerException("Something went wrong"));
    }

    @Override
    public boolean deleteServer(Long serverId) {
        serverRepository.deleteById(serverId);
        return TRUE;
    }

    @Override
    public ServerDTO ping(String serverIp) throws IOException {
        Server server = serverRepository.findByIpAddress(serverIp)
                .orElseThrow(() -> new ServerException("There is not any existing server with specified server id"));

        InetAddress address = InetAddress.getByName(serverIp);

        server.setStatus( address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);

        return serverMapper.toDto(serverRepository.save(server));
    }

    private String setServerImgaeUrl() {
        String[] imageNames = {"server1.png","server2.png","server3.png","server4.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/server/image/"+ imageNames[new Random().nextInt(4)]).toUriString();
    }
}
