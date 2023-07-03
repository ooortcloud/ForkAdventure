package com.capstone.design.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class OrderDTO {
    private Integer id;
    private List<String> foodName;
    private List<Integer> numberOfFood;
}
