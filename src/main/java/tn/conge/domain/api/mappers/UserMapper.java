package tn.conge.domain.api.mappers;

import org.mapstruct.*;
import tn.conge.domain.api.dtos.UserDto;
import tn.conge.domain.entitites.User;

@Mapper(uses = {ContractMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper extends EntityMapper<User,UserDto> {
}