package com.example.crio.rentAndRead.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentBookRequest {
    private Long userId;
    private Long bookId;
}
