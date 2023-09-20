package com.green.Shop.item.sevice;

import com.green.Shop.item.vo.ItemVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final SqlSessionTemplate sqlSession;

    @Override
    public List<ItemVO> selectItemList() {
        return sqlSession.selectList("itemMapper.selectItemList");
    }

    @Override
    public ItemVO selectItemDetail(String itemCode) {
        return sqlSession.selectOne("itemMapper.selectItemDetail", itemCode);
    }
}
