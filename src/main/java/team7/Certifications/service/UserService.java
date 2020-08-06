package team7.Certifications.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team7.Certifications.dto.UserDto;
import team7.Certifications.entity.User;
import team7.Certifications.mapper.UserMapper;
import team7.Certifications.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private  final UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper=userMapper;
        this.passwordEncoder=passwordEncoder;
    }

    public UserDto createUser(UserDto userDto)
    {
        User user=userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser=userRepository.save(user);
        UserDto savedUserDto=userMapper.toDto(savedUser);

        return savedUserDto;
    }


}
