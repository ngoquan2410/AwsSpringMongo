package com.example.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface IMapper<E, D> {
    D toDto(E entity);

    E toEntity(D dto);

    List<D> toDtos(List<E> entities);

    List<E> toEntities(List<D> dtos);

}
