package com.example.demo.cqrs.feature.auth.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class GetUserDto {
    @JsonProperty("name")
    @NotBlank(message="Name cannot be null")
    private String name;

    @JsonProperty("email")
    @NotBlank(message="Email cannot be null")
    @Email(message="Email is not valid format")
    private String email;

    @JsonProperty("password")
    @NotBlank(message="Password cannot be null")
    private String password;

    public GetUserDto(String name,String email,String password){
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
       

    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }

}
