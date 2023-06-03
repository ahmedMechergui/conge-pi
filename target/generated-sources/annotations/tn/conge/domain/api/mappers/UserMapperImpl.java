package tn.conge.domain.api.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tn.conge.domain.api.dtos.UserDto;
import tn.conge.domain.entitites.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-03T14:34:21+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.17 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public List<User> toEntity(List<UserDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtoList.size() );
        for ( UserDto userDto : dtoList ) {
            list.add( toEntity( userDto ) );
        }

        return list;
    }

    @Override
    public List<UserDto> toDto(List<User> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( entityList.size() );
        for ( User user : entityList ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public User toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setPhone( userDto.getPhone() );
        user.setRole( userDto.getRole() );

        return user;
    }

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setPhone( user.getPhone() );
        userDto.setRole( user.getRole() );

        return userDto;
    }

    @Override
    public User partialUpdate(UserDto userDto, User user) {
        if ( userDto == null ) {
            return null;
        }

        if ( userDto.getPhone() != null ) {
            user.setPhone( userDto.getPhone() );
        }
        if ( userDto.getRole() != null ) {
            user.setRole( userDto.getRole() );
        }

        return user;
    }
}
