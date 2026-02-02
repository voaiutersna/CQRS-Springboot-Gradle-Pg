package com.example.demo.ThreeLayer.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.ThreeLayer.dto.UserDto;
import com.example.demo.ThreeLayer.model.UserData;
import com.example.demo.ThreeLayer.repositories.UserRepo;

import jakarta.validation.Valid;


@RestController
public class UserController {

    @Autowired
    UserRepo userepo;


    @GetMapping("/user-dto-autowired")  
    public List<UserDto> getMethodNameMemmory() {
        List<UserDto> ListUserDto = userepo.findAll().stream().map(user-> new UserDto(user.getId(),user.getName(),user.getEmail(),user.getPassword())).toList();
        return ListUserDto;
    }

    @GetMapping("/user-dto-autowired/{id}")
    public UserDto getUserByIdAutowired(@PathVariable UUID id) {
        UserDto user = userepo.findAll().stream()
        .filter(finduser -> finduser.getId().equals(id))
        .map(mapper-> new UserDto(mapper.getId(),mapper.getName(),mapper.getEmail(),mapper.getPassword()))
        .findFirst().orElse(null);
        return user;
    }

    @PostMapping("/user-dto-autowired-email")
    public UserDto getMethodEmail(@RequestBody UserDto userDto) {
        // System.out.println("email value: " + email);
        UserDto UserWithEmail = userepo.findByemail(userDto.getEmail())
        .map(user-> new UserDto(user.getId(),user.getName(),user.getEmail(),user.getPassword())).orElse(null);
        if (UserWithEmail == null){
            return null;
        }
        return UserWithEmail;
    }

    @PostMapping("/user-email-destruce")
    public UserDto getMethodDestructure(@RequestBody Map<String, String> body) {
        // System.out.println("email value: " + email);
        String email = body.get("email");
        UserDto UserWithEmail = userepo.findByemail(email)
        .map(user-> new UserDto(user.getId(),user.getName(),user.getEmail(),user.getPassword())).orElse(null);
        if (UserWithEmail == null){
            return null;
        }
        return UserWithEmail;
    }

    @PostMapping("/user-dto-autowired-name") //handler err
    public UserDto getMethodName(@RequestBody UserDto userDto) {
        UserDto UserWithEmail = userepo.findByname(userDto.getName())
        .map(user-> new UserDto(user.getId(),user.getName(),user.getEmail(),user.getPassword())).orElse(null);
        if (UserWithEmail == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"user not found");
        }
        return UserWithEmail;
    }

    @PostMapping("/user-dto-autowired") //handle err
    public UserDto postUser(@Valid @RequestBody UserDto user){
        UserData savedUser = userepo.save(new UserData(user.getName(),user.getEmail(),user.getPassword()));

        return new UserDto(savedUser.getId(),savedUser.getName(),savedUser.getEmail(),savedUser.getPassword());
    }


    @PutMapping("/user-dto-autowired/{id}")
    public String putMethodName(@PathVariable UUID id, @RequestBody UserDto userDto) {
        UserData user = userepo.findById(id).orElse(null);
        if (user == null) {
            return "User not found";
        }
        
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userepo.save(user);
        return "Update Successfully";

        // save() มันรู้ได้ไงว่ามันจะ INSERT หรือ UPDATE
        // ถ้า entity.getId() == null  →  INSERT (สร้างใหม่)
        // ถ้า entity.getId() != null  →  UPDATE (แก้ไข)
    }
    
    @DeleteMapping("/user-dto-autowired/{id}")
    public String deleteUser(@PathVariable UUID id){
        UserData userTodelete = userepo.findAll().stream()
        .filter(user-> user.getId().equals(id))
        .findFirst()
        .orElse(null);
        if (userTodelete != null){
            userepo.delete(userTodelete);
            return "Successfully delete user";
        }
        return "user not found";
    }
}

