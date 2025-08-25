package com.usersmicroservice.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CompanyInfoDto {
    private UUID id;
    private String name;
}
