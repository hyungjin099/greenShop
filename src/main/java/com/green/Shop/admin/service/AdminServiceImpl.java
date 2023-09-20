package com.green.Shop.admin.service;

import com.green.Shop.item.vo.CateVO;
import com.green.Shop.item.vo.ItemSearchVO;
import com.green.Shop.item.vo.ItemVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final SqlSessionTemplate sqlSession;

    @Override
    public List<CateVO> selectCategoryList() {
        return sqlSession.selectList("adminMapper.selectCategoryList");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertItem(ItemVO itemVO) {
        sqlSession.insert("adminMapper.insertItem", itemVO);
        sqlSession.insert("adminMapper.insertImgs", itemVO);
    }

    @Override
    public List<ItemVO> selectItemList(ItemSearchVO itemSearchVO) {
        return sqlSession.selectList("adminMapper.selectItemList", itemSearchVO);
    }

    @Override
    public void updateItemStock(ItemVO itemVO) {
        sqlSession.update("adminMapper.updateItemStock",itemVO);
    }

    @Override
    public void updateItemStatus(ItemVO itemVO) {
        sqlSession.update("adminMapper.updateItemStatus", itemVO);
    }


    @Override
    public String selectNextItemCode() {
        return sqlSession.selectOne("adminMapper.selectNextItemCode");
    }

}
