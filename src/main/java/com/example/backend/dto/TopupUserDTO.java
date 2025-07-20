package com.example.backend.dto;

import com.example.backend.entity.Payment;

import lombok.Data;

@Data
public class TopupUserDTO {
    private Long id; // mã đơn nạp
    private String fullName; // tên người nạp
    private String avatarUrl; // img
    private String phoneNumber; // sdt
    private String createdAt; // ngày nạp
    private Long coin; // số tiền
    private Payment.PaymentStatus status; // trạng thái thanh toán
    private Payment.PaymentMethod method; // phương thức thanh toán
    
    // Thông tin ngân hàng cho rút tiền
    private String bankAccountNumber; // số tài khoản
    private String bankAccountName; // tên chủ tài khoản
    private String bankName; // tên ngân hàng
}