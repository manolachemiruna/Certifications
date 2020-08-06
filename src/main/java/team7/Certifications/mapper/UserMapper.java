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
        dto.setPassword(userEntity.getPassword());



        return dto;
    }

    public User toEntity(UserDto userDto)
    {
        User user=new User();
        user.setName(userDto.getName());
        user.setRole("user");
        user.setPassword(userDto.getPassword());
        user.setPermissions("USER_ACCESS");
        user.setActive(1);

        return user;
    }


    @Autowired
    public void setRequestMapper(RequestMapper requestMapper) {
        this.requestMapper = requestMapper;
    }
}
