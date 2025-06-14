package com.jstn9.usermanagementsystem.services;

import com.jstn9.usermanagementsystem.dto.PermissionDto;
import com.jstn9.usermanagementsystem.exceptions.ResourceNotFoundException;
import com.jstn9.usermanagementsystem.models.Permission;
import com.jstn9.usermanagementsystem.repositories.PermissionRepository;
import com.jstn9.usermanagementsystem.utils.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }


    public PermissionDto getPermissionById(Long id){
        return MapperUtil.mapToDto(findPermissionOrThrow(id), PermissionDto.class);
    }

    public List<PermissionDto> findAllPermissions(){
        return permissionRepository.findAll()
                .stream()
                .map(user -> MapperUtil.mapToDto(user, PermissionDto.class))
                .collect(Collectors.toList());
    }

    public PermissionDto createPermission(Permission Permission){
        return MapperUtil.mapToDto(permissionRepository.save(Permission), PermissionDto.class);
    }

    public PermissionDto updatePermission(Long id, Permission Permission){
        Permission findPermission = findPermissionOrThrow(id);
        findPermission.setName(Permission.getName());
        return MapperUtil.mapToDto(permissionRepository.save(findPermission), PermissionDto.class);
    }

    public void deletePermission(Long id){
        permissionRepository.deleteById(id);
    }

    private Permission findPermissionOrThrow(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission", id));
    }
}
