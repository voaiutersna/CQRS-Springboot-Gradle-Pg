package com.example.demo.cqrs.feature.expense.presentation.dto;

import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateExpenseDto {

    @JsonProperty("total_amount")
    private Double total_amount;

    @JsonProperty("note")
    private String note;

    @JsonProperty("category_id")
    private UUID category_id;

    @JsonProperty("payment_status")
    private Integer payment_status;

    @JsonProperty("created_at")
    private Timestamp created_at;

    @JsonProperty("updated_at")
    private Timestamp updated_at;

    public UpdateExpenseDto() {}

    public UpdateExpenseDto(Double total_amount, String note, UUID category_id, Integer payment_status, Timestamp created_at, Timestamp updated_at) {
        this.total_amount = total_amount;
        this.note = note;
        this.category_id = category_id;
        this.payment_status = payment_status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Double getTotalAmount() {
        return this.total_amount;
    }

    public String getNote() {
        return this.note;
    }

    public UUID getCategoryId() {
        return this.category_id;
    }

    public Integer getPaymentStatus() {
        return this.payment_status;
    }

    public Timestamp getCreatedAt() {
        return this.created_at;
    }

    public Timestamp getUpdatedAt() {
        return this.updated_at;
    }

    public void setTotalAmount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setCategoryId(UUID category_id) {
        this.category_id = category_id;
    }

    public void setPaymentStatus(Integer payment_status) {
        this.payment_status = payment_status;
    }

    public void setCreatedAt(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setUpdatedAt(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
