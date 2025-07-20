package com.example.backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class CreateMomentRequest {
    private String content;
    private List<String> imageUrls;
} 