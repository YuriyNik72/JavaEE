package ru.geekbrains.service;


import org.springframework.stereotype.Service;
import ru.geekbrains.entity.Role;
import ru.geekbrains.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {


    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
