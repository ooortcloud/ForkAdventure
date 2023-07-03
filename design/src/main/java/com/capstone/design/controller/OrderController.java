package com.capstone.design.controller;

import com.capstone.design.dto.OrderDTO;
import com.capstone.design.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://3.132.13.94:3000"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Create
    // MyBatis Query에서 Insert 반환값: 성공 시 1 반환, 실패 시 error 반환.
    // 손님 - 메뉴 주문
    @PostMapping(path = "/order")
    public ResponseEntity<String> orderMenu(@RequestBody OrderDTO order){
        /* 예상 요청 메세지 JSON 형식
        {
            "foodName" : ["김치볶음밥", "자장면"],
            "numberOfFood" : [2, 1]
        }
        */
        // 일단 list 형태로 받고 -> 그걸 문자열로 변환시켜서 -> DB에 저장하고 -> 다시 빼올 때 역순으로

        if (order.getFoodName().isEmpty() || order.getNumberOfFood().isEmpty())
            return new ResponseEntity<>("빈 리스트입니다.", HttpStatus.BAD_REQUEST);

        int v = orderService.setOrderMenu(order);
        if (v==1)
            return new ResponseEntity<>(null, HttpStatus.OK);
        else
            return new ResponseEntity<>("DB에서 주문 처리 도중 에러 발생...", HttpStatus.BAD_REQUEST);
    }

    // Read
    // MyBatis Query에서 Select 반환값: 성공 시 Select 반환값 반환, 실패 시 error 반환.
    @GetMapping("/getAllOrderedMenu")
    public ResponseEntity<List<OrderDTO>> getOrderedMenu(){
        List<OrderDTO> v = orderService.getAllOrderMenu();
        if (v.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.FOUND);
        return new ResponseEntity<>(v, HttpStatus.FOUND);
    }

    // Update
    // MyBatis Query에서 Update 반환값: 성공 시 Update된 행의 개수 반환, 실패 시 0 반환.



    // Delete
    // MyBatis Query에서 Delete 반환값: 성공 시 Delete된 행의 개수 반환, 실패 시 error 반환.

}
