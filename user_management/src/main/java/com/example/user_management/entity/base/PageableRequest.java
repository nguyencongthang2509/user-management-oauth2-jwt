package com.example.user_management.entity.base;

import com.example.user_management.infrastructure.constant.PaginationConstant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageableRequest {

    private int page = PaginationConstant.DEFAULT_PAGE;
    private int size = PaginationConstant.DEFAULT_SIZE;
}

