package com.green.Shop.buy.service;

import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.buy.vo.BuyVO;
import com.green.Shop.cart.vo.CartVO;

import java.util.List;

public interface BuyService {
    //다음에 들어갈 buy_code 조회
    public String selectNextBuyCode();

    //장바구니 품목 구매하기
    public void insertBuy(BuyVO buyVO, CartVO cartVO);

    //구매 목록 조회
    public List<BuyVO> selectBuyList(String memberId);

    //상품 단일 구매
    public void regBuy(BuyVO buyVO, BuyDetailVO buyDetailVO);

}
