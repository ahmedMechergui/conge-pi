package tn.conge.domain.api.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tn.conge.domain.api.dtos.UserDto;
import tn.conge.domain.entitites.Client;
import tn.conge.domain.entitites.DeliveryMan;
import tn.conge.domain.entitites.User;
import tn.conge.domain.storage.CustomFile;
import tn.conge.domain.storage.rest.CustomFileDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-14T20:20:19+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.19 (Ubuntu)"
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

        user.setDeliveryMan( userDtoToDeliveryMan( userDto ) );
        user.setClient( userDtoToClient( userDto ) );
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
        userDto.setFirstName( userDeliveryManFirstName( user ) );
        userDto.setLastName( userDeliveryManLastName( user ) );
        userDto.setPhoto( customFileToCustomFileDto( userDeliveryManPhoto( user ) ) );
        userDto.setPhone( user.getPhone() );
        userDto.setRole( user.getRole() );

        return userDto;
    }

    @Override
    public User partialUpdate(UserDto userDto, User user) {
        if ( userDto == null ) {
            return null;
        }

        if ( user.getDeliveryMan() == null ) {
            user.setDeliveryMan( new DeliveryMan() );
        }
        userDtoToDeliveryMan1( userDto, user.getDeliveryMan() );
        if ( user.getClient() == null ) {
            user.setClient( new Client() );
        }
        userDtoToClient1( userDto, user.getClient() );
        if ( userDto.getPhone() != null ) {
            user.setPhone( userDto.getPhone() );
        }
        if ( userDto.getRole() != null ) {
            user.setRole( userDto.getRole() );
        }

        return user;
    }

    protected CustomFile customFileDtoToCustomFile(CustomFileDto customFileDto) {
        if ( customFileDto == null ) {
            return null;
        }

        CustomFile customFile = new CustomFile();

        customFile.setId( customFileDto.getId() );
        customFile.setFileUrl( customFileDto.getFileUrl() );
        customFile.setFileUrlType( customFileDto.getFileUrlType() );

        return customFile;
    }

    protected DeliveryMan userDtoToDeliveryMan(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        DeliveryMan deliveryMan = new DeliveryMan();

        deliveryMan.setId( userDto.getId() );
        deliveryMan.setFirstName( userDto.getFirstName() );
        deliveryMan.setLastName( userDto.getLastName() );
        deliveryMan.setPhoto( customFileDtoToCustomFile( userDto.getPhoto() ) );

        return deliveryMan;
    }

    protected Client userDtoToClient(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        Client client = new Client();

        client.setId( userDto.getId() );

        return client;
    }

    private String userDeliveryManFirstName(User user) {
        if ( user == null ) {
            return null;
        }
        DeliveryMan deliveryMan = user.getDeliveryMan();
        if ( deliveryMan == null ) {
            return null;
        }
        String firstName = deliveryMan.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String userDeliveryManLastName(User user) {
        if ( user == null ) {
            return null;
        }
        DeliveryMan deliveryMan = user.getDeliveryMan();
        if ( deliveryMan == null ) {
            return null;
        }
        String lastName = deliveryMan.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    private CustomFile userDeliveryManPhoto(User user) {
        if ( user == null ) {
            return null;
        }
        DeliveryMan deliveryMan = user.getDeliveryMan();
        if ( deliveryMan == null ) {
            return null;
        }
        CustomFile photo = deliveryMan.getPhoto();
        if ( photo == null ) {
            return null;
        }
        return photo;
    }

    protected CustomFileDto customFileToCustomFileDto(CustomFile customFile) {
        if ( customFile == null ) {
            return null;
        }

        CustomFileDto customFileDto = new CustomFileDto();

        customFileDto.setId( customFile.getId() );
        customFileDto.setFileUrl( customFile.getFileUrl() );
        customFileDto.setFileUrlType( customFile.getFileUrlType() );

        return customFileDto;
    }

    protected void customFileDtoToCustomFile1(CustomFileDto customFileDto, CustomFile mappingTarget) {
        if ( customFileDto == null ) {
            return;
        }

        if ( customFileDto.getId() != null ) {
            mappingTarget.setId( customFileDto.getId() );
        }
        if ( customFileDto.getFileUrl() != null ) {
            mappingTarget.setFileUrl( customFileDto.getFileUrl() );
        }
        if ( customFileDto.getFileUrlType() != null ) {
            mappingTarget.setFileUrlType( customFileDto.getFileUrlType() );
        }
    }

    protected void userDtoToDeliveryMan1(UserDto userDto, DeliveryMan mappingTarget) {
        if ( userDto == null ) {
            return;
        }

        if ( userDto.getId() != null ) {
            mappingTarget.setId( userDto.getId() );
        }
        if ( userDto.getFirstName() != null ) {
            mappingTarget.setFirstName( userDto.getFirstName() );
        }
        if ( userDto.getLastName() != null ) {
            mappingTarget.setLastName( userDto.getLastName() );
        }
        if ( userDto.getPhoto() != null ) {
            if ( mappingTarget.getPhoto() == null ) {
                mappingTarget.setPhoto( new CustomFile() );
            }
            customFileDtoToCustomFile1( userDto.getPhoto(), mappingTarget.getPhoto() );
        }
    }

    protected void userDtoToClient1(UserDto userDto, Client mappingTarget) {
        if ( userDto == null ) {
            return;
        }

        if ( userDto.getId() != null ) {
            mappingTarget.setId( userDto.getId() );
        }
    }
}
