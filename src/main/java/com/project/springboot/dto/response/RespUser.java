package com.project.springboot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespUser {

    private String username;
    private String fullName;
    @JsonProperty(value = "token")
    private RespToken respToken;
}
