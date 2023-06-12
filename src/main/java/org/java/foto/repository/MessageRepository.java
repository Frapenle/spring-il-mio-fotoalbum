package org.java.foto.repository;

import org.java.foto.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer>{

}
