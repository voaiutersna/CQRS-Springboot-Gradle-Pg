package com.example.demo.cqrs.feature.expense.presentation.dto;

import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class GetAllExpenseByEmailDto {

    public GetAllExpenseByEmailDto() {}

    @JsonProperty("email")
    @NotBlank(message = "Email can not be null!")
    @Email(message="Email is not valid format")
    private String email;

    @JsonProperty("total_amount")
    private double total_amount;

    @JsonProperty("note")
    private String note;

    @JsonProperty("category_id")
    private UUID category_id;

    @JsonProperty("payment_status")
    private int payment_status;

    private Timestamp created_at;
    private Timestamp updated_at;

    public GetAllExpenseByEmailDto(String email){
        this.email = email;
    }

    public GetAllExpenseByEmailDto(String email, double total_amount, String note, UUID category_id, int payment_status){
        this.email = email;
        this.total_amount = total_amount;
        this.note = note;
        this.category_id = category_id;
        this.payment_status = payment_status;
    }

    public GetAllExpenseByEmailDto(String email, double total_amount, String note, UUID category_id, int payment_status, Timestamp create_at, Timestamp updated_at){
        this.email = email;
        this.total_amount = total_amount;
        this.note = note;
        this.category_id = category_id;
        this.payment_status = payment_status;
        this.created_at = create_at;
        this.updated_at = updated_at;
    }

    @JsonProperty("email")
    public String getEmail(){
        return this.email;
    }
    @JsonProperty("total_amount")
    public double getTotalAmount(){
        return this.total_amount;
    }
    @JsonProperty("note")
    public String getNote(){
        return this.note;
    }
    @JsonProperty("category_id")
    public UUID getCategoryId(){
        return this.category_id;
    }
    @JsonProperty("payment_status")
    public int getPaymentStatus(){
        return this.payment_status;
    }
    @JsonProperty("created_at")
    public Timestamp getCreatedAt(){
        return this.created_at;
    }
    @JsonProperty("updated_at")
    public Timestamp getUpdatedAt(){
        return this.updated_at;
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
