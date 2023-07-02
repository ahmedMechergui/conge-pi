package tn.conge.domain.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import tn.conge.domain.api.dtos.ReplacementDto;
import tn.conge.domain.entitites.Replacement;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReplacementMapper extends EntityMapper<Replacement, ReplacementDto>{
}
