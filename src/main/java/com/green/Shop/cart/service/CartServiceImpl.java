package com.green.Shop.cart.service;

import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.cart.vo.CartVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{
    private final SqlSessionTemplate sqlSession;

    @Override
    public int insertCart(CartVO cartVO) {
        return sqlSession.insert("cartMapper.insertCart", cartVO);
    }

    @Override
    public List<CartVO> selectCartList(String memberId) {
        return sqlSession.selectList("cartMapper.selectCartList", memberId);
    }

    @Override
    public void deleteCart(CartVO cartVO) {
        sqlSession.delete("cartMapper.deleteCart", cartVO);
    }

    @Override
    public void updateCartCnt(CartVO cartVO) {
        sqlSession.update("cartMapper.updateCartCnt", cartVO);
    }

    @Override
    public List<BuyDetailVO> selectCartListForBuy(CartVO cartVO) {
        return sqlSession.selectList("cartMapper.selectCartListForBuy", cartVO);
    }
}
