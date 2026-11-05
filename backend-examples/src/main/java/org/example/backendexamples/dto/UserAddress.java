package org.example.backendexamples.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backendexamples.entity.Address;
import org.example.backendexamples.entity.User;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {
    private User user;
    private List<Address> addresses;
}
