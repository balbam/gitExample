package com.example.spring02.controller.message;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring02.model.message.dto.MessageDTO;
import com.example.spring02.service.message.MessageService;

@RestController
@RequestMapping("messages/*")
public class MessageController {
	@Inject
	MessageService messageService;
	@RequestMapping(value = "/")
	public ResponseEntity<String> addMessage(@RequestBody MessageDTO dto){
		//ResponseEntity : 리턴값을 json+에러메시지를 함께 처리해준다.
		ResponseEntity<String> entity=null;
		try {
			messageService.addMessage(dto);
			entity=new ResponseEntity<>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			//ex)400에러 : 상호간 변수가 안맞을 때
		}
		return entity;
	}

}
