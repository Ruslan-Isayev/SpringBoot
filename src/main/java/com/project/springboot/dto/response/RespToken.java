package com.project.springboot.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespToken {

    private Long userId;
    private String token;
}
