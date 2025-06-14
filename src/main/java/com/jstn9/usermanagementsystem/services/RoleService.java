package com.jstn9.usermanagementsystem.services;

import com.jstn9.usermanagementsystem.dto.RoleDto;
import com.jstn9.usermanagementsystem.exceptions.ResourceNotFoundException;
import com.jstn9.usermanagementsystem.models.Role;
import com.jstn9.usermanagementsystem.repositories.RoleRepository;
import com.jstn9.usermanagementsystem.utils.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleDto getRoleById(Long id){
        return MapperUtil.mapToDto(findRoleOrThrow(id), RoleDto.class);
    }

    public List<RoleDto> findAllRoles(){
        return roleRepository.findAll()
                .stream()
                .map(user -> MapperUtil.mapToDto(user, RoleDto.class))
                .collect(Collectors.toList());
    }

    public RoleDto createRole(Role role){
        return MapperUtil.mapToDto(roleRepository.save(role), RoleDto.class);
    }

    public RoleDto updateRole(Long id, Role role){
        Role findRole = findRoleOrThrow(id);
        findRole.setName(role.getName());
        return MapperUtil.mapToDto(roleRepository.save(findRole), RoleDto.class);
    }

    public void deleteRole(Long id){
        roleRepository.deleteById(id);
    }

    private Role findRoleOrThrow(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", id));
    }
}

