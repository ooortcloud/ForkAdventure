package com.capstone.design.dao;

import com.capstone.design.dto.OrderDTO;
import com.capstone.design.dto.OrderDTOCustom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    // Create
    int setOrderedMenu(OrderDTOCustom order);

    // Read
    List<OrderDTO> getAllOrderedMenu();

    // Update


    // Delete

}
