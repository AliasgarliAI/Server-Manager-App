package com.aliasgarli.controller;

import com.aliasgarli.dto.Response;
import com.aliasgarli.model.Server;
import com.aliasgarli.service.impl.ServerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/server")
@RequiredArgsConstructor
public class ServerController {

    private final ServerServiceImpl serverService;
    private final HttpStatus statusOk = HttpStatus.OK;

    @GetMapping
    public ResponseEntity<Response> getAllServers() {
        Response body = responseBuilder(serverService.getAllServers(), statusOk);
        return new ResponseEntity<>(body, statusOk);
    }

    @GetMapping("/{serverIp}")
    public ResponseEntity<Response> getServerByIp(@PathVariable String serverIp) {
        Response body = responseBuilder(List.of(serverService.getServerByIpAdress(serverIp)), statusOk);
        return new ResponseEntity<>(body, statusOk);
    }

    @GetMapping
    public ResponseEntity<Response> getServersByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return new ResponseEntity<>(responseBuilder(serverService.getServersByPage(page, size), statusOk), statusOk);
    }

    @GetMapping()
    public ResponseEntity<Response> getSeversByName(@RequestParam String name) {
        return new ResponseEntity<>(responseBuilder(serverService.getServerByName(name), statusOk), statusOk);
    }

    @GetMapping("/{serverId}")
    public ResponseEntity<Response> getServerById(@PathVariable Long serverId) {
        Response body = responseBuilder(List.of(serverService.getServerById(serverId)), statusOk);
        return new ResponseEntity<>(body, statusOk);
    }

    @PostMapping
    public ResponseEntity<Response> createServer(@RequestBody @Valid Server server) {
        Response body = responseBuilder(List.of(serverService.createServer(server)),HttpStatus.CREATED);
        return new ResponseEntity<>(body,HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Response> updateServer(@RequestBody @Valid Server server){
        Response body = responseBuilder(List.of(serverService.updateServer(server)),statusOk);
        return new ResponseEntity<>(body,statusOk);
    }

    @DeleteMapping("{serverId}")
    public ResponseEntity<Response> deleteServer( @PathVariable Long serverId){
        serverService.deleteServer(serverId);
        return new ResponseEntity<>(responseBuilder(null,statusOk),statusOk);
    }

    @PostMapping
    public ResponseEntity<Response> pingServer(@RequestBody String serverIp) throws IOException {
        Response body = responseBuilder(List.of(serverService.ping(serverIp)), statusOk);
        return new ResponseEntity<>(body,statusOk);
    }

    private static Response responseBuilder(List<?> data, HttpStatus statusOk) {
        return Response.builder()
                .servers(data)
                .status(statusOk)
                .statusCode(statusOk.value())
                .message("Operation performed successfully")
                .build();
    }
}
