package com.apiexample2.Payload;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewDto {
    private List<StudentDto> allDtos;
    private int pageNo;
    private int pageSize;
    private boolean isFirst;
    private boolean isLast;
    private int totalPages;
    private Long totalElements;
}
