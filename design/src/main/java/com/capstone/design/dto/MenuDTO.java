package com.capstone.design.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MenuDTO {
    private Integer id;
    private String foodName;
    private Integer price;
    private String oldFoodName;  // 실제 DB에는 존재하지 않는 칼럼.
}
