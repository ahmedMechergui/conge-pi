package tn.conge.domain.api.mappers;

import org.mapstruct.*;
import tn.conge.domain.Entities.BaseEntity;
import tn.conge.domain.Entities.Client;
import tn.conge.domain.Entities.User;
import tn.conge.domain.api.dtos.UserDto;


/*@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper extends EntityMapper<UserDto, User> {

    @Mappings(value = {
           // @Mapping(source = ".", target = User.Fields.deliveryMan, resultType = DeliveryMan.class),
            //@Mapping(source = ".", target = User.Fields.client, resultType = Client.class),
            @Mapping(target = BaseEntity.Fields.id, ignore = true)
    })
    User toEntity(UserDto userDto);

    @InheritInverseConfiguration(name = "toEntity")
    @Mappings(value = {
            @Mapping(target = BaseEntity.Fields.id, source = BaseEntity.Fields.id)
    })
    UserDto toDto(User user);


    @Mappings(value = {
            @Mapping(target = BaseEntity.Fields.id, ignore = true)
    })
    @InheritConfiguration(name = "toEntity")
    User partialUpdate(UserDto userDto, @MappingTarget User user);
}*/