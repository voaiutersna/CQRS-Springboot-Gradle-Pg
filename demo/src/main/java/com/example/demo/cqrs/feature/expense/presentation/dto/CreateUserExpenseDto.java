package com.example.demo.cqrs.feature.expense.presentation.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateUserExpenseDto {

    @JsonProperty("email")
    @NotBlank(message = "Email can not be null!")
    @Email(message="Email is not valid format")
    private String email;

    @JsonProperty("total_amount")
    private double total_amount;

    @JsonProperty("note")
    private String note;

    @JsonProperty("category_id")
    @NotNull(message = "Category id can not be null!")
    private UUID category_id;

    @JsonProperty("payment_status")
    private int payment_status;

    public CreateUserExpenseDto(String email, double total_amount, String note, UUID category_id, int payment_status){
        this.email = email;
        this.total_amount = total_amount;
        this.note = note;
        this.category_id = category_id;
        this.payment_status = payment_status;
    }

    public String getEmail(){
        return this.email;
    }
    public double getTotalAmount(){
        return this.total_amount;
    }
    public String getNote(){
        return this.note;
    }
    public UUID getCategoryId(){
        return this.category_id;
    }
    public int getPaymentStatus(){
        return this.payment_status;
    }


    public void setEmail(String email){
        this.email = email;
    }
    public void setTotalAmount(double total_amount){
        this.total_amount = total_amount;
    }
    public void setNote(String note){
        this.note = note;
    }
    public void setCategoryId(UUID category_id){
        this.category_id = category_id;
    }
    public void setPaymentStatus(int payment_status){
        this.payment_status = payment_status;
    }


}
