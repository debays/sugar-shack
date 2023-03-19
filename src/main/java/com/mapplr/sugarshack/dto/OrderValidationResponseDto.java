package com.mapplr.sugarshack.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderValidationResponseDto {

    private boolean isOrderValid;
    private List<String> errors;
}
