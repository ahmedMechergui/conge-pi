package tn.conge.domain.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import tn.conge.domain.api.dtos.EmployeeDto;
import tn.conge.domain.entitites.Employee;

@Mapper(uses = {ContractMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper extends EntityMapper<Employee, EmployeeDto> {
}
