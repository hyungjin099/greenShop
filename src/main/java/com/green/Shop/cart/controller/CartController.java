package com.green.Shop.cart.controller;

import com.green.Shop.cart.service.CartService;
import com.green.Shop.cart.vo.CartVO;
import com.green.Shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    //장바구니에 상품 등록
    @ResponseBody
    @PostMapping("/insertCart")
    public int insertCart(CartVO cartVO, HttpSession session){
        //memberId값 세팅
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        cartVO.setMemberId(loginInfo.getMemberId());

        //상품 등록 쿼리
        return cartService.insertCart(cartVO);
    }



    //장바구니 목록 페이지
    @GetMapping("/list")
    public String cartList(HttpSession session, Model model){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        //장바구니 목록 조회
        List<CartVO> cartList = cartService.selectCartList("java");
        System.out.println(cartList);

        model.addAttribute("cartList", cartList);

        return "content/cart/cart_list";
    }

    //장바구니 품목 선택 삭제
    @GetMapping("/deleteCart")
    public String deleteCart(@RequestParam(name = "cartCodes") List<String> cartCodes, CartVO cartVO){
        //선택 품목 삭제
        cartVO.setCartCodeList(cartCodes);
        cartService.deleteCart(cartVO);

        return "redirect:/cart/list";
    }

    //장바구니 수량 변경
    @ResponseBody
    @PostMapping("/updateCartCnt")
    public void updateCartCnt(CartVO cartVO){
        cartService.updateCartCnt(cartVO);
    }

}







