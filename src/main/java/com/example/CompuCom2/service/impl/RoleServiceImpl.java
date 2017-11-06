package com.example.CompuCom2.service.impl;

import com.example.CompuCom2.entity.Role;
import com.example.CompuCom2.repository.RoleRepository;
import com.example.CompuCom2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByType(String type) {
        return roleRepository.findByType(type);
    }
}
