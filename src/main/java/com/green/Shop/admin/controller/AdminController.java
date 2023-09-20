package com.green.Shop.admin.controller;

import com.green.Shop.admin.service.AdminService;
import com.green.Shop.admin.service.MenuService;
import com.green.Shop.admin.vo.SubMenuVO;
import com.green.Shop.item.vo.ImgVO;
import com.green.Shop.item.vo.ItemSearchVO;
import com.green.Shop.item.vo.ItemVO;
import com.green.Shop.member.vo.MemberVO;
import com.green.Shop.util.ConstantVariable;
import com.green.Shop.util.UploadUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final MenuService menuService;

    //상품 등록 페이지로 이동
    @GetMapping("/regItemForm")
    public String regItemForm(SubMenuVO subMenuVO , Model model){
        //카테고리 목록 조회
        model.addAttribute("categoryList", adminService.selectCategoryList());

        return "content/admin/reg_item";
    }

    //상품등록
    @PostMapping("/regItem")
    public String regItem(ItemVO itemVO, MultipartFile mainImg, MultipartFile[] subImg){
        //--- 상품 이미지 등록 ---//
        //0. 다음에 들어가야 할 ITEM_CODE를 조회
        String itemCode = adminService.selectNextItemCode();

        //2. 이미지 정보 하나가 들어갈 수 있는 통!
        //첨부파일 기능 단일
        ImgVO vo = UploadUtil.uploadFile(mainImg);

        //첨부파일 기능 다중
        List<ImgVO> imgList = UploadUtil.multiFileUpload(subImg);
        imgList.add(vo);

        for(ImgVO imgVO : imgList){
            imgVO.setItemCode(itemCode);
        }

        itemVO.setImgList(imgList);

        //상품 등록  + 이미지 등록 쿼리
        itemVO.setItemCode(itemCode);
        adminService.insertItem(itemVO);

        return "redirect:/admin/regItemForm";
    }

    //상품관리 페이지로 이동
    @RequestMapping("/itemManage")
    public String itemManage(SubMenuVO subMenuVO, Model model, ItemSearchVO itemSearchVO){
        if(subMenuVO.getSubMenuCode() == null){
            subMenuVO.setSubMenuCode("1");
        }

        //상품 목록 조회
        model.addAttribute("itemList", adminService.selectItemList(itemSearchVO));

        //카테고리 목록 조회
        model.addAttribute("categoryList", adminService.selectCategoryList());

        return "content/admin/item_manage";
    }

    //상품 재고 변경
    @PostMapping("/updateItemStock")
    public String updateItemStock(ItemVO itemVO){
        adminService.updateItemStock(itemVO);
        return "redirect:/admin/itemManage";
    }

    //상품 상태 변경
    @ResponseBody
    @PostMapping("/updateItemStatusFetch")
    public void updateItemStatus(ItemVO itemVO){
        adminService.updateItemStatus(itemVO);
    }

    //상단 메뉴 중 고객관리 클릭시 회원 정보 수정 페이지로 이동
    @GetMapping("/memberManage")
    public String memberManage(SubMenuVO subMenuVO, Model model){
        if(subMenuVO.getSubMenuCode() == null){
            subMenuVO.setSubMenuCode("4");
        }

        return "content/admin/update_member_form";
    }

    //회원 목록 페이지
    @GetMapping("/selectMemberList")
    public String selectMemberList(SubMenuVO subMenuVO){

        return "content/admin/member_list";
    }

    //년도별 매출 관리 페이지
    @GetMapping("/saleManagePerYear")
    public String saleManagePerYear(SubMenuVO subMenuVO){
        if(subMenuVO.getSubMenuCode() == null){
            subMenuVO.setSubMenuCode("7");
        }

        return "content/admin/sale_manage_per_year";
    }

}








