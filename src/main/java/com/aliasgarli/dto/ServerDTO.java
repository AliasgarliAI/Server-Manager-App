package com.aliasgarli.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServerDTO {
    private Long id;
    private String ipAddress;
    private String name;
    private String memory;
    private String type;
    private String imageUrl;
    private String status;
}
