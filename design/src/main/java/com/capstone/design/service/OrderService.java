package com.capstone.design.service;

import com.capstone.design.dao.OrderMapper;
import com.capstone.design.dto.OrderDTO;
import com.capstone.design.dto.OrderDTOCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    // Create
    // 손님 - 메뉴 주문
    public int setOrderMenu(OrderDTO order){
        // DB 저장 방식을 바꿔야 하나...inner join 형식으로 바꿀까 고민 중

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < order.getFoodName().size(); i++) {
            sb1.append(order.getFoodName().get(i));
            sb2.append(order.getNumberOfFood().get(i));
            if (i < order.getFoodName().size() - 1) {
                sb1.append(", ");
                sb2.append(", ");
            }
        }

        OrderDTOCustom orderDTOCustom = new OrderDTOCustom();
        orderDTOCustom.setFoodName(String.valueOf(sb1));
        orderDTOCustom.setNumberOfFood(String.valueOf(sb2));
        orderDTOCustom.setFoodName("김치"+"자장면");
        orderDTOCustom.setNumberOfFood("12");
        System.out.println("orderDTOCustom.getFoodName() = " + orderDTOCustom.getFoodName());
        System.out.println("orderDTOCustom.getNumberOfFood() = " + orderDTOCustom.getNumberOfFood());

        if (orderMapper.setOrderedMenu(orderDTOCustom) != 1)
            return -1;
        return 0;
    }


    // Read
    public List<OrderDTO> getAllOrderMenu(){
        return orderMapper.getAllOrderedMenu();
    }

    // Update


    // Delete
}
