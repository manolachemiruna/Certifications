package team7.Certifications.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team7.Certifications.dto.UserDto;
import team7.Certifications.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/admin/addUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        UserDto newUserDto=userService.createUser(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUserDto);
    }

    @GetMapping(value="/user/{name}")

    public Integer getUserId(@PathVariable("name") String name)
    {
        Integer id=userService.getUserIdByName(name);
        return id;
    }

}
