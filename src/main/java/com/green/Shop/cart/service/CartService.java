package com.green.Shop.cart.service;

import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.cart.vo.CartVO;

import java.util.List;

public interface CartService {
    //장바구니에 상담 등록
    public int insertCart(CartVO cartVO);

    //장바구니 목록 조회
    public List<CartVO> selectCartList(String memberId);

    //장바구니 목록 삭제
    public void deleteCart(CartVO cartVO);

    //장바구니 수량 변경
    public void updateCartCnt(CartVO cartVO);

    //구매를 위한 장바구니 목록 조회
    public List<BuyDetailVO> selectCartListForBuy(CartVO cartVO);

}
