package tn.conge.domain.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import tn.conge.domain.api.dtos.ContractDto;
import tn.conge.domain.entitites.Contract;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ContractMapper extends EntityMapper<Contract, ContractDto>{
}
