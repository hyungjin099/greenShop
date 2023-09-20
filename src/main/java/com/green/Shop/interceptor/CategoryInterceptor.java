package com.green.Shop.interceptor;

import com.green.Shop.admin.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//카테고리 목록 조회 인터셉터
@Component
@RequiredArgsConstructor
public class CategoryInterceptor implements HandlerInterceptor {
    private final AdminService adminService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //카테고리 목록 조회
        modelAndView.addObject("categoryList", adminService.selectCategoryList());
    }

}
