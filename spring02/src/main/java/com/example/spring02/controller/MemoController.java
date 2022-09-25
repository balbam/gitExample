package com.example.spring02.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.memo.dto.MemoDTO;
import com.example.spring02.service.memo.MemoService;

@Controller //스프링에게 컨트롤러임을 명시
@RequestMapping("memo/*") //공통적인 url pattern
public class MemoController {
	@Inject
	MemoService memoService;
	
	@RequestMapping("list.do") //세부 url pattern ("memo/list.do")
	public ModelAndView list(ModelAndView mav) {
		List<MemoDTO> items=memoService.list();
		mav.setViewName("memo/memo_list"); //포워딩할 뷰의 이름
		mav.addObject("list", items);//전달할 데이터(모델)
		return mav;
	}
	
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute MemoDTO dto) {
		memoService.insert(dto);//F4
		return "redirect:/memo/list.do";
	}
	
	@RequestMapping("view/{idx}")
	public ModelAndView view(@PathVariable int idx, ModelAndView mav) {
		//포워딩할 뷰의 이름
		mav.setViewName("memo/view");
		//전달할 데이터
		mav.addObject("dto", memoService.memo_view(idx));
		return mav;
	}
	
	@RequestMapping("update/{idx}")
	public String update(@PathVariable int idx, @ModelAttribute MemoDTO dto) {
		//메모 수정
		memoService.update(dto);
		//수정완료후 목록으로 이동
		return "redirect:/memo/list.do";
	}
	
	@RequestMapping("delete/{idx}")
	public String delete(@PathVariable int idx) {
		//레코드 삭제 처리
		memoService.delete(idx);
		//목록이동
		return "redirect:/memo/list.do";
	}
}
