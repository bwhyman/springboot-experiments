package org.example.mybatisflexexamples.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {
    private Long userId;
    private Long addressId;
    private String name;
    private String detail;
}
