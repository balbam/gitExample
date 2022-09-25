package com.example.spring02.model.message.dao;

import com.example.spring02.model.message.dto.MessageDTO;

public interface MessageDAO {
	public void create(MessageDTO dto);//메시지 쓰기
	public MessageDTO readMessage(int mid);//메시지 읽기
	public void updateState(int mid);//상태 변경

}
