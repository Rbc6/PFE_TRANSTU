package com.pfe.dto;

import com.pfe.entites.Role;
import lombok.Data;

@Data
public class AuthentificationResponse {
    private String jwt;
    private Long userId;
    private Long roleId;
}
