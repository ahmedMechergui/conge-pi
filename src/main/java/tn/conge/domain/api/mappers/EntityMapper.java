package tn.conge.domain.api.mappers;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface EntityMapper<D, E extends BaseEntity> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);

    E partialUpdate(D dto, @MappingTarget E entity);

}
