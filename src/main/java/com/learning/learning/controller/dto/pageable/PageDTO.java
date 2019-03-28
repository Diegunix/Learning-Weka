package com.learning.learning.controller.dto.pageable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class PageDTO extends OrderDTO{

    private int pageNumber;
    private int pageSize = 20;

}
