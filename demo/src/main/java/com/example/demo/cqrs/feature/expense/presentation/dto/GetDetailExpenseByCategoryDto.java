package com.example.demo.cqrs.feature.expense.presentation.dto;

import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;


public class GetDetailExpenseByCategoryDto {
    public GetDetailExpenseByCategoryDto() {}

    @JsonProperty("total_amount")
    private double total_amount;

    @JsonProperty("note")
    private String note;

    @JsonProperty("category_id")
    private UUID category_id;

    @JsonProperty("payment_status_id")
    private int payment_status_id;

    @JsonProperty("created_at")
    private Timestamp created_at;

    @JsonProperty("updated_at")
    private Timestamp updated_at;

    public GetDetailExpenseByCategoryDto(UUID category_id){
        this.category_id = category_id;
    }

    public GetDetailExpenseByCategoryDto(double total_amount, String note, UUID category_id, int payment_status){
        this.total_amount = total_amount;
        this.note = note;
        this.category_id = category_id;
        this.payment_status_id = payment_status;
    }

    public GetDetailExpenseByCategoryDto(double total_amount, String note, UUID category_id, int payment_status, Timestamp create_at, Timestamp updated_at){
        this.total_amount = total_amount;
        this.note = note;
        this.category_id = category_id;
        this.payment_status_id = payment_status;
        this.created_at = create_at;
        this.updated_at = updated_at;
    }


    public double gettotal_amount(){
        return this.total_amount;
    }

    public String getnote(){
        return this.note;
    }

    public UUID getcategory_id(){
        return this.category_id;
    }

    public int getpayment_status_id(){
        return this.payment_status_id;
    }

    public Timestamp getcreated_at(){
        return this.created_at;
    }

    public Timestamp getupdated_at(){
        return this.updated_at;
    }


    public void settotal_amount(double total_amount){
        this.total_amount = total_amount;
    }
    public void setnote(String note){
        this.note = note;
    }
    public void setcategory_id(UUID category_id){
        this.category_id = category_id;
    }
    public void setpayment_status_id(int payment_status_id){
        this.payment_status_id = payment_status_id;
    }
}
