package com.learning.learning.controller.dto.pageable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class OrderDTO {

    private int orderBy;
    private String orderDirection;
}
