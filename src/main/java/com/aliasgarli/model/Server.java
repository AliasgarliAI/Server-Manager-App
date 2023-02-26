package com.aliasgarli.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotEmpty(message = "Ip address can't be empty")
    @NotNull(message = "Ip address can't be empty")
    private String ipAddress;
    private String name;
    private String memory;
    private String type;
    private String imageUrl;
    @Column(nullable = false)
    @NotEmpty(message = "Server status must be selected")
    private Status status;
}
