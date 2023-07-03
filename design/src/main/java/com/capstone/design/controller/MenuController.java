package com.capstone.design.controller;

import com.capstone.design.dto.MenuDTO;
import com.capstone.design.dto.MenuDTOCustom;
import com.capstone.design.service.MenuService;
import com.capstone.design.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://3.132.13.94:3000"})  // CORS 문제 해결을 위함.
public class MenuController {
    @Autowired
    private MenuService menuService;

    // 요청 테스트
    @GetMapping(path = "/test")
    public ResponseEntity<String> requestTestMethod(@RequestBody String s) {
        System.out.println("s = " + s);
        String message = s;
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // Create
    // MyBatis Query에서 Insert 반환값: 성공 시 1 반환, 실패 시 error 반환.
    // 사장 - 메뉴 추가
    @PostMapping(path="/addMenu")
    public ResponseEntity<MenuDTO> addMenu(@RequestBody MenuDTO menu){
        /* 예상 요청 메세지 JSON 형식
        {
            "foodName" : "김치볶음밥",
            "price" : 8000
        }
        */
        if ( menu.getFoodName().equals(null)) {  // null: 변수 자체를 안 보냄.
            System.out.println("음식 이름을 전해줘야 합니다.");
            return new ResponseEntity<>(menu, HttpStatus.BAD_REQUEST);
        }

        if ( menu.getFoodName().equals("")) {  // "": 변수를 보내긴 하는데 아무 값도 입력하지 않음.
            System.out.println("음식 이름을 입력해야 합니다.");
            return new ResponseEntity<>(menu, HttpStatus.BAD_REQUEST);
        }

        for (MenuDTOCustom searchMenu : menuService.findAll() ){  // 로직 처리 전에 이름 중복 여부 확인
            if(menu.getFoodName().equals(searchMenu.getFoodName()))
                return new ResponseEntity<>(menu, HttpStatus.ALREADY_REPORTED);
        }
        
        Integer savedMenu = menuService.save(menu);

        if (savedMenu != 1)
            return new ResponseEntity<>(menu, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }


    // Read
    // MyBatis Query에서 Select 반환값: 성공 시 Select 반환값 반환, 실패 시 error 반환.

    // 손님 - 메뉴 확인
    @GetMapping(path="/getMenu")
    public ResponseEntity<List<MenuDTOCustom>> getMenu(){
        List<MenuDTOCustom> v = menuService.findAll();
        if (v==null)
            return new ResponseEntity<>(null, HttpStatus.FOUND);
        return new ResponseEntity<>(v, HttpStatus.FOUND);
    }



    // Update
    // MyBatis Query에서 Update 반환값: 성공 시 Update된 행의 개수 반환, 실패 시 0 반환.

    // 사장 - 메뉴 수정
    @PostMapping(path="/updateMenu")  // @Put 메소드로도 처리 가능.
    public void updateMenu(@RequestBody MenuDTO menu){
        /* 예상 요청 메세지 JSON 형식
        {
            "oldFoodName" : "김치볶음밥",
            "foodName" : "핫도그",
            "price" : 3000
        }
        */
        menuService.update(menu);
    }


    // Delete
    // MyBatis Query에서 Delete 반환값: 성공 시 Delete된 행의 개수 반환, 실패 시 error 반환.

    // 사장 - 메뉴 삭제
    @DeleteMapping(path="/deleteMenu/{foodName}")
    public ResponseEntity<String> deleteMenu(@PathVariable String foodName){
        // System.out.println("foodName = " + foodName);
        Integer deletedMenu = menuService.deleteByFoodName(foodName);
        if(deletedMenu==null)
            return new ResponseEntity<>(foodName, HttpStatus.NOT_FOUND);
        if (deletedMenu < 1)
            return new ResponseEntity<>(foodName, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(foodName, HttpStatus.ACCEPTED);
    }
    /* 아래 코드로도 작성 가능
    This code removes all menu items that have the given deleteFoodName from the menuList.
    It returns "Delete OK" if at least one item was removed, and null if no item was removed.

    public String deleteByFood(String deleteFoodName){
        boolean deleted = menuRepository.getMenuList().removeIf(menu -> menu.getFoodName().equals(deleteFoodName));
        return deleted ? "Delete OK" : null;
    }
     */






}
