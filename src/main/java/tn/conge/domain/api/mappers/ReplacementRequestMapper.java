package tn.conge.domain.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import tn.conge.domain.api.dtos.ReplacementRequestDto;
import tn.conge.domain.entitites.ReplacementRequest;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = {ReplacementRequestMapper.class})
public interface ReplacementRequestMapper extends EntityMapper<ReplacementRequest, ReplacementRequestDto> {
}
