package team7.Certifications.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team7.Certifications.dto.UserDto;
import team7.Certifications.entity.User;


@Component
public class UserMapper {


    @Autowired
    private RequestMapper requestMapper;

    public UserDto toDto(User userEntity)
    {
        UserDto dto =new UserDto();
        dto.setId(userEntity.getId());
        dto.setName(userEntity.getName());
        dto.setRole(userEntity.getRole());


        return dto;
    }

    public User toEntity(UserDto userDto)
    {
        User user=new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setRole(userDto.getRole());

        return user;
    }


    @Autowired
    public void setRequestMapper(RequestMapper requestMapper) {
        this.requestMapper = requestMapper;
    }
}
