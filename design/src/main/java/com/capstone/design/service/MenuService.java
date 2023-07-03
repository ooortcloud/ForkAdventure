package com.capstone.design.service;

import com.capstone.design.dto.MenuDTO;
import com.capstone.design.dao.MenuMapper;
import com.capstone.design.dto.MenuDTOCustom;
import com.capstone.design.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;

    // Create
    // 사장 - 메뉴 추가
    public Integer save(MenuDTO menu){
        int v = menuMapper.addMenuList(menu);  // Menu(id=4, foodName=김치볶음밥, price=8000)를 리스트에 추가
        return v;
    }



    // Read
    // 손님 - 메뉴 확인
    public List<MenuDTOCustom> findAll() {
        return menuMapper.getMenuList();
    }



    // Update
    // 사장 - 메뉴 수정
    public MenuDTO update(MenuDTO newMenu){

        // `iterator`로는 값을 변경할 수 없다. 그러니 근본인 배열형 반복문을 써서 탐색하자.
        for (MenuDTOCustom searchOldMenu : menuMapper.getMenuList()){
            if(searchOldMenu.getFoodName().equals(newMenu.getOldFoodName())){
                // id는 그대로 가져갈거임.
                searchOldMenu.setFoodName(newMenu.getFoodName());
                searchOldMenu.setPrice(newMenu.getPrice());
                menuMapper.updateMenu(searchOldMenu);
            }
        }
        return null;
    }

    // Delete
    // 사장 - 메뉴 삭제
    public Integer deleteByFoodName(String deleteFoodName){
//        Iterator<MenuDTO> iterator = menuMapper.getMenuList().iterator();
//        while(iterator.hasNext()){
//            MenuDTO next = iterator.next();
//            if(next.getFoodName().equals(deleteFoodName)){
//                iterator.remove();
//                return null;
//            }
//        }
        return menuMapper.deleteByFoodName(deleteFoodName);
    }
}
