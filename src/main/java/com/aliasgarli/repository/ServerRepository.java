package com.aliasgarli.repository;

import com.aliasgarli.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {
    Optional<Server> findByIpAddress(String ipAddress);
    Optional<List<Server>> findByName(String name);
}
