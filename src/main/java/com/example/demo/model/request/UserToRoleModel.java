package com.example.demo.model.request;

import lombok.Data;

@Data
public class UserToRoleModel {
    private Long userId;
    private String roleName;
}
