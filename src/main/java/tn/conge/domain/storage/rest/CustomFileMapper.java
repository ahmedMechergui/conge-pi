package tn.conge.domain.storage.rest;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tn.conge.domain.api.mappers.EntityMapper;
import tn.conge.domain.storage.CustomFile;

@Mapper(componentModel = "spring")
public interface CustomFileMapper extends EntityMapper<CustomFile, CustomFileDto> {
    CustomFileMapper INSTANCE = Mappers.getMapper(CustomFileMapper.class);
}
