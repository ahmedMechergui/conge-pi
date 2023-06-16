package tn.conge.domain.storage.rest;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tn.conge.domain.storage.CustomFile;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-16T22:20:46+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.19 (Amazon.com Inc.)"
)
@Component
public class CustomFileMapperImpl implements CustomFileMapper {

    @Override
    public CustomFile toEntity(CustomFileDto dto) {
        if ( dto == null ) {
            return null;
        }

        CustomFile customFile = new CustomFile();

        customFile.setId( dto.getId() );
        customFile.setFileUrl( dto.getFileUrl() );
        customFile.setFileUrlType( dto.getFileUrlType() );

        return customFile;
    }

    @Override
    public CustomFileDto toDto(CustomFile entity) {
        if ( entity == null ) {
            return null;
        }

        CustomFileDto customFileDto = new CustomFileDto();

        customFileDto.setId( entity.getId() );
        customFileDto.setFileUrl( entity.getFileUrl() );
        customFileDto.setFileUrlType( entity.getFileUrlType() );

        return customFileDto;
    }

    @Override
    public List<CustomFile> toEntity(List<CustomFileDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<CustomFile> list = new ArrayList<CustomFile>( dtoList.size() );
        for ( CustomFileDto customFileDto : dtoList ) {
            list.add( toEntity( customFileDto ) );
        }

        return list;
    }

    @Override
    public List<CustomFileDto> toDto(List<CustomFile> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CustomFileDto> list = new ArrayList<CustomFileDto>( entityList.size() );
        for ( CustomFile customFile : entityList ) {
            list.add( toDto( customFile ) );
        }

        return list;
    }

    @Override
    public CustomFile partialUpdate(CustomFileDto dto, CustomFile entity) {
        if ( dto == null ) {
            return null;
        }

        entity.setId( dto.getId() );
        entity.setFileUrl( dto.getFileUrl() );
        entity.setFileUrlType( dto.getFileUrlType() );

        return entity;
    }
}
