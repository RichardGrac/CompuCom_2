package com.example.CompuCom2.service;

import com.example.CompuCom2.entity.Role;

public interface RoleService {
    Role findByType(String type);
}
