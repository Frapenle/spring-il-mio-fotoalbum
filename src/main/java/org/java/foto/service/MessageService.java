package org.java.foto.service;

import org.java.foto.pojo.Message;
import org.java.foto.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	@Autowired
	private MessageRepository messageRepository;
	
	public Message save(Message message) {
		return messageRepository.save(message);
	}

}
