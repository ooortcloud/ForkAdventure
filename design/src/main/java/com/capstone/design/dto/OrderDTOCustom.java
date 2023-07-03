package com.capstone.design.dto;

import lombok.Data;

import java.util.List;


@Data
public class OrderDTOCustom {
    private Integer id;
    // 리스트를 문자열 형태로 DB에 저장... 어쩔 수 없음.
    private String foodName;
    private String numberOfFood;
}
