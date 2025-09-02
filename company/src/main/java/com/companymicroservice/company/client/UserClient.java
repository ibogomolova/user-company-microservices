package com.companymicroservice.company.client;

import com.companymicroservice.company.dto.UserInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserClient {
    @GetMapping("/users/by-company/{companyId}")
    List<UserInfoDto> getUsersByCompany(@PathVariable UUID companyId);
}
