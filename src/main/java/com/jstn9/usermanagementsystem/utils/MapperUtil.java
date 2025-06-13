package com.jstn9.usermanagementsystem.utils;

import org.modelmapper.ModelMapper;

public class MapperUtil {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <D, T> T mapToEntity(D dto, Class<T> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    public static <T, D> D mapToDto(T entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }
}
