package com.green.Shop.item.sevice;

import com.green.Shop.item.vo.ItemVO;

import java.util.List;

public interface ItemService {
    //상품 목록 조회
    public List<ItemVO> selectItemList();

    //상품 상세 조회
    public ItemVO selectItemDetail(String itemCode);

}
