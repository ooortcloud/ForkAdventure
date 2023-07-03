package com.capstone.design.dao;

import com.capstone.design.dto.MenuDTO;
import com.capstone.design.dto.MenuDTOCustom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    // Create
    int addMenuList(MenuDTO menu);


    // Read
    List<MenuDTOCustom> getMenuList();  // MyBatis가 리스트에 맞게 DTO에 따라 넣어서 출력해줌.

    // Update
    int updateMenu(MenuDTOCustom menu);

    // Delete
    int deleteByFoodName(String deleteFoodName);








}
