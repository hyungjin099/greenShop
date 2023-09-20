package com.green.Shop.admin.service;

import com.green.Shop.item.vo.CateVO;
import com.green.Shop.item.vo.ItemSearchVO;
import com.green.Shop.item.vo.ItemVO;

import java.util.List;

public interface AdminService {
    //상품 등록 시 카테고리 목록 조회
    public List<CateVO> selectCategoryList();

    //상품 등록 + 상품 이미지 등록
    public void insertItem(ItemVO itemVO);

    //상품 목록 조회
    public List<ItemVO> selectItemList(ItemSearchVO itemSearchVO);

    //상품 재고 변경
    public void updateItemStock(ItemVO itemVO);

    //상품 상태 변경
    public void updateItemStatus(ItemVO itemVO);


    //다음 ITEM_CODE 조회
    public String selectNextItemCode();

}
