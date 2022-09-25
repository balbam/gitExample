package com.example.spring02.controller.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.shop.dto.CartDTO;
import com.example.spring02.service.shop.CartService;

@Controller
@RequestMapping("shop/cart/*")
public class CartController {
	
	@Inject
	CartService cartService;
	
	@RequestMapping("list.do")
	public ModelAndView list(HttpSession session, ModelAndView mav) {
		Map<String, Object> map = new HashMap<>();
		//세션변수 확인
		String userid=(String)session.getAttribute("userid");
		if(userid != null) {//로그인한 경우
			List<CartDTO> list = cartService.listCart(userid);
			//장바구니 합계 계산
			int sumMoney = cartService.sumMoney(userid);
			//배송료 계산
			int fee = sumMoney >= 30000 ? 0 : 2500; //합계3만원 이상이면 배송료 0원
			map.put("sumMoney", sumMoney); //장바구니 금액 합계
			map.put("fee", fee);//배송료
			map.put("sum", sumMoney + fee); //총합계금액
			
			map.put("list", list);//맵에 자료 추가
			map.put("count", list.size());
			mav.setViewName("shop/cart_list");
			mav.addObject("map", map);
			return mav;
		}else {//로그인하지 않은 경우
			return new ModelAndView("member/login", "", null);
		}
	}
	
	
	@RequestMapping("insert.do") //세부 url
	public String insert(HttpSession session, @ModelAttribute CartDTO dto) {
		//세션에 userid 변수가 존재하는지 확인
		String userid=(String)session.getAttribute("userid");
//		if(userid == null) {//로그인 하지 않은 상태
//			return "redirect:/member/login.do"; //로그인 페이지로
//		}
		dto.setUserid(userid);
		cartService.insert(dto);
		
		//장바구니에 insert 처리 후 장바구니 목록으로 이동
		return "redirect:/shop/cart/list.do";
	}
	
	//장바구니 개별 상품 삭제
	@RequestMapping("delete.do")
	public String delete(@RequestParam int cart_id, HttpSession session) {
		if(session.getAttribute("userid") != null)
			cartService.delete(cart_id);
		return "redirect:/shop/cart/list.do";
	}
	
	//장바구니 모두 비우기
	@RequestMapping("deleteAll.do")
	public String deleteAll(HttpSession session) {
		String userid=(String)session.getAttribute("userid");
		if(userid != null) {//로그인 상태이면
			//장바구니를 비우고
			cartService.deleteAll(userid);
		}
		//장바구니 목록이동
		return "redirect:/shop/cart/list.do";
	}
	
	@RequestMapping("update.do")
	public String update(@RequestParam int[] amount, 
			@RequestParam int[] cart_id, HttpSession session) {
		String userid=(String)session.getAttribute("userid");
		if(userid != null) {
			for(int i=0; i<cart_id.length; i++) {
				if(amount[i] == 0) {//수량이 0이면 레코드 삭제
					cartService.delete(cart_id[i]);
				}else {//0이 아니면 수정
					CartDTO dto = new CartDTO();
					dto.setUserid(userid);
					dto.setCart_id(cart_id[i]);
					dto.setAmount(amount[i]);
					cartService.modifyCart(dto);
				}
			}
		}
		return "redirect:/shop/cart/list.do";
	}
	

}
