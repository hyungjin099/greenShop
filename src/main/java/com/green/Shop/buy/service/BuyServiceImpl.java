package com.green.Shop.buy.service;

import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.buy.vo.BuyVO;
import com.green.Shop.cart.vo.CartVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Component, Service, Controlelr, Repository...
//BuyServiceImpl buyServiceImpl = new BuyServiceImpl();
@Service
@RequiredArgsConstructor
public class BuyServiceImpl implements BuyService{
    private final SqlSessionTemplate sqlSession;

    @Override
    public String selectNextBuyCode() {
        return sqlSession.selectOne("buyMapper.selectNextBuyCode");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBuy(BuyVO buyVO, CartVO cartVO) {
        sqlSession.insert("buyMapper.insertBuy", buyVO);
        sqlSession.insert("buyMapper.insertBuyDetail", buyVO);
        sqlSession.delete("cartMapper.deleteCart", cartVO);
    }

    @Override
    public List<BuyVO> selectBuyList(String memberId) {
        return sqlSession.selectList("buyMapper.selectBuyList", memberId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void regBuy(BuyVO buyVO, BuyDetailVO buyDetailVO) {
        sqlSession.insert("buyMapper.insertBuy", buyVO);
        sqlSession.insert("buyMapper.regBuyDetail", buyDetailVO);
    }
}









