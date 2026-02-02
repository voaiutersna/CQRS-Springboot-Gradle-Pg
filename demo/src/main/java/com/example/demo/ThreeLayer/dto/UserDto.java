package com.example.demo.ThreeLayer.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    @NotBlank(message="Email cannot be null")
    @Email(message="Email is not valid format")
    private String email;

    @JsonProperty("password")
    private String password;

    public UserDto(){

    }

    public UserDto(String name,String email,String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public UserDto(UUID id,String name,String email,String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }
    public UUID getId(){
        return this.id;
    }


    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setId(UUID id){
        this.id = id;
    }
}
