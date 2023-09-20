package com.green.Shop.buy.controller;

import com.green.Shop.buy.service.BuyService;
import com.green.Shop.buy.service.BuyServiceImpl;
import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.buy.vo.BuyVO;
import com.green.Shop.cart.service.CartService;
import com.green.Shop.cart.vo.CartVO;
import com.green.Shop.member.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/buy")
@RequiredArgsConstructor
public class BuyController {
    private final BuyService buyService;
    private final CartService cartService;

    //장바구니 품목 구매
    @GetMapping("/insertBuy")
    public String insertBuy(CartVO cartVO, HttpSession session){
        //1. 가져온 CART_CODE를 사용해서 ITEM_CODE, BUY_CNT, BUY_PRICE를 구함
        List<BuyDetailVO> buyDetailList = cartService.selectCartListForBuy(cartVO);

        //구매 총 가격
        int buyTotalPrice = 0;

        for(BuyDetailVO buyDetail : buyDetailList){
            buyTotalPrice += buyDetail.getBuyPrice();
        }

        //다음에 들어갈 BUY_CODE 조회
        String buyCode = buyService.selectNextBuyCode();

        //구매자 ID
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        String memberId = loginInfo.getMemberId();

        //구매
        BuyVO buyVO = new BuyVO();
        buyVO.setBuyCode(buyCode);
        buyVO.setMemberId(memberId);
        buyVO.setBuyTotalPrice(buyTotalPrice);
        buyVO.setBuyDetailList(buyDetailList);

        buyService.insertBuy(buyVO, cartVO);

        return "redirect:/cart/list";
    }

    //구매 목록 조회
    @GetMapping("/list")
    public String list(Model model, HttpSession session){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        //목록 조회
        List<BuyVO> buyList = buyService.selectBuyList(loginInfo.getMemberId());
        model.addAttribute("buyList", buyList);
        System.out.println(buyList);

        return "content/buy/buy_list";
    }

    //바로 구매하기
    @PostMapping("/regBuy")
    public String regBuy(BuyDetailVO buyDetailVO, BuyVO buyVO, HttpSession session){
        //다음에 들어갈 BUY_CODE값 조회
        String buyCode =  buyService.selectNextBuyCode();
        buyDetailVO.setBuyCode(buyCode);

        buyVO.setBuyCode(buyCode);
        buyVO.setMemberId(((MemberVO)session.getAttribute("loginInfo")).getMemberId());
        buyVO.setBuyTotalPrice(buyDetailVO.getBuyPrice());

        buyService.regBuy(buyVO, buyDetailVO);

        return "redirect:/buy/list";
    }

}
