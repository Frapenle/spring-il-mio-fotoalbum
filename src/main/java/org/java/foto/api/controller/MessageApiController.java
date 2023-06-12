package org.java.foto.api.controller;

import org.java.foto.pojo.Message;
import org.java.foto.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class MessageApiController {
	
	@Autowired
	private MessageService messageService;
	@PostMapping("/message")
	public ResponseEntity<Message> storeMessage(@RequestBody Message message){
		message = messageService.save(message);
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}
}
