package com.example.CompuCom2.component;


import com.example.CompuCom2.entity.Role;
import com.example.CompuCom2.repository.RoleRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleToRoleConverter implements Converter<Object, Role> {

    private static final Log LOG = LogFactory.getLog(RoleToRoleConverter.class);

    private final RoleRepository roleRepository;

    @Autowired
    public RoleToRoleConverter(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role convert(Object element) {
        Integer id = Integer.parseInt((String)element);
        return roleRepository.findById(id);
    }

}
