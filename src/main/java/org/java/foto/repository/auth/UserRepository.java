package org.java.foto.repository.auth;

import java.util.Optional;

import org.java.foto.pojo.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
	public Optional<User> findByUsername(String username);
}
