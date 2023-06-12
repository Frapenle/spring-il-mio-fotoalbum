package org.java.foto.pojo;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Email
	private String email;
	
	@Size(min = 5, max = 500)
	private String message;
	
	private LocalDateTime createdAt;
	
	public Message() {}

	public Message(String email, String message) {
		setEmail(email);
		setMessage(message);
		setCreatedAt(createdAt);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = LocalDateTime.now();
	}
	@Override
	public String toString() {
		return "Id: " + getId()
			+ "\nE-mail: " + getEmail()
			+ "\nMessaggio: " + getMessage();
	}
	
}
